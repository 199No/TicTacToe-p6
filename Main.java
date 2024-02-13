/*

2-D Array sneak attack!

Can you write a method that will look at STATE
and return true if the '2' team would win 
at tic-tac-toe? ba

*/

import java.util.Scanner;
import com.sun.jdi.ReferenceType;

class Main {

  public static final int[][] STATE = {
      { 0, 0, 0 },
      { 0, 0, 0 },
      { 0, 0, 0 }
  };
  public static String[] resultsPlayer1 = {"","","",""};
  public static String[] resultsPlayer2 = {"","","",""};

  public static void main(String[] args) throws Exception {

    // Welcome bullcrap

    System.out.println("Welcome a 0 is a free space a 1 Stands for player one and 2 For player 2!");
    System.out.println("");
    System.out.println("This is the board in letters:");

    System.out.println("tl, tm, tr");
    System.out.println("ml, mm, mr");
    System.out.println("bl, bm, br");

    System.out.println("");
    System.out.println("");
    System.out.println("Current State is: ");
    System.out.println("");
    System.out.println(STATE[0][0] + " " + STATE[0][1] + " " + STATE[0][2]);
    System.out.println(STATE[1][0] + " " + STATE[1][1] + " " + STATE[1][2]);
    System.out.println(STATE[2][0] + " " + STATE[2][1] + " " + STATE[2][2]);
    System.out.println("");
    System.out.println("");

    // Playing acutal game

    int turn = 2;
    boolean boardIsNotFull = true;
    Scanner scanner = new Scanner(System.in);

    while (boardIsNotFull) {

      // Swapping players turn
      turn = (turn == 1) ? 2 : 1;

      System.out.println("Player " + turn + " where do you want to go?");
      String ans = scanner.next();

      //Changing state depending on 
      if (ans.contains("tl")&& STATE[0][0] == 0) {
        STATE[0][0] = turn;
      }
      if (ans.contains("tm") && STATE[0][1] == 0) {
        STATE[0][1] = turn;
      }
      if (ans.contains("tr") && STATE[0][2] == 0) {
        STATE[0][2] = turn;
      }
      if (ans.contains("ml") && STATE[1][0] == 0) {
        STATE[1][0] = turn;
      }
      if (ans.contains("mm") && STATE[1][1] == 0) {
        STATE[1][1] = turn;
      }
      if (ans.contains("mr") && STATE[1][2] == 0) {
        STATE[1][2] = turn;
      }
      if (ans.contains("bl") && STATE[2][0] == 0) {
        STATE[2][0] = turn;
      }
      if (ans.contains("bm") && STATE[2][1] == 0) {
        STATE[2][1] = turn;
      }
      if (ans.contains("br") && STATE[2][2] == 0) {
        STATE[2][2] = turn;
      }

      


      System.out.println("");
      System.out.println("");
      System.out.println("Current State is: ");
      System.out.println("");
      System.out.println(STATE[0][0] + " " + STATE[0][1] + " " + STATE[0][2]);
      System.out.println(STATE[1][0] + " " + STATE[1][1] + " " + STATE[1][2]);
      System.out.println(STATE[2][0] + " " + STATE[2][1] + " " + STATE[2][2]);
      System.out.println("");
      System.out.println("");


      if (checkForWinnerPlayer1(STATE)){
        System.out.println("Player 1 wins!");
        boardIsNotFull = false;
        break; // Exiting the while loop if player 1 wins
      }
      if (checkForWinnerPlayer2 (STATE)){
        System.out.println("Player 2 wins!");
        boardIsNotFull = false;
        break; // Exiting the while loop if player 2 wins
      }
      boardIsNotFull = IsBoardNotFilled(STATE);
      if (!boardIsNotFull) {
        System.out.println("The board is filled!");
        break; // Exiting the while loop if the board is filled
      }
    }

    //////////////////////////////// Wining Stuff //////////////////////////////// 

    System.out.println("Board is filled, printing who won!");
    System.out.println("");

    //Updating scores
    System.out.println("Player 1 what is your name?");
    resultsPlayer1[0] = scanner.next();
    resultsPlayer1[1] = "1";
    resultsPlayer1[2] = "0";
    resultsPlayer1[3] = "0";
    System.out.println("Player 2 what is your name?");
    resultsPlayer2[0] = scanner.next();
    resultsPlayer2[1] = "1";
    resultsPlayer2[2] = "0";
    resultsPlayer2[3] = "0";


    
    // Printing who won
    int player1Wins = 0;
    int player2Wins = 0;
    if(checkForWinnerPlayer1(STATE)){
      System.out.println("Player 1 won");
      player1Wins += 1;
      resultsPlayer1[3] = "1";
      resultsPlayer2[2] = "1";
    }
    if(checkForWinnerPlayer2(STATE)){
      System.out.println("Player 2 won");
      player2Wins += 1;
      resultsPlayer1[2] = "1";
      resultsPlayer2[3] = "1";
    }



    //wirting the results
    FileHandler fileHandler1 = new FileHandler(resultsPlayer1);
    FileHandler fileHandler2 = new FileHandler(resultsPlayer2);
  }

