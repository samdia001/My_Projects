package taskeight;
import java.util.Random;


/*This program counts the number of ant's moves over a chessborad,
 since the program is executed completly when the ant visits all the squares of the board.
 the simulations will print randomly ten times and then print the avarage number of steps */

public class Ants {

    /**
     * this part of the program runs the program randomly by calculating the ant's movements on the chessboard and then calculating
     *  the avarage of ants steps
     * @param timesToRunSimulation
     * @param boardSizeX
     * @param boardSizeY
     */
    public static void RunMultipleSimulationsAndDisplayAverage(int timesToRunSimulation, int boardSizeX, int boardSizeY) {

        int totalSteps = 0;

        // runs the simulation specified number of times
        for (int i = 1; i < (timesToRunSimulation + 1); i++) {
            int simulationResult = RunSimulation(boardSizeX, boardSizeY);
            System.out.println("Number of steps in simulation " + i + ": " + simulationResult);
            totalSteps += simulationResult;
        }
         
        // print the avarage number of steps
        System.out.println("Average amount of steps: " + ((totalSteps * 1.0) / timesToRunSimulation));
    }

  
    /* simulates the random steps of an ant on the chessboard,
    and returns the number of steps the ant takes to reach all the squares.
    The steps here mean moving from one square to another, not the steps of the ant*/
    public static int RunSimulation(int boardSizeX, int boardSizeY) {

        Random aRandom = new Random();

        boolean[][] chessBoard = new boolean[boardSizeX][boardSizeY];

        int antX = aRandom.nextInt(boardSizeX) + 1;
        int antY = aRandom.nextInt(boardSizeY) + 1;

        int steps = 0;
        int squaresVisited = 0;

        while(squaresVisited < (boardSizeX * boardSizeY)) {
            
            if (chessBoard[antX - 1][antY - 1] == false) {
                chessBoard[antX - 1][antY - 1] = true;
                squaresVisited++;
            }
        
            while(true) {
                int newDirection = aRandom.nextInt(4);
                if (newDirection == 0 && antX != boardSizeX) {   
                    antX++;
                    break;
                } else if (newDirection == 1 && antX != 1) {    
                    antX--;
                    break;
                } else if (newDirection == 2 && antY != boardSizeY) {   
                    antY++;
                    break;
                } else if (newDirection == 3 && antY != 1) {  
                    antY--;
                    break;
                }
            }
            steps++;
        }

        return steps;
    }

    public static void main(String[] args) {

        System.out.println("Ants");
        System.out.println("====");
        System.out.println("");

        RunMultipleSimulationsAndDisplayAverage(10, 8, 8);
    }
}
