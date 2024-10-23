
package tasknine;
import java.util.Scanner;
import java.lang.Math;
/**
 * Asks the user to enter a hexadecimal number, then converts the given number
 * to a regular decimal number and prints it to the terminal.
 */
public class Hex2Dec {

    /* Converts a hexadecimal character to a decimal integer */
    public static int hexCharToInt(char c) {
        int cAscii = (int)c;
        if (cAscii >= 48 && cAscii <= 57 ) {
            return c - 48;
        } else if (cAscii >= 65 && cAscii <= 70 ) {
            return c - 65 + 10;
        } else if (cAscii >= 97 && cAscii <= 102 ) {
            return c - 97 + 10;
        } else {
            return -1;
        }
    }

    /* Converts a String representing a hexadecimal number to a decimal Int */
    public static int hexToDecimal(String hex) {
        int total = 0;
        for (int i = 0; i < hex.length(); i++) {
            total += hexCharToInt(hex.charAt(i)) * Math.pow(16, (hex.length() - 1 - i));
        }
        return total;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter a hex number: ");
        String enteredHex = in.nextLine();
        System.out.println("The decimal value for " + enteredHex + " is " + hexToDecimal(enteredHex) + ".");

        in.close();
    }
}
