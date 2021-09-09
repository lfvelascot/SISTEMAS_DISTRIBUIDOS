/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usbbog;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class AesUtil {
    private static final String Algorithm = "AES";
    private final static String HEX = "0123456789ABCDEF";

    public AesUtil() {
    }
 
    
    
    // Función de cifrado. la clave es la clave
    public String encrypt(String key,String src){
        try {
            byte[] rawkey = getRawKey(key.getBytes());
            byte[] result = encrypt(rawkey,src.getBytes());
            return toHex(result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
         // Función de descifrado. El valor de la clave debe ser el mismo que el de la clave al cifrar
    public  String decrypt(String key,String encrypted){
        try {
            byte[] rawKey = getRawKey(key.getBytes());
            byte[] enc = toByte(encrypted);
            byte[] result = decrypt(rawKey,enc);
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
    private static void appendHex(StringBuffer sb,byte b){
        sb.append(HEX.charAt((b>>4)&0x0f)).append(HEX.charAt(b&0x0f));
    }
 
    private  byte[] getRawKey(byte[] seed){
        byte[] raw = null;
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(Algorithm);
                         // SHA1PRNG algoritmo de semilla aleatoria fuerte, para distinguir el método de llamada de Android 4.2.2 y superior
            SecureRandom sr = null;
            sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(seed);
            kgen.init(256,sr);
            SecretKey skey = kgen.generateKey();
            raw = skey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return raw;
    }
 
    private  byte[] encrypt(byte[] key,byte[] src) throws NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        byte[] encrypted = null;
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key,Algorithm);
            Cipher cipher = Cipher.getInstance(Algorithm);
            cipher.init(Cipher.ENCRYPT_MODE,skeySpec);
            encrypted = cipher.doFinal(src);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return encrypted;
    }
 
    private  byte[] decrypt(byte[] key,byte[] encrypted){
        byte[] decrypted = null;
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key,Algorithm);
            Cipher cipher = Cipher.getInstance(Algorithm);
            cipher.init(Cipher.DECRYPT_MODE,skeySpec);
            decrypted = cipher.doFinal(encrypted);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
        }
        return decrypted;
    }
 
    private  byte[] toByte(String hexString){
        int len = hexString.length()/2;
        byte[] result = new byte[len];
        for (int i=0;i<len;i++){
            result[i] = Integer.valueOf(hexString.substring(2*i,2*i+2),16).byteValue();
        }
        return result;
    }
 
    private String toHex(byte[] buf){
        if (buf == null){
            return "";
        }
        StringBuffer result = new StringBuffer(2*buf.length);
        for (int i=0;i<buf.length;i++){
            appendHex(result,buf[i]);
        }
        return result.toString();
    }
}

