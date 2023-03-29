import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Scanner;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Mac;
import javax.crypto.Cipher;

import static java.lang.System.exit;

public class Client {
    public static void main(String[] args) throws Exception {


        Socket socketToServer = new Socket("127.0.0.1", 8080);

        ObjectOutputStream out = new ObjectOutputStream( socketToServer.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socketToServer.getInputStream());
        System.out.println("client connected");


        // send Nonce1 to server
        byte Nonce[] = SecureRandom.getSeed(32);
        out.writeObject(Nonce);


        // get  Certificate
        Certificate certificate = Shared.getCertificate("CASignedClientCertificate.pem");

        byte DHPrivateKeyArr[] = SecureRandom.getSeed(32);
        BigInteger DHPrivateKey = new BigInteger(DHPrivateKeyArr);
        BigInteger DHPublicKey = Shared.g.modPow(DHPrivateKey, Shared.n);

        // create Key factory for later
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        // make key spec
        File PrivateKeyFile = new File("clientPrivateKey.der");
        FileInputStream PrivateKeyFileStream = new FileInputStream(PrivateKeyFile);
        byte PrivateKeyString[] = PrivateKeyFileStream.readAllBytes();

        PKCS8EncodedKeySpec KeySpec = new PKCS8EncodedKeySpec(PrivateKeyString);
        PrivateKey PrivateKey = keyFactory.generatePrivate(KeySpec);

        // sign
        Signature PrivateSignature = Signature.getInstance("SHA256withRSA");
        PrivateSignature.initSign(PrivateKey);
        PrivateSignature.update(DHPublicKey.toByteArray());
        byte[] DHSignedPublicKey = PrivateSignature.sign();

        Certificate serverCert = (Certificate) in.readObject();
        BigInteger serverDHPublicKey = (BigInteger) in.readObject();
        byte[] serverDHSignedPublicKey = (byte[]) in.readObject();


        out.writeObject(certificate);
        out.writeObject(DHPublicKey);
        out.writeObject(DHSignedPublicKey);
        out.flush();

        // calculate shared secret key
        BigInteger SharedKey = serverDHPublicKey.modPow((BigInteger) DHPrivateKey,Shared.n);

        // get secret keys
        Shared.makeSecretKeys(Nonce, SharedKey.toByteArray());


        // mac encrypt all handshake messages so far for comparing with server records
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(Shared._serverMAC);

        // Mac encrypt all previous messages
        byte[] NonceMac = mac.doFinal(Nonce);

        byte[] serverCertMac = mac.doFinal(serverCert.getEncoded());
        byte[] serverDHPublicKeyMac = mac.doFinal(serverDHPublicKey.toByteArray());
        byte[] serverDHSignedPublicKeyMac = mac.doFinal(serverDHSignedPublicKey);

        byte[] clientCertMac = mac.doFinal(certificate.getEncoded());
        byte[] clientDHPublicKeyMac = mac.doFinal(DHPublicKey.toByteArray());
        byte[] clientDHSignedPublicKeyMac = mac.doFinal(DHSignedPublicKey);

        // put into byte stream
        ByteArrayOutputStream clientHandshakeRecords = new ByteArrayOutputStream();
        clientHandshakeRecords.writeBytes(NonceMac);
        clientHandshakeRecords.writeBytes(serverCertMac);
        clientHandshakeRecords.writeBytes(serverDHPublicKeyMac);
        clientHandshakeRecords.writeBytes(serverDHSignedPublicKeyMac);
        clientHandshakeRecords.writeBytes(clientCertMac);
        clientHandshakeRecords.writeBytes(clientDHPublicKeyMac);
        clientHandshakeRecords.writeBytes(clientDHSignedPublicKeyMac);

        // compare
        byte[] serverHandshakeRecordsArr = (byte[]) in.readObject();
        byte[] clientHandshakeRecordsArr = clientHandshakeRecords.toByteArray();

        for(int i = 0; i < clientHandshakeRecordsArr.length; i++){
            if(serverHandshakeRecordsArr[i]!=clientHandshakeRecordsArr[i]){
                System.out.println("mismatched records, exiting...");
                exit(1);
            }
        }
        System.out.println("handshake successful");


        // mac encrypt all handshake messages so far with clientMac for sending to server
        mac = Mac.getInstance("HmacSHA256");
        mac.init(Shared._clientMAC);

        // Mac encrypt all previous messages
        NonceMac = mac.doFinal(Nonce);

        serverCertMac = mac.doFinal(serverCert.getEncoded());
        serverDHPublicKeyMac = mac.doFinal(serverDHPublicKey.toByteArray());
        serverDHSignedPublicKeyMac = mac.doFinal(serverDHSignedPublicKey);

        clientCertMac = mac.doFinal(certificate.getEncoded());
        clientDHPublicKeyMac = mac.doFinal(DHPublicKey.toByteArray());
        clientDHSignedPublicKeyMac = mac.doFinal(DHSignedPublicKey);

        // put into byte stream
        clientHandshakeRecords = new ByteArrayOutputStream();
        clientHandshakeRecords.writeBytes(NonceMac);
        clientHandshakeRecords.writeBytes(serverCertMac);
        clientHandshakeRecords.writeBytes(serverDHPublicKeyMac);
        clientHandshakeRecords.writeBytes(serverDHSignedPublicKeyMac);
        clientHandshakeRecords.writeBytes(clientCertMac);
        clientHandshakeRecords.writeBytes(clientDHPublicKeyMac);
        clientHandshakeRecords.writeBytes(clientDHSignedPublicKeyMac);

        out.writeObject(clientHandshakeRecords.toByteArray());


        // get 2 messages from server
        byte[] messageFromServer = (byte[]) in.readObject();
        byte[] messageFromServer2 = (byte[]) in.readObject();

        byte[] stringFromServer = Shared.decrypt(Shared._serverMAC.getEncoded(), messageFromServer, Shared._serverIV.getIV(), Shared._serverEncrypt.getEncoded());
        byte[] stringFromServer2 = Shared.decrypt(Shared._serverMAC.getEncoded(), messageFromServer2, Shared._serverIV.getIV(), Shared._serverEncrypt.getEncoded());

        for(int i = 0; i < stringFromServer.length; i++){
            System.out.print((char) stringFromServer[i]);
        }
        System.out.println();
        for(int i = 0; i < stringFromServer2.length; i++){
            System.out.print((char) stringFromServer2[i]);
        }
        System.out.println();

        // send message to server
        String message = "yo it works!";
        out.writeObject(Shared.encrypt(Shared._clientMAC.getEncoded(), message.getBytes(), Shared._clientIV.getIV(), Shared._clientEncrypt.getEncoded()));


    }



}
