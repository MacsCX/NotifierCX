/*
 *
 * ENCRYPTOR - utility to encrypt/decrypt CSV files
 * Copyright (c) 2017.
 * @author MacsCX
 */

package utils;

public class Encryptor {
    private String password;
    private int[] componentsGotFromPassword;
    

    public Encryptor(String password) {
        this.password = password;
        this.componentsGotFromPassword = getIntegersFromString(password);
    }

    /**
     *
     * @param string string to encrypt
     * @return encrypted string
     */
    public String encryptString(String string)  {
        return encryptOrDecryptString(string, "plus");
    }

    public String decryptString(String string)  {
        return encryptOrDecryptString(string, "minus");
    }

    /**
     * this method uses modified Caesar Cipher algorithm
     * and Base64 algorithm
     * @param string string to encrypt/decrypted
     * @param plusOrMinus "plus" - for encryption, "minus" - for decryption
     * @return encrypted/decrypted string
     */
    private String encryptOrDecryptString(String string, String plusOrMinus)  {
        int[] integersFromString = getIntegersFromString(string);
        char[] resultChar = new char[string.length()];
        int counterForComponentArray = 0;
        for (int counter = 0; counter<string.length(); counter++)   {
            if (counterForComponentArray == componentsGotFromPassword.length)   {
                counterForComponentArray = 0;
            }
            if (plusOrMinus.equals("plus")){
                resultChar[counter] = (char) (integersFromString[counter] + componentsGotFromPassword[counterForComponentArray]);
            }
            else if (plusOrMinus.equals("minus"))   {
                resultChar[counter] = (char) (integersFromString[counter] - componentsGotFromPassword[counterForComponentArray]);
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
