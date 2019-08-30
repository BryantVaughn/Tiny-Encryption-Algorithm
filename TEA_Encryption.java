/* This is an encryption program that utilizes the TEA Encryption 
 * approach. This program will take two 32 bit  plaintext inputs 
 * from the user, as well as four 32 bit keys, and provide the 
 * corresponding 32 bit ciphertext.
 * 
 * Author: Bryant Vaughn
 * Date: 08/2019
 */

import java.util.*;

public class TEA_Encryption {
    
    // Constants and array variables
    final int DELTA_ONE = 0x11111111;
    final int DELTA_TWO = 0x22222222;
    int[] K = new int[4];
    int[] L = new int[3];
    int[] R = new int[3];

    public static void main(String[] args) {
        TEA_Encryption Tea = new TEA_Encryption();
        
        // Get keys from user
        // for(int i = 0; i < Tea.K.length; i++) {
        //     System.out.print("Please input K[" + i + "] in Hex String (without '0x'): ");
        //     String key = keyboard.nextLine();
        //     Tea.K[i] = Integer.parseUnsignedInt(key, 0, key.length(), 16);
        //     System.out.println();
        //     System.out.println(Integer.toHexString(Tea.K[i]).toUpperCase());    
        // }
        Tea.getKeys();

        //Tea.encrypt();

        // Get plaintext from user
        // System.out.print("Please input L[0] in Hex String (without '0x'): ");
        // String L0 = keyboard.nextLine();
        // Tea.L[0] = Integer.parseUnsignedInt(L0, 0, L0.length(), 16);
        // System.out.println();
        // System.out.println(Integer.toHexString(Tea.L[0]).toUpperCase());

        // System.out.print("Please input R[0] in Hex String (without '0x'): ");
        // String R0 = keyboard.nextLine();
        // Tea.R[0] = Integer.parseUnsignedInt(R0, 0, R0.length(), 16);
        Tea.getPlaintext();

        // Initializing L[1], L[2], R[1], and R[2] as 0x00000000
        // for(int i = 1; i < Tea.L.length; i++) {
        //     Tea.L[i] = Tea.R[i] = 0x00000000;
        // }
        Tea.initializeLR();

        // Encrypt the plaintext user input
        Tea.encrypt();

        System.out.println();

        // Print the output
        Tea.printCiphertext();
    }

    // Get keys from user
    public void getKeys() {
        // Scanner object for user input
        Scanner keyboard = new Scanner(System.in);

        for(int i = 0; i < K.length; i++) {
            System.out.print("Please input K[" + i + "] in Hex String (without '0x'): ");
            String key = keyboard.nextLine();
            K[i] = Integer.parseUnsignedInt(key, 0, key.length(), 16);
            System.out.println();   
        }

        // Closing scanner object
        keyboard.close();
    }

    // Get plaintext from user
    public void getPlaintext() {
        // Scanner object for user input
        Scanner keyboard = new Scanner(System.in);

        // Getting L[0]
        System.out.print("Please input L[0] in Hex String (without '0x'): ");
        String L0 = keyboard.nextLine();
        L[0] = Integer.parseUnsignedInt(L0, 0, L0.length(), 16);

        System.out.println();

        // Getting R[0]
        System.out.print("Please input R[0] in Hex String (without '0x'): ");
        String R0 = keyboard.nextLine();
        R[0] = Integer.parseUnsignedInt(R0, 0, R0.length(), 16);

        // Closing scanner object
        keyboard.close();
    }

    // Initialize L[] and R[]
    public void initializeLR() {
        // Initializing L[1], L[2], R[1], and R[2] as 0x00000000
        for(int i = 1; i < L.length; i++) {
            L[i] = R[i] = 0x00000000;
        }
    }

    // TEA algorithm for encryption
    public void encrypt() {
        
        for(int i = 1; i < 2; i++) {
            // i will only be 1, using it for index
            L[i] = R[i-1];
            R[i] = (L[i-1] + (((R[i-1] << 4) + K[i-1]) ^ ((R[i-1] >>> 5) + K[i]) ^ (R[i-1] + DELTA_ONE)));
            
            L[i+1] = R[i];
            R[i+1] = (L[i] + (((R[i] << 4) + K[i+1]) ^ ((R[i] >>> 5) + K[i+2]) ^ (R[i] + DELTA_TWO)));
        }
    }

    // Print method for ciphertext
    public void printCiphertext() {
        // for loop to run through all L[] and R[] values
        for(int i = 0; i < L.length; i++) {
            System.out.print("L[" + i + "] = " + Integer.toHexString(L[i]).toUpperCase() + "\t");
            System.out.println("R[" + i + "] = " + Integer.toHexString(R[i]).toUpperCase());
        }
    }
}