  public static boolean checkForWinnerPlayer2(int[][] state) {

    boolean trob = false;
    int inARow = 0;
    int upARow = 0;
    int crossARow = 0;

    // Horinzatal wins
    for (int t = 0; t < state[0].length; t++) {

      for (int i = 0; i < state[t].length; i++) {
        if (state[t][i] == 2) {
          inARow += 1;
        }
      }
      if (inARow == 3) {
        trob = true;
      }
      inARow = 0;
    }

    // Vertical wins
    for (int y = 0; y < state[0].length; y++) {
      for (int q = 0; q < state[0].length; q++) {
        if (state[q][y] == 2) {
          upARow += 1;
        }
      }
      if (upARow == 3) {
        trob = true;
      }
      upARow = 0;
    }

    // Cross wins
    if (state[0][0] == 2) {
      crossARow += 1;
    }
    if (state[1][1] == 2) {
      crossARow += 1;
    }
    if (state[2][2] == 2) {
      crossARow += 1;
    }
    if (crossARow == 3) {
      trob = true;
    }
    crossARow = 0;

    if (state[0][2] == 2) {
      crossARow += 1;
    }
    if (state[1][1] == 2) {
      crossARow += 1;
    }
    if (state[2][0] == 2) {
      crossARow += 1;
    }
    if (crossARow == 3) {
      trob = true;
    }

    return trob;

  }

  public static boolean checkForWinnerPlayer1(int[][] state) {

    boolean trob = false;
    int inARow = 0;
    int upARow = 0;
    int crossARow = 0;

    // Horinzatal wins
    for (int t = 0; t < state[0].length; t++) {

      for (int i = 0; i < state[t].length; i++) {
        if (state[t][i] == 1) {
          inARow += 1;
        }
      }
      if (inARow == 3) {
        trob = true;
      }
      inARow = 0;
    }

    // Vertical wins
    for (int y = 0; y < state[0].length; y++) {
      for (int q = 0; q < state[0].length; q++) {
        if (state[q][y] == 1) {
          upARow += 1;
        }
      }
      if (upARow == 3) {
        trob = true;
      }
      upARow = 0;
    }

    // Cross wins
    if (state[0][0] == 1) {
      crossARow += 1;
    }
    if (state[1][1] == 1) {
      crossARow += 1;
    }
    if (state[2][2] == 1) {
      crossARow += 1;
    }
    if (crossARow == 3) {
      trob = true;
    }
    crossARow = 0;

    if (state[0][2] == 1) {
      crossARow += 1;
    }
    if (state[1][1] == 1) {
      crossARow += 1;
    }
    if (state[2][0] == 1) {
      crossARow += 1;
    }
    if (crossARow == 3) {
      trob = true;
    }

    return trob;

  }

  public static boolean IsBoardNotFilled(int[][] state) {

    if (state[0][0] == 0) {
      return true;
    }
    if (state[0][1] == 0) {
      return true;
    }
    if (state[0][2] == 0) {
      return true;
    }
    if (state[1][0] == 0) {
      return true;
    }
    if (state[1][1] == 0) {
      return true;
    }
    if (state[1][2] == 0) {
      return true;
    }
    if (state[2][0] == 0) {
      return true;
    }
    if (state[2][1] == 0) {
      return true;
    }
    if (state[2][2] == 0) {
      return true;
    }

    return false;
  }

}