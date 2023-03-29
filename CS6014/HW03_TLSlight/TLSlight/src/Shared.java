import java.io.*;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
public class Shared {

    static String publicKeyString = "FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B" +
            "80DC1CD129024E088A67CC74020BBEA63B139B22514A08798E3404DDEF9" +
            "519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7E" +
            "C6F44C42E9A637ED6B0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C" +
            "4B1FE649286651ECE45B3DC2007CB8A163BF0598DA48361C55D39A69163FA" +
            "8FD24CF5F83655D23DCA3AD961C62F356208552BB9ED529077096966D670C" +
            "354E4ABC9804F1746C08CA18217C32905E462E36CE3BE39E772C180E86039" +
            "B2783A2EC07A28FB5C55DF06F4C52C9DE2BCBF6955817183995497CEA956A" +
            "E515D2261898FA051015728E5A8AACAA68FFFFFFFFFFFFFFFF";

    static BigInteger g = new BigInteger("2");
    static BigInteger n = new BigInteger(publicKeyString, 16);

    static SecretKeySpec _serverEncrypt;
    static SecretKeySpec _clientEncrypt;
    static SecretKeySpec _serverMAC;
    static SecretKeySpec _clientMAC;
    static IvParameterSpec _serverIV;
    static IvParameterSpec _clientIV;


    static Certificate getCertificate(String fileName) throws FileNotFoundException, CertificateException {
        File certificateFile = new File (fileName);
        FileInputStream certificateInputStream = new FileInputStream(certificateFile);
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Certificate certificate = certificateFactory.generateCertificate(certificateInputStream);
        return certificate;
    }
    static Certificate StringToCertificate(String certificateString) throws CertificateException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        ByteArrayInputStream certificateInputStream = new ByteArrayInputStream(certificateString.getBytes());
        Certificate certificate = certificateFactory.generateCertificate(certificateInputStream);
        return certificate;
    }


    public static byte[] hkdfExpand(byte[] input, byte[] tag) throws Exception {
        byte[] concatenated_tag = new byte[tag.length + 1];

        for(int i = 0; i < tag.length; i++){
            concatenated_tag[i] = tag[i];
        }
        concatenated_tag[concatenated_tag.length -1] = 0x01;

        Mac hmac = Mac.getInstance("HmacSHA256");
        hmac.init(new SecretKeySpec(input, "HmacSHA256"));
        byte[] okm = hmac.doFinal(concatenated_tag);

        byte output[] = new byte[16];
        System.arraycopy(okm,0, output, 0, 16);

        return output;
    }



    static void makeSecretKeys(byte[] clientNonce, byte[] sharedSecretFromDiffieHellman) throws Exception {

        //byte[] prk = HMAC(key = clientNonce, data = sharedSecretFromDiffieHellman);

        Mac hmac = Mac.getInstance("HmacSHA256");
        hmac.init(new SecretKeySpec(sharedSecretFromDiffieHellman, "HmacSHA256"));
        byte[] prk = hmac.doFinal(clientNonce);

        byte[] serverEncrypt = hkdfExpand(prk, "server encrypt".getBytes());
        byte[] clientEncrypt = hkdfExpand(serverEncrypt, "client encrypt".getBytes());
        byte[] serverMAC = hkdfExpand(clientEncrypt, "server MAC".getBytes());
        byte[] clientMAC = hkdfExpand(serverMAC, "client MAC".getBytes());
        byte[] serverIV = hkdfExpand(clientMAC, "server IV".getBytes());
        byte[] clientIV = hkdfExpand(serverIV, "client IV".getBytes());

        _serverEncrypt = new SecretKeySpec(serverEncrypt, "HmacSHA256");
        _clientEncrypt = new SecretKeySpec(clientEncrypt, "HmacSHA256");
        _serverMAC = new SecretKeySpec(serverMAC, "HmacSHA256");
        _clientMAC = new SecretKeySpec(clientMAC, "HmacSHA256");
        _serverIV = new IvParameterSpec(serverIV);
        _clientIV = new IvParameterSpec(serverEncrypt);
    }

    public static byte[] encrypt(byte[] macType, byte[] messageArr, byte[] typeIV, byte[] typeOfEncrypt) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(macType, "HmacSha256");
        mac.init(secretKeySpec);
        byte[] messageMac = mac.doFinal(messageArr);

        ByteArrayOutputStream finalMessageMac = new ByteArrayOutputStream();
        finalMessageMac.writeBytes(messageArr);
        finalMessageMac.writeBytes(messageMac);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(typeIV);
        SecretKeySpec cipherSpec = new SecretKeySpec(typeOfEncrypt, "AES");

        cipher.init(Cipher.ENCRYPT_MODE, cipherSpec, ivParameterSpec);

        return cipher.doFinal(finalMessageMac.toByteArray());

    }
    public static byte[] decrypt(byte[] messageArr, byte[] typeIV, byte[] typeOfEncrypt) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(typeIV);
        SecretKeySpec cipherSpec = new SecretKeySpec(typeOfEncrypt, "AES");

        cipher.init(Cipher.DECRYPT_MODE, cipherSpec, ivParameterSpec);

        byte[] result = cipher.doFinal(messageArr);



        return Arrays.copyOf(result, result.length - 32);
    }

}
