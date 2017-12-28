/*
 * Copyright (c) 2017
 *
 *
 * @author MacsCX
 */

package utils;

public class EncryptorTest {
    public static void main(String[] args)  {
        Encryptor encryptor = new Encryptor("Kurła dobra rypka!");
        String encrypted = encryptor.mixedEncryption("Grażyna polej no musztardkie!");
        String decrypted = encryptor.mixedDecryption(encrypted);

        System.out.printf("Encrypted string: %s\n", encrypted);
        System.out.printf("Decrypted string: %s", decrypted);

    }
}
