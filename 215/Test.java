import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Test{
    public static void main(String[] args) {
        String username = "lolita";
        String response = "edde4416345970aa1c431f696f3bcb59";
        String realm = "Mordor";
        String nonce = "03e2abb8a924e966bee59d41cef32851";
        String uri = "/Public/CS/Home.png";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] ha2Bytes = md.digest(("GET:" + uri).getBytes(StandardCharsets.UTF_8));
            String ha2 = bytesToHex(ha2Bytes);

            File file = new File("words.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().toLowerCase();
                String ha1 = bytesToHex(md.digest((username + ":" + realm + ":" + word).getBytes(StandardCharsets.UTF_8)));
                String response2 = bytesToHex(md.digest((ha1 + ":" + nonce + ":" + ha2).getBytes(StandardCharsets.UTF_8)));

                if (response.equals(response2)) {
                    System.out.println("Password found: " + word);
                    break;
                }
            }

            scanner.close();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("MD5 algorithm not available.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
