import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class DecryptTest {
    @Test
    public void cypherXORTest() throws IOException, ClassNotFoundException {
        Cypher cypher = Mockito.mock(Cypher.class);
        Mockito.when(cypher.getKeyword()).thenReturn("1010");
        Mockito.when(cypher.getType()).thenReturn(CypherType.XOR);
        DecryptInputStream<String> decrypter = new DecryptInputStream<>();
        byte[] decryptedData = decrypter.decryptData(new String("TestString"),cypher);
        EncryptOutputStream<String> encrypter = new EncryptOutputStream<>();
        String encrypted = encrypter.encryptData(decryptedData,cypher);
        Assert.assertEquals("TestString",encrypted);
        Mockito.verify(cypher,Mockito.times(2)).getKeyword();
        Mockito.verify(cypher,Mockito.times(2)).getType();
    }

    @Test
    public void cypherCaesarTest() throws IOException, ClassNotFoundException {
        Cypher cypher = Mockito.mock(Cypher.class);
        Mockito.when(cypher.getShift()).thenReturn(5);
        Mockito.when(cypher.getType()).thenReturn(CypherType.Caesar);
        DecryptInputStream<String> decrypter = new DecryptInputStream<>();
        byte[] decryptedData = decrypter.decryptData(new String("TestString"),cypher);
        EncryptOutputStream<String> encrypter = new EncryptOutputStream<>();
        String encrypted = encrypter.encryptData(decryptedData,cypher);
        Assert.assertEquals("TestString",encrypted);
        Mockito.verify(cypher,Mockito.times(2)).getShift();
        Mockito.verify(cypher,Mockito.times(2)).getType();
    }
}
