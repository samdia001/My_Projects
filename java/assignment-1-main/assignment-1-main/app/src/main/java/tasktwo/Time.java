package tasktwo;
import java.util.Scanner;
/**
 * this program uses a specific mathematical method in order to convert a
 *  spcific number of seconds that the user enters into hours, minutes, and seconds
 */

public class Time {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        
        System.out.print("Give a number of seconds: ");
        long  totalSeconds = scan.nextInt();

        long hours = (totalSeconds / 3600);
        long minutes = (totalSeconds % 3600) / 60;
        long seconds =  (totalSeconds % 3600) % 60 ;

        System.out.print("This corresponds to: ");
        if (hours == 1) {
            System.out.print(hours + " hour, ");
        } else {
            System.out.print(hours + " hours, ");
        }
        
        if (minutes == 1) {
            System.out.print(minutes + " minute, and ");
        } else {
            System.out.print(minutes + " minutes, and ");
        }
        if (seconds == 1) {
            System.out.print(seconds + " second.");
        } else {
            System.out.print(seconds + " seconds.");
        }
        scan.close();
    }
}
