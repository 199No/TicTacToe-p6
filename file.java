//Imports
import java.io.*; 
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

//Filehandler stuff
class FileHandler {

    //vars
    private String name;
    private int totalWins;
    private int totalLosses;
    private int totalGames;
  
    //Thorws all exceptions lol
    public FileHandler(String[] results) throws Exception {

        //Setting all vars based on input
        this.name = results[0];
        this.totalWins = Integer.parseInt(results[1].trim());
        this.totalLosses = Integer.parseInt(results[2].trim());
        this.totalGames = Integer.parseInt(results[3].trim());

      
        // Remove old saves and add old scores
        updatePlayerScores();

        // Write player results to the data file
        writeResultsToFile();
    }


    //Update scores method, throws IO
  private void updatePlayerScores() throws IOException {
      //makes files
      File inputFile = new File("data.csv");
      File tempFile = new File("temp.csv");

      //Found this Buff Reader thing pretty helpful
      // Gets rid of fileWriter and use Buffed instead
      try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
           BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

          String currentLine;

          //reads each line 
          while ((currentLine = reader.readLine()) != null) {
              String[] values = currentLine.split(",");

              //checks if name = 
              if (values[0].trim().equals(name)) {
                
                  //add old scores to the current game scores
                  totalWins += Integer.parseInt(values[1].trim());
                  totalLosses += Integer.parseInt(values[2].trim());
                  totalGames += Integer.parseInt(values[3].trim());


              } else {
                  //if its a new player write the line to the temp file
                  writer.write(currentLine + System.getProperty("line.separator"));
              }
          }
      }

      //renames the temp file to the original file
      tempFile.renameTo(inputFile);
  }


    //Write results throws Io
    private void writeResultsToFile() throws IOException {

        //Make file
        File file = new File("data.csv");

        //Make file writer
        FileWriter fileWriter = new FileWriter(file, true);

        //Writes players stuff
        fileWriter.write(name + ", " + totalWins + ", " + totalLosses + ", " + totalGames + "\n");

        //Close file writer bec I have to
        fileWriter.close();
    }
}