/* This is an encryption program that utilizes the TEA Encryption 
 * approach. This program will take two 32 bit  plaintext inputs 
 * from the user and provide the corresponding 32 bit 
 * ciphertext.
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
        Scanner keyboard = new Scanner(System.in);
        TEA_Encryption Tea = new TEA_Encryption();
        
        // Get keys from user
        for(int i = 0; i < Tea.K.length; i++) {
            System.out.print("Please input K[" + i + "] in Hex String (without '0x'): ");
            String key = keyboard.nextLine();
            Tea.K[i] = Integer.parseUnsignedInt(key, 0, key.length(), 16);
            System.out.println();
            System.out.println(Integer.toHexString(Tea.K[i]).toUpperCase());    
        }

        //Tea.encrypt();

        // Get plaintext from user
        System.out.print("Please input L[0] in Hex String (without '0x'): ");
        String L0 = keyboard.nextLine();
        Tea.L[0] = Integer.parseUnsignedInt(L0, 0, L0.length(), 16);
        System.out.println();
        System.out.println(Integer.toHexString(Tea.L[0]));

        System.out.print("Please input R[0] in Hex String (without '0x'): ");
        String R0 = keyboard.nextLine();
        Tea.R[0] = Integer.parseUnsignedInt(R0, 0, R0.length(), 16);

        // Initializing L[1], L[2], R[1], and R[2] as 0x00000000
        for(int i = 1; i < Tea.L.length; i++) {
            Tea.L[i] = Tea.R[i] = 0x00000000;
        }
    }

    public void encrypt() {
        //System.out.println(Integer.toHexString(K[0]).toUpperCase());
        // need to perform the math calculations
    }
}