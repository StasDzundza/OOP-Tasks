import java.io.IOException;

public class Main {
    public static void main(String[]args) throws IOException, ClassNotFoundException {
        Cypher cypher = new Cypher(5);
        String s1 = new String("qwerty");
        DecryptInputStream<String> decryptor = new DecryptInputStream<>();
        byte[]decryptedData = decryptor.decryptData(s1,cypher);
        EncryptOutputStream<String> encryptor = new EncryptOutputStream<>();
        String s2 = encryptor.encryptData(decryptedData,cypher);
        System.out.println(s2);
    }
}
