
package taskseven;
import java.util.Scanner;

/**
 * The program determines the day the user enters,
 * calculates the day of the week for the specified date, 
 * and then prints it for the user on the screen
 */
public class DayOfWeek {
    
    public static String DayOfWeekString (int dayOfWeek) {
        switch (dayOfWeek) {
            case 0:
                return "Saturday";
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            default:
                return "";
        }
    }

    
    public static int CalculateDayOfWeek(int year, int month, int dayOfMonth) {
      
        if (month == 1) {
            month = 13;
            year--;
        } else if (month == 2) {
            month = 14;
            year--;
        }

        
        return (dayOfMonth + ((26 * (month + 1)) / 10) + (year % 100) + ((year % 100) / 4) + ((year / 100) / 4) + (5 * (year / 100))) % 7;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

       
        System.out.print("Enter year: ");
        int year = scan.nextInt();
        System.out.print("Enter month (1-12): ");
        int month = scan.nextInt();
        System.out.print("Enter day of the month (1-31): ");
        int dayOfMonth = scan.nextInt();

        
        System.out.println("Day of week is " + DayOfWeekString(CalculateDayOfWeek(year, month, dayOfMonth)));
        scan.close();
    }
}
