package smartsag.Encryption;

import java.io.UnsupportedEncodingException; 
import java.security.InvalidAlgorithmParameterException; 
import java.security.InvalidKeyException; 
import java.security.Key; 
import java.security.NoSuchAlgorithmException; 
import java.security.spec.InvalidKeySpecException; 
import java.security.spec.KeySpec; 
import java.util.Base64; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import javax.crypto.BadPaddingException; 
import javax.crypto.Cipher; 
import javax.crypto.IllegalBlockSizeException; 
import javax.crypto.NoSuchPaddingException; 
import javax.crypto.SecretKey; 
import javax.crypto.SecretKeyFactory; 
import javax.crypto.spec.IvParameterSpec; 
import javax.crypto.spec.PBEKeySpec; 
import javax.crypto.spec.SecretKeySpec; 

public class Encryption { 
 
    private Cipher cipher; 
    private Key key; 
    private IvParameterSpec ivSpec; 
 
    public Encryption(String encryptionType, String encryptionKey, String salt) { 
        this.initParameterSpec(); 
        try { 
            this.cipher = Cipher.getInstance(encryptionType); 
        } catch (NoSuchAlgorithmException | NoSuchPaddingException ex) { 
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex); 
        } 
        this.key = this.generateKey(encryptionKey, encryptionType, salt); 
    } 
     
    private void initParameterSpec(){ 
        byte[] ivByteArray = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; 
        this.ivSpec = new IvParameterSpec(ivByteArray); 
    } 
 
    private Key generateKey(String encryptionKey, String encryptionType, String salt) { 
        SecretKeySpec generatedKey = null; 
        try { 
            SecretKeyFactory factory; 
 
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256"); 
 
            KeySpec keySpec = new PBEKeySpec(encryptionKey.toCharArray(), salt.getBytes(), 65536, 256); 
            SecretKey secretKey = factory.generateSecret(keySpec); 
            generatedKey = new SecretKeySpec(secretKey.getEncoded(), "AES"); 
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) { 
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex); 
        } 
        return generatedKey; 
    } 
 
    public String encryptData(String data) { 
        String encryptedValue = null; 
        try { 
            this.cipher.init(Cipher.ENCRYPT_MODE, key, this.ivSpec); 
            byte[] encryptedData = cipher.doFinal(data.getBytes("utf-8")); 
            encryptedValue = Base64.getEncoder().encodeToString(encryptedData); 
 
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | UnsupportedEncodingException ex) { 
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex); 
        } 
        return encryptedValue; 
    } 
 
    public String decryptData(String data) { 
        String decryptedValue = null; 
        try { 
            this.cipher.init(Cipher.DECRYPT_MODE, this.key, this.ivSpec); 
 
            byte[] decryptedData = Base64.getDecoder().decode(data); 
            decryptedValue = new String(cipher.doFinal(decryptedData)); 
            return decryptedValue; 
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException ex) { 
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex); 
        } 
        return decryptedValue; 
    } 
} 
