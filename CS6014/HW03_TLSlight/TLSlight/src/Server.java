import javax.crypto.interfaces.DHPublicKey;
import java.io.*;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Mac;
import javax.crypto.Cipher;

import static java.lang.System.exit;

public class Server {
    public static void main(String[] args) throws Exception {
        Shared.getCertificate("CASignedServerCertificate.pem");

        // set up communication
        ServerSocket ss = new ServerSocket(8080);
        Socket socketToClient = ss.accept();
        System.out.println("server connected");
        ObjectInputStream in = new ObjectInputStream(socketToClient.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream( socketToClient.getOutputStream() );

        // get  certificate
        Certificate certificate = Shared.getCertificate("CASignedServerCertificate.pem");

        // generate diffie Hellman keys
        byte[] Nonce = (byte[]) in.readObject();



        byte[] DHPrivateKeyArr = SecureRandom.getSeed(32);
        System.out.println("got Nonce");

        BigInteger DHPrivateKey = new BigInteger(DHPrivateKeyArr);
        BigInteger DHPublicKey = Shared.g.modPow(DHPrivateKey, Shared.n);

        // create Key factory for later
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        // get  key pair
        File PrivateKeyFile = new File ("serverPrivateKey.der");
        FileInputStream PrivateKeyFileStream = new FileInputStream(PrivateKeyFile);
        byte PrivateKeyString[] = PrivateKeyFileStream.readAllBytes();

        PKCS8EncodedKeySpec KeySpec = new PKCS8EncodedKeySpec(PrivateKeyString);
        PrivateKey PrivateKey = keyFactory.generatePrivate(KeySpec);

        // sign
        Signature PrivateSignature = Signature.getInstance("SHA256withRSA");
        PrivateSignature.initSign(PrivateKey);
        PrivateSignature.update(DHPublicKey.toByteArray());
        byte[] DHSignedPublicKey = PrivateSignature.sign();

        // send cert, DHpublicKey, and signed DHpublicKey
        out.writeObject(certificate);
        out.writeObject(DHPublicKey);
        out.writeObject(DHSignedPublicKey);
        out.flush();

        // save client messages
        Certificate clientCert = (Certificate) in.readObject();
        BigInteger clientDHPublicKey = (BigInteger) in.readObject();
        byte[] clientDHSignedPublicKey = (byte[]) in.readObject();

        // calculate shared secret key
        BigInteger SharedKey = clientDHPublicKey.modPow((BigInteger) DHPrivateKey,Shared.n);

        // get secret keys
        Shared.makeSecretKeys(Nonce, SharedKey.toByteArray());

        // mac encrypt all handshake messages so far
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(Shared._serverMAC);

        // Mac encrypt all previous messages
        byte[] NonceMac = mac.doFinal(Nonce);

        byte[] serverCertMac = mac.doFinal(certificate.getEncoded());
        byte[] serverDHPublicKeyMac = mac.doFinal(DHPublicKey.toByteArray());
        byte[] serverDHSignedPublicKeyMac = mac.doFinal(DHSignedPublicKey);

        byte[] clientCertMac = mac.doFinal(clientCert.getEncoded());
        byte[] clientDHPublicKeyMac = mac.doFinal(clientDHPublicKey.toByteArray());
        byte[] clientDHSignedPublicKeyMac = mac.doFinal(clientDHSignedPublicKey);

        // put into byte stream
        ByteArrayOutputStream serverHandshakeRecords = new ByteArrayOutputStream();
        serverHandshakeRecords.writeBytes(NonceMac);
        serverHandshakeRecords.writeBytes(serverCertMac);
        serverHandshakeRecords.writeBytes(serverDHPublicKeyMac);
        serverHandshakeRecords.writeBytes(serverDHSignedPublicKeyMac);
        serverHandshakeRecords.writeBytes(clientCertMac);
        serverHandshakeRecords.writeBytes(clientDHPublicKeyMac);
        serverHandshakeRecords.writeBytes(clientDHSignedPublicKeyMac);

        // send to client
        out.writeObject(serverHandshakeRecords.toByteArray());

        // mac encrypt all handshake messages so far
        mac = Mac.getInstance("HmacSHA256");
        mac.init(Shared._clientMAC);

        // Mac encrypt all previous messages with clientMac for comparison with client records
        NonceMac = mac.doFinal(Nonce);

        serverCertMac = mac.doFinal(certificate.getEncoded());
        serverDHPublicKeyMac = mac.doFinal(DHPublicKey.toByteArray());
        serverDHSignedPublicKeyMac = mac.doFinal(DHSignedPublicKey);


        clientCertMac = mac.doFinal(clientCert.getEncoded());
        clientDHPublicKeyMac = mac.doFinal(clientDHPublicKey.toByteArray());
        clientDHSignedPublicKeyMac = mac.doFinal(clientDHSignedPublicKey);

        // put into byte stream
        serverHandshakeRecords = new ByteArrayOutputStream();
        serverHandshakeRecords.writeBytes(NonceMac);
        serverHandshakeRecords.writeBytes(serverCertMac);
        serverHandshakeRecords.writeBytes(serverDHPublicKeyMac);
        serverHandshakeRecords.writeBytes(serverDHSignedPublicKeyMac);
        serverHandshakeRecords.writeBytes(clientCertMac);
        serverHandshakeRecords.writeBytes(clientDHPublicKeyMac);
        serverHandshakeRecords.writeBytes(clientDHSignedPublicKeyMac);


        byte[] clientHandshakeRecordsArr = (byte[]) in.readObject();
        byte[] serverHandshakeRecordsArr = serverHandshakeRecords.toByteArray();

        for(int i = 0; i < clientHandshakeRecordsArr.length; i++){
            if(serverHandshakeRecordsArr[i]!=clientHandshakeRecordsArr[i]){
                System.out.println("mismatched records, exiting...");
                exit(1);
            }
        }
        System.out.println("handshake successful");


        // send 2 messages to the client
        String message = "please please please work";
        byte[] messageToSend = Shared.encrypt(Shared._serverMAC.getEncoded(), message.getBytes(), Shared._serverIV.getIV(), Shared._serverEncrypt.getEncoded());
        String message2 = "oh thank god";
        byte[] messageToSend2 = Shared.encrypt(Shared._serverMAC.getEncoded(), message2.getBytes(), Shared._serverIV.getIV(), Shared._serverEncrypt.getEncoded());

        out.writeObject(messageToSend);
        out.writeObject(messageToSend2);

        // get message from client
        byte[] messageFromClient = (byte[]) in.readObject();
        byte[] stringFromClient = Shared.decrypt(messageFromClient, Shared._clientIV.getIV(), Shared._clientEncrypt.getEncoded());

        for(int i = 0; i < stringFromClient.length; i++){
            System.out.print((char) stringFromClient[i]);
        }
        System.out.println();
    }
}
