package taskfive;
import java.util.Scanner;

/**
 * this program lets the user a number of stars and then print
 * a diamond depends on this number. The diamond consists of two 
 * triangles (higher and lower triangles).
 */
public class Diamonds {


    public static void DrawDiamond(int height) {
        
        if (height > 0) {
            System.out.println("");
        }
        // print the higher triangle
        for (int i = 0; i < height; i++) {
            int spaces = height - (i + 1);
            for (int j = 0; j < spaces; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < (1 + (i * 2)); k++) {
                System.out.print("*");
            }
            System.out.println("");
        }

        // print the lower triangle
        for (int i = (height - 1); i > 0; i--) {
            int spaces = height - i;
            for (int j = 0; j < spaces; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < ((i * 2) - 1); k++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        int enterNumber = 0;

        while (enterNumber >= 0) {
           
            System.out.print("Give a positive number: ");
            try {
                enterNumber = scan.nextInt();
                DrawDiamond(enterNumber);
            } catch (Exception e) {
                System.out.println("Only positive numbers are allowed!");
                scan.next();
            }
        }

        scan.close();
    }
}
