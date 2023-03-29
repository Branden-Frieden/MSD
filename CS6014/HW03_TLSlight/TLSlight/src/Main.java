import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class Main {
    public static void main(String[] args) throws IOException, CertificateException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException, NoSuchProviderException {
        String publicKeyString = "FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B" +
                "80DC1CD129024E088A67CC74020BBEA63B139B22514A08798E3404DDEF9" +
                "519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7E" +
                "C6F44C42E9A637ED6B0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C" +
                "4B1FE649286651ECE45B3DC2007CB8A163BF0598DA48361C55D39A69163FA" +
                "8FD24CF5F83655D23DCA3AD961C62F356208552BB9ED529077096966D670C" +
                "354E4ABC9804F1746C08CA18217C32905E462E36CE3BE39E772C180E86039" +
                "B2783A2EC07A28FB5C55DF06F4C52C9DE2BCBF6955817183995497CEA956A" +
                "E515D2261898FA051015728E5A8AACAA68FFFFFFFFFFFFFFFF";

        BigInteger g = new BigInteger("2");
        BigInteger n = new BigInteger(publicKeyString, 16);



        /////////////////////////////////Server

        // get server Certificate
        File serverCertificateFile = new File ("CASignedServerCertificate.pem");
        FileInputStream serverCertificateInputStream = new FileInputStream(serverCertificateFile);
        CertificateFactory serverCertificateFactory = CertificateFactory.getInstance("X.509");
        Certificate serverCertificate = serverCertificateFactory.generateCertificate(serverCertificateInputStream);

        // generate server DH private key
        byte DHserverPrivateKeyArr[] = SecureRandom.getSeed(32);
        BigInteger DHserverPrivateKey = new BigInteger(DHserverPrivateKeyArr);


        BigInteger DHserverPublicKey = g.modPow(DHserverPrivateKey, n);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        File serverPrivateKeyFile = new File ("ServerPrivateKey.der");
        FileInputStream serverPrivateKeyFileStream = new FileInputStream(serverPrivateKeyFile);
        byte serverPrivateKeyString[] = serverPrivateKeyFileStream.readAllBytes();

        PKCS8EncodedKeySpec serverKeySpec = new PKCS8EncodedKeySpec(serverPrivateKeyString);
        PrivateKey serverPrivateKey = keyFactory.generatePrivate(serverKeySpec);

        // sign
        Signature serverPrivateSignature = Signature.getInstance("SHA256withRSA");
        serverPrivateSignature.initSign(serverPrivateKey);
        serverPrivateSignature.update(DHserverPublicKey.toByteArray());

        byte[] serverSignedPublicKey = serverPrivateSignature.sign();


        //////////////////// client


        // get client Certificate
        File clientCertificateFile = new File ("CASignedClientCertificate.pem");
        FileInputStream clientCertificateInputStream = new FileInputStream(clientCertificateFile);
        CertificateFactory clientCertificateFactory = CertificateFactory.getInstance("X.509");
        Certificate clientCertificate = serverCertificateFactory.generateCertificate(clientCertificateInputStream);

        byte DHclientPrivateKeyArr[] = SecureRandom.getSeed(32);
        BigInteger DHclientPrivateKey = new BigInteger(DHclientPrivateKeyArr);


        BigInteger DHclientPublicKey = g.modPow(DHclientPrivateKey, n);

        // make key spec
        File clientPrivateKeyFile = new File ("ClientPrivateKey.der");
        FileInputStream clientPrivateKeyFileStream = new FileInputStream(clientPrivateKeyFile);
        byte clientPrivateKeyString[] = clientPrivateKeyFileStream.readAllBytes();

        PKCS8EncodedKeySpec clientKeySpec = new PKCS8EncodedKeySpec(clientPrivateKeyString);
        PrivateKey clientPrivateKey = keyFactory.generatePrivate(clientKeySpec);

        // sign
        Signature clientPrivateSignature = Signature.getInstance("SHA256withRSA");
        clientPrivateSignature.initSign(clientPrivateKey);
        clientPrivateSignature.update(DHclientPublicKey.toByteArray());

        byte[] clientSignedPublicKey = clientPrivateSignature.sign();



        /////////// Checking
        Certificate CA = Shared.getCertificate("CAcertificate.pem");
        PublicKey CApublicKey = CA.getPublicKey();
        clientCertificate.verify(CApublicKey);

        Signature serverVerifySigs = Signature.getInstance("SHA256withRSA");
        byte[] DHserverPublic = DHserverPublicKey.toByteArray();
        serverVerifySigs.initVerify(serverCertificate.getPublicKey());
        serverVerifySigs.update(DHserverPublic);
        System.out.println(serverVerifySigs.verify(serverSignedPublicKey));

        Signature clientVerifySigs = Signature.getInstance("SHA256withRSA");
        byte[] DHclientPublic = DHclientPublicKey.toByteArray();
        clientVerifySigs.initVerify(clientCertificate.getPublicKey());
        clientVerifySigs.update(DHclientPublic);
        System.out.println(clientVerifySigs.verify(clientSignedPublicKey));


    }
}