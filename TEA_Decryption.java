/* This is an implementation of a decryption algorithm
 * to decrypt an encrypted message that used TEA. This 
 * program will take user input for the key and the 
 * encrypted message, then perform the needed operations.
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
}