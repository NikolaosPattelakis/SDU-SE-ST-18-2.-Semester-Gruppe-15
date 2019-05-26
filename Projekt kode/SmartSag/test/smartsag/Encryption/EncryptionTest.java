/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartsag.Encryption;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;


/**
 *
 * @author sande
 */
public class EncryptionTest {
    
    public EncryptionTest() {
        
    }
    
    /**
     * Test of encrypt/decrypt methods of the class Encryption.
     */
    @Test
    public void testEncryptDecrypt() {
        Encryption encryption = new Encryption("AES/CBC/PKCS5Padding", "MsC3wSAtCfkAuxs2u2QXhZQxJ", "Ba0tHw7QiQ8usc7W");
        
        String data = "vgcK20SU8ccycmJ3su1KvxEuAUsiiG6rofup3fz5V69e1DmGRa"; // Random test data
        String encryptedData = encryption.encryptData(data);
        String decryptedData = encryption.decryptData(encryptedData);
        
        assertEquals(data, decryptedData);
    }
    
}
