package smartsag.Encryption;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/*
    Unit test class that tests methods of the Encryption class.
 */
public class EncryptionTest {
    
    public EncryptionTest() {
        
    }
    
    /*
     * Test of encrypt/decrypt methods of the class Encryption.
     * Uses a random salt, key and String-data to verify that the encryption and decryption is consistent.
     */
    @Test
    public void testEncryptDecrypt() {
        Encryption encryption = new Encryption("AES/CBC/PKCS5Padding", "MsC3wSAtCfkAuxs2u2QXhZQxJ", "Ba0tHw7QiQ8usc7W");
        
        String data = "vgcK20SU8ccycmJ3su1KvxEuAUsiiG6rofup3fz5V69e1DmGRa";
        
        String encryptedData = encryption.encryptData(data);
        String decryptedData = encryption.decryptData(encryptedData);
        
        assertEquals(data, decryptedData);
    }
    
}
