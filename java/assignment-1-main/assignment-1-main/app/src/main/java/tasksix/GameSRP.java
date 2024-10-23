package tasksix;
import java.util.Scanner;
import java.util.Random;
/**
 * A paper-scissors game is performed between the user and the computer. 
 * The game will be repeated until the player enters a zero and here the
 *  result will be displayed and the program will be closed.
 */
public class GameSRP {

    /* Returns the value of a number for each of the stone,paper and 
    scissors in the form or in the form of a string */
    public static String IntToStringSRP (int choice) {
        if (choice == 1) {
            return "scissor";
        } else if (choice == 2) {
            return "rock";
        } else if (choice == 3) {
            return "paper";
        } else {
            return "";
        }
    }


    public static int DetermineSRPWinner(int playerChoice, int computerChoice) {
        if (playerChoice == 1) { // Player chose scissor
            if (computerChoice == 1) { // Computer chose scissor = Tie
                return 0;
            } else if (computerChoice == 2) { // Computer chose rock = Loss
                return -1;
            } else if (computerChoice == 3) { // Computer chose scissor = Win
                return 1;
            } else {
                return 0;
            }
        } else if (playerChoice == 2) { // Player chose rock
            if (computerChoice == 1) { // Computer chose scissor = Win
                return 1;
            } else if (computerChoice == 2) { // Computer chose rock = Tie
                return 0;
            } else if (computerChoice == 3) { // Computer chose paper = Loss
                return -1;
            } else {
                return 0;
            }
        } else if (playerChoice == 3) { // Player chose paper
            if (computerChoice == 1) { // Computer chose scissor = Loss
                return -1;
            } else if (computerChoice == 2) { // Computer chose rock = Win
                return 1;
            } else if (computerChoice == 3) { // Computer chose paper = Tie
                return 0;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Random aRandom = new Random();

        int playerChoice = 0;
        int playerScore = 0;
        int computerScore = 0;
        int draws = 0;

        do {
            System.out.print("Scissor (1), rock (2), paper (3) or 0 to quit: ");
            playerChoice = scan.nextInt();
            
            if (playerChoice != 0) {

                int computerChoice = aRandom.nextInt(3) + 1;

                int winOrLoss = DetermineSRPWinner(playerChoice, computerChoice);

                /* Announce winner (or tie). */
                if (winOrLoss == -1) {
                    System.out.println("You lost, computer had " + IntToStringSRP(computerChoice) + "!");
                    computerScore++;
                } else if (winOrLoss == 0) {
                    System.out.println("It's a draw!");
                    draws++;
                } else if (winOrLoss == 1) {
                    System.out.println("You won, computer had " + IntToStringSRP(computerChoice) + "!");
                    playerScore++;
                }
            }
            
        } while (playerChoice != 0);

        System.out.println("Score: " + playerScore + " for you " + computerScore + " for the computer " + draws + " (draw).");
        scan.close();
    }
}
