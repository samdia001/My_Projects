package taskthree;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;
/**
 * In this program, each of the computer players responds and then decides whether to throw another start. 
 * The goal of this game is to get a total score close to nine, but not reaching 10.
 */
public class Nine {


    public static String DetermineWinner(int playerscore, int computerscore) {

        String theWinner = "";

       
        if (playerscore >= 10) {
            System.out.println("Player is fat!");
            theWinner = "Computer";
            // Check if computer is also fat
            if (computerscore >= 10) {
                System.out.println("Computer is fat!");
                theWinner = "Tie";
            }
        }

        // Check if computer is fat
        if (theWinner.equals("") && computerscore >= 10) {
            System.out.println("Computer is fat!");
            theWinner = "Player";
        }

        // Otherwise, compare score and determine winner
        if (theWinner.equals("")) {
            int playerScore = Math.abs(9 - playerscore);
            int computerScore = Math.abs(9 - computerscore);
            if (playerScore < computerScore) {
                theWinner = "Player";
            } else if (playerScore > computerScore) {
                theWinner = "Computer";
            } else if (playerScore == computerScore) {
                theWinner = "Tie";
            }
        }

        return theWinner;
    }

    /* Announces winner (or tie) by printing to the terminal */
    public static void AnnounceWinner(String theWinner) {
        
        if (theWinner.equals("Player")) {
            System.out.println("You won!");
        } else if (theWinner.equals("Computer")) {
            System.out.println("You lost!");
        } else if (theWinner.equals("Tie")) {
            System.out.println("It's a tie!");
        }
    }

    public static void main(String[] args) {

        Random random = new Random();

        int  dice1ForPlayer = 0;
        int dice2ForPlayer = 0;
        int  dice1ForComputer = 0;
        int dice2ForComputer = 0;

        Scanner scan = new Scanner(System.in);
        
        System.out.println("Playing a game");
        System.out.println("=================");
        System.out.println("");
        System.out.print("Ready to play? (Y/N) ");
        String readyToPlay = scan.nextLine();

        // Ask if player is ready to play
        if (readyToPlay.toLowerCase().equals("y") || readyToPlay.toLowerCase().equals("yes")) {
            dice1ForPlayer= random.nextInt(6) + 1;
            System.out.println("You rolled " + dice1ForPlayer);
            System.out.print("Would you like to roll again? (Y/N) ");
            String rollAgain = scan.nextLine();

            // Ask if player wants to roll another die
            if (rollAgain.toLowerCase().equals("y") || rollAgain.toLowerCase().equals("yes")) {
                dice2ForPlayer = random.nextInt(6) + 1;
                System.out.println("You rolled " + dice2ForPlayer + " and in total you have " + (dice1ForPlayer + dice2ForPlayer));
            }

            // Computer rolls first die
            dice1ForComputer = random.nextInt(6) + 1;
            System.out.println("The computer rolled " + dice1ForComputer);

            // Computer decides wether to roll second die
            if (dice1ForComputer <= 4) {
                dice2ForComputer = random.nextInt(6) + 1;
                System.out.println("The computer rolls again and gets " + dice2ForComputer + " in total " + (dice1ForComputer + dice2ForComputer));
            }

            String winner = DetermineWinner((dice1ForPlayer + dice2ForPlayer), (dice1ForComputer + dice2ForComputer));
            AnnounceWinner(winner);
        }

        scan.close();
    }
}
