import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DecryptInputStream <T>{
    public byte[]decryptData(T data, Cypher cypher) throws IOException {
        switch (cypher.getType()){
            case XOR:
                return decryptXOR(data,cypher.getKeyword());
            case Caesar:
                return decryptCaesar(data,cypher.getShift());
            default:
                return null;
        }
    }

    private byte[]decryptXOR(T data,String keyword) throws IOException {
        int k = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(data);
        oos.flush();
        byte [] byteData = bos.toByteArray();
        for (int i = 0; i < byteData.length; i++){
            if(k == keyword.length())
                k = 0;
            byteData[i] = (byte) (byteData[i] ^ keyword.charAt(k));
            k++;
        }
        return byteData;
    }

    private byte[]decryptCaesar(T data,int shift) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(data);
        oos.flush();
        byte [] byteData = bos.toByteArray();
        for (int i = 0; i < byteData.length; i++){
            byteData[i] = (byte) ((int)byteData[i] - shift);
        }
        return byteData;
    }
}
