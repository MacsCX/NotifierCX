

import java.util.Base64;
import java.util.Scanner;

public class FirstSteps {

    public static void main(String[] args)  {
        byte[] encodedBytes = Base64.getEncoder().encode("Test".getBytes());
        System.out.println("encodedBytes " + new String(encodedBytes));



    }
}
