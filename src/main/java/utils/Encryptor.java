/*
 *
 * ENCRYPTOR - utility to encrypt/decrypt CSV files
 * Copyright (c) 2017.
 * @author MacsCX
 */

package utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encryptor {
    private String password;
    private int[] componentsFromPassword;
    

    public Encryptor(String password) {
        this.password = password;
        this.componentsFromPassword = getIntegersFromString(password);
    }

    /**
     * this method uses modified Caesar Cipher algorithm
     * @param string string to encrypt
     * @return encrypted string
     */
    public String encryptByAddingComponents(String string)  {
        return encryptOrDecryptByCcAlgorithm(string, "plus");
    }

    /**
     * this method uses modified Caesar Cipher algorithm
     * @param string string to decrypt
     * @return decrypted string
     */
    public String decryptBySubtractingComponents(String string)  {
        return encryptOrDecryptByCcAlgorithm(string, "minus");
    }

    /**
     * this method uses base64 algorithm
     * @param string string to encrypt
     * @return encrypted string
     */
    public String encryptByBase64(String string)    {
        byte[] stringTurnedToBytes = string.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(stringTurnedToBytes);

    }

    /**
     * this method uses base64 algorithm
     * @param string string encrypted by base64 algorithm
     * @return decrypted string
     */
    public String decryptByBase64(String string)    {
        byte[] stringTurnedToBytes = Base64.getDecoder().decode(string);
        return new String(stringTurnedToBytes, StandardCharsets.UTF_8);

    }

    /**
     * this encryption method is combination of
     * modified Caesar Cipher algorithm and base64 algorithm
     * @param string string to encrypt
     * @return encrypted string
     */
    public String mixedEncryption(String string)    {
        return encryptByBase64(
                encryptByAddingComponents(string));
    }

    /**
     * this method is to decrypt string
     * decrypted by combination of modified Caesar Cipher algorithm
     * and base64 algorithm
     * @param string string to decrypt
     * @return decrypted string
     */
    public String mixedDecryption(String string)    {
        return decryptBySubtractingComponents(
                decryptByBase64(string));
    }

    /**
     * this method uses modified Caesar Cipher algorithm
     * @param string string to encrypt/decrypted
     * @param plusOrMinus "plus" - for encryption, "minus" - for decryption
     * @return encrypted/decrypted string
     */
    private String encryptOrDecryptByCcAlgorithm(String string, String plusOrMinus)  {
        int[] integersFromString = getIntegersFromString(string);
        char[] resultChar = new char[string.length()];
        int counterForComponentArray = 0;
        for (int counter = 0; counter<string.length(); counter++)   {
            if (counterForComponentArray == componentsFromPassword.length)   {
                counterForComponentArray = 0;
            }
            if (plusOrMinus.equals("plus")){
                resultChar[counter] = (char) (integersFromString[counter] + componentsFromPassword[counterForComponentArray]);
            }
            else if (plusOrMinus.equals("minus"))   {
                resultChar[counter] = (char) (integersFromString[counter] - componentsFromPassword[counterForComponentArray]);
            }
        }
        return String.valueOf(resultChar);
    }

    /**
     * private method to get components from password
     * to execute modified Caesar Cipher algorithm
     * @param string string to be transformed to an array of integers
     * @return string as an array of characters transformed to integers
     *  
     */
    private int[] getIntegersFromString(String string) {
        char[] charArray = string.toCharArray();
        int[] result = new int[charArray.length];
        for (int character = 0; character<charArray.length; character++)    {
            result[character] = (int) charArray[character];
        }
        return result;
    }
}
