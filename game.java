//Imports
import java.io.*; 
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


//Gameplay
class GamePlay {

    //Vars
    private int totalGames = 0;
    private int gamesWon = 0;
    private int gamesLost = 0;


    //PlayGame
    public String[] playGame() {

        //Make the scanner
        Scanner scanner = new Scanner(System.in);

        //Get players name
        System.out.println("Enter your name");

        //make name the players name
        String name = scanner.next();

        //Set Vars
        boolean playAgain = true;
        boolean realAnswer;
        int[][] STATE = {
          { 0, 0, 0 },
          { 0, 0, 0 },
          { 0, 0, 0 }
      };


        //While the player wants to play, play
        while (playAgain) {

            //Play Game get gusses
            int tempGuesses = playSingleGame(scanner);

            //Add to total games
            totalGames++;

            //Add to total gusses
            totalGuesses += tempGuesses;

            //Checking to make sure the player didnt lose
            if (tempGuesses == 5) {
                gamesLost++;
            }

            //Seeing if their is a new best game
            if (bestGame == 0 || tempGuesses < bestGame) {
                bestGame = tempGuesses;
            }


            realAnswer = false;


            //Seeing if the player acutally worte the correct stuff
            while (!realAnswer) {

                //Asking the player if they want to play
                System.out.print("Play again? (y/n)");

                //Answer = player input
                String answer = scanner.next();

                //Asking and changing booleans based on answear
                if (answer.equals("y")) {
                    System.out.println("------------------------------------------------------------");
                    System.out.println("---------------------- Starting New Game ----------------------");
                    System.out.println("------------------------------------------------------------");
                    realAnswer = true;
                } else if (answer.equals("n")) {
                    System.out.println("------------------------------------------------------------");
                    System.out.println("--------------------- Thanks For Playing! ------------------");
                    System.out.println("------------------------------------------------------------");
                    realAnswer = true;
                    playAgain = false;
                } else {
                    System.out.println("------------------------------------------------------------");
                    System.out.println("----------------------- Invalid Input ----------------------");
                    System.out.println("------------------------------------------------------------");
                }
            }
        }

        //Getting results 
        String[] results = getResults(name);
        //Returning results
        return results;
    }

    //Playing the acutal game
    private int playSingleGame(Scanner scanner) {

        //Making a random
        Random random = new Random();

        //Setting the random number
        int targetNumber = random.nextInt(100) + 1;

        //Delcaring vars
        int counter = 0;
        int guess = 0;

        //Asking player
        System.out.println("I'm thinking of a number between 1 and 100...");

        //Asking until they lose or get it
        while (guess != targetNumber) {

            //Checking to see if they lost
            if (counter >= 5) {
                System.out.println("Too Many Guesses!");
                return 5;
            }

            //Cheat statement
            System.out.println("");
            System.out.println("IM CHEATING DON'T LOOK AT THIS NUMBER!!! " + targetNumber);
            System.out.println("");

            //Asking for guess
            System.out.print("Your guess? ");

            //Guess = players guess
            guess = scanner.nextInt();

            //Saying if the numbers to much
            if (guess > targetNumber) {
                System.out.println("It's lower.");
            }

            //Saying if its to little
            if (guess < targetNumber) {
                System.out.println("It's higher.");
            }
            //Adding to gusses
            counter++;
        }
        //Speicail statement if they got it in 1
        if (counter == 1) {
            System.out.println("You got it right in 1 guess!");
        } 
        //Else print gusse count
        else {
            System.out.println("You guessed, " + counter + " times!");
        }
        //Returning gusses
        return counter;
    }
    //Getresult method
    private String[] getResults(String name) {

      //print all the stuff
        System.out.println("");
        System.out.println("");
        System.out.println("-------------------------- Results -------------------------");
        System.out.println("Total games     = " + totalGames);
        System.out.println("Total guesses   = " + totalGuesses);
        //Speical thing for Guess game avg to make sure its not 0
        System.out.println("Guesses/game    = " + (totalGames == 0 ? 0 : (double) totalGuesses / totalGames));
        System.out.println("Best game       = " + bestGame);
        System.out.println("Game's Lost     = " + gamesLost);
        System.out.println("------------------------------------------------------------");
        System.out.println("");
        System.out.println("");


        //Making a result list
        String[] results = new String[5];
        //Setting all the results
        results[0] = name;
        results[1] = Integer.toString(totalGames);
        results[2] = Integer.toString(totalGuesses);
        results[3] = Integer.toString(bestGame);
        results[4] = Integer.toString(gamesLost);

        //returning them
        return results;
    }
}