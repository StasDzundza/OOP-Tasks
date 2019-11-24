import java.io.*;

public class EncryptOutputStream <T> {
    public T encryptData(byte[] decryptedData, Cypher cypher) throws IOException, ClassNotFoundException {
        switch (cypher.getType()) {
            case XOR:
                return encryptXOR(decryptedData,cypher.getKeyword());
            case Caesar:
                return encryptCaesar(decryptedData,cypher.getShift());
            default:
                return null;
        }
    }

    private T encryptXOR(byte[] decryptedData,String keyword) throws IOException, ClassNotFoundException {
        int k = 0;
        for (int i = 0; i < decryptedData.length; i++) {
            if (k == keyword.length())
                k = 0;
            decryptedData[i] = (byte) (decryptedData[i] ^ keyword.charAt(k));
            k++;
        }
        ByteArrayInputStream in = new ByteArrayInputStream(decryptedData);
        ObjectInputStream is = new ObjectInputStream(in);
        return (T) is.readObject();
    }

    private T encryptCaesar(byte[] decryptedData,int shift) throws IOException, ClassNotFoundException {
        for (int i = 0; i < decryptedData.length; i++) {
            decryptedData[i] = (byte) ((int)decryptedData[i] + shift);
        }
        ByteArrayInputStream in = new ByteArrayInputStream(decryptedData);
        ObjectInputStream is = new ObjectInputStream(in);
        return (T) is.readObject();
    }

}
