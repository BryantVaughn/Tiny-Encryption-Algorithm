/* This is an implementation of a decryption algorithm
 * to decrypt an encrypted message that used TEA. This 
 * program will take user input for the key and the 
 * encrypted message, then perform the needed operations 
 * and printout the original message.
 * 
 * Author: Bryant Vaughn
 * Date: 08/2019
 */
import java.util.*;

public class TEA_Decryption {

    // Constants and array variables
    final int DELTA_ONE = 0x11111111;
    final int DELTA_TWO = 0x22222222;
    int[] K = new int[4];
    int[] L = new int[3];
    int[] R = new int[3];

    // Program driver
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        TEA_Decryption Tea = new TEA_Decryption();

        // Get keys from user
        for(int i = 0; i < Tea.K.length; i++) {
            System.out.print("Please input K[" + i + "] in Hex String (without '0x'): ");
            String key = keyboard.nextLine();
            Tea.K[i] = Integer.parseUnsignedInt(key, 0, key.length(), 16);
            System.out.println();
            System.out.println(Integer.toHexString(Tea.K[i]).toUpperCase());    
        }

        // Get plaintext from user
        System.out.print("Please input L[2] in Hex String (without '0x'): ");
        String L2 = keyboard.nextLine();
        Tea.L[2] = Integer.parseUnsignedInt(L2, 0, L2.length(), 16);
        System.out.println();
        System.out.println(Integer.toHexString(Tea.L[2]).toUpperCase());

        System.out.print("Please input R[2] in Hex String (without '0x'): ");
        String R2 = keyboard.nextLine();
        Tea.R[2] = Integer.parseUnsignedInt(R2, 0, R2.length(), 16);

        // Initializing L[0], L[1], R[0], and R[1] as 0x00000000
        for(int i = 0; i < Tea.L.length - 1; i++) {
            Tea.L[i] = Tea.R[i] = 0x00000000;
        }

        // Decrypt ciphertext in L[2] and R[2]
        Tea.decrypt();

        System.out.println();

        // Print the plaintext
        Tea.printPlaintext();
    }

    // Decryption of TEA ciphertext
    public void decrypt() {

        for(int i = 1; i > 0; i--) {
            // i will only be 1, using it for index
            R[i] = L[i+1];
            L[i] = (R[i+1] - (((L[i+1] << 4) + K[i+1]) ^ (L[i+1] + DELTA_TWO) ^ ((L[i+1] >>> 5) + K[i+2])));

            R[i-1] = L[i];
            L[i-1] = (R[i] - (((L[i] << 4) + K[i-1]) ^ (L[i] + DELTA_ONE) ^ ((L[i] >>> 5) + K[i])));
        }
    }

    // Print method for plaintext
    public void printPlaintext() {
        // for loop to run through all L[] and R[] values
        for(int i = 2; i > -1; i--) {
            System.out.print("L[" + i + "] = " + Integer.toHexString(L[i]).toUpperCase() + "\t");
            System.out.println("R[" + i + "] = " + Integer.toHexString(R[i]).toUpperCase());
        }
    }
}