package taskten;
import java.util.Scanner;

/**
 * This program prints a calendar for a specific month after the year 1800, 
 * through that the user selects the year and month, and then the program 
 * can find the correct calendar
 */
public class PrintCalendar {
    // determine the days of a specific month and the leap year as well
    public static int AmountOfDaysOfMonth(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7
                || month == 8 || month == 10 || month == 12) {
            return 31;
        } else if (month == 2) {
            if (YearIsLeapYear(year)) {
                return 29;
            }
            else {
                return 28;
            }
        } else {
            return 30;
        }
    }
     
    // use boolean to determine whether a year is a leap or not
    public static boolean YearIsLeapYear(int year) {
        if (!(year % 4 == 0)) {
            return false;
        } else if (!(year % 100 == 0)) {
            return true;
        } else if (!(year % 400 == 0)) {
            return false;
        } else {
            return true;
        }
    }

    // determine the name of a month based o n its number
    public static String MonthIntToString(int month) {
        String[] months = {
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        };
        return months[month - 1];
    }

    // creats an int array to represent a specific month
    public static int[][] CreateCalendarArray(int dayOfWeekOf1st, int amountOfDaysOfMonth) {

        boolean isSettingNumbersToArray = false;
        int dayCounter = 1;
        int[][] calendar = new int[7][6];

        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 7; x++) {
                if (y == 0 && x == dayOfWeekOf1st) {
                    isSettingNumbersToArray = true;
                }
                if (isSettingNumbersToArray) {
                    calendar[x][y] = dayCounter;
                    dayCounter++;
                    if (dayCounter > amountOfDaysOfMonth) {
                        isSettingNumbersToArray = false;
                    }
                }
            }
        }

        return calendar;
    }

    
    public static String PadStringWithLeadingChars(String s, char c, int stringLength) {
        while (s.length() < stringLength) {
            s = c + s;
        }

        return s;
    }

    //  Takes a 2D array representing a month calendar
    public static void PrintCalendarArray(int[][] calendar) {

        System.out.println("-----------------------------");
        System.out.println(" Mon Tue Wed Thu Fri Sat Sun");

        for (int y = 0; y < 6; y++) {
            if (y == 5 && calendar[0][y] == 0) {
                break;
            } else {
                for (int x = 0; x < 7; x++) {
                    String s = "";
                    if (calendar[x][y] != 0) {
                        s = String.valueOf(calendar[x][y]);
                    }
                    s = PadStringWithLeadingChars(s, ' ', 4);
                    System.out.print(s);
                }
                System.out.println("");
            }
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        
        System.out.print("Enter a year after 1800: ");
        int year = scan.nextInt();
        System.out.print("Enter month (1-12): ");
        int month = scan.nextInt();

        
        int dayOfWeekOf1st = ((taskseven.DayOfWeek.CalculateDayOfWeek(year, month, 1) - 2) + 7) % 7;
        int amountOfDaysOfMonth = AmountOfDaysOfMonth(year, month);

        
        int[][] calendar = CreateCalendarArray(dayOfWeekOf1st, amountOfDaysOfMonth);

        System.out.println(MonthIntToString(month) + " " + year);
        PrintCalendarArray(calendar);

        scan.close();
    }
}
