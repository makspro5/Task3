import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;


public class CompGenerator{

    private int movesCount;

    private byte[] key = new byte[16];

    private Mac signer = null;

    private int pcMove;

    public CompGenerator(int movesCount){
        this.movesCount = movesCount;
        generateKey();
        setPCMove();
        initEncryption();
        printHMAC();
    }

    private void generateKey() {
        SecureRandom random = new SecureRandom();
        random.nextBytes(key);
    }

    public void setPCMove() {
        Random pcRandomChoice = new Random();
        pcMove = pcRandomChoice.nextInt(movesCount);
    }

    private void initEncryption() {
        try {
            signer = Mac.getInstance("HmacSHA3-256");
            SecretKeySpec keySpec = new SecretKeySpec(key, "HmacSHA3-256");
            signer.init(keySpec);
        } catch (NoSuchAlgorithmException wrongAlgorithm) {
            denyEncription(wrongAlgorithm,"This algorithm is not supported");
        }
        catch(InvalidKeyException wrongKey) {
            denyEncription(wrongKey,"This key is not valid.");
        }

    }
    private void denyEncription(Exception e,String message) {
        System.out.println(e.getClass().getName() + " exception:");
        System.out.println(message);
        signer = null;
    }



    public void printHMAC() {
        if (signer != null) {
            printBytesToHex(signer.doFinal(String.valueOf(pcMove).getBytes()),"HMAC");
        } else
            System.out.println("Encription is not initialized.");
    }

    public void printKey() {
        printBytesToHex(key,"HMAC key");
    }

    public int getPCMove() {
        return pcMove;
    }

    public static void printBytesToHex(byte[] data ,String message) {
        StringBuilder a = new StringBuilder(data.length);
        a.append(message + ":\n");
        for (byte b: data)
            a.append(String.format("%x",b));
        System.out.println(a.toString());
    }
}