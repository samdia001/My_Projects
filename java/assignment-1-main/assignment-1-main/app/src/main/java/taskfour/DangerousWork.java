
package taskfour;
/**
 * this program asks the user about the salary he wants 
 * and then calculates the number of days that this person that this person must work overtime
 */
import java.util.Scanner;// Import the Scanner class
public class DangerousWork {
  public static void main(String[] args) {
    // start from day 1
    int day = 1;
    // ask the use to input the salary 
    Scanner scan  = new Scanner( System.in );
    //print the question
    System.out.print("How much would you like to earn? ");
  
    float money = scan.nextFloat();
    // while loop to calculat the monay 
    while(money > 0.01){
      money=money/2;

      day++;
    }
    //If the money is not equal to 0.01
    
    if(money!=0.01){ day = day -1;}
    //validate the user after 30 days
    if(day>=30){System.out.println("You won’t survive, you need " + day + " days!");}
    else{
    //Print the days that the user needs
    System.out.printf("You will have your money in %d days.",day );
    scan.close();
    }
  }
}
