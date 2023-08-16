import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Processor {
    public static void main(String[] args) {
    	
        // Checking if the program is given exactly one input when run
        if (args.length != 1) {
            System.out.println("Usage: java Processor <inputFileName>");
            return; // Exiting the program if the argument is not provided correctly
        }

        // Getting the input filename from the command-line argument
        String inputFileName = args[0];
        
        // Naming the output file as "out2.txt"
        String outputFileName = "out2.txt";

        // Setting up resources to read input and write output using try-with-resources
        try (
            FileReader fileReader = new FileReader(inputFileName);
            FileWriter fileWriter = new FileWriter(outputFileName)
        ) {
            int character;
            // Processing each character in the input file
            while ((character = fileReader.read()) != -1) {
                // Ignoring newline characters while copying characters to the output file
                if (character != '\n') {
                    fileWriter.write(character);
                }
            }

            // Adding the special symbol '$' to the output file
            fileWriter.write('$');

        } catch (IOException e) {
            // Printing any errors that occur during reading or writing files
            e.printStackTrace();
        }

        // Displaying the contents of the new output file
        try (Scanner outputScanner = new Scanner(new FileReader(outputFileName))) {
            // Reading and printing each word from the output file
            while (outputScanner.hasNext()) {
                System.out.print(outputScanner.next());
            }
        } catch (IOException e) {
            // Printing any errors that occur during reading or writing files
            e.printStackTrace();
        }
    }
}
