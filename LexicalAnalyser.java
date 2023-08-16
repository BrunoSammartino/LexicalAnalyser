import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LexicalAnalyser {
    public static void main(String[] args) {
        // Checking if the program is given exactly one input when run
        if (args.length != 1) {
            System.out.println("Usage: java LexicalAnalyzer <input_filename>");
            return; // Exiting the program if the argument is not provided correctly
        }

        // Getting the input filename from the command-line argument
        String inputFilename = args[0];

        // Setting up resources to read input using try-with-resources
        try (
            FileReader fileReader = new FileReader(inputFilename);
            Scanner scanner = new Scanner(fileReader)
        ) {
            // Processing each line in the input file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Skipping empty lines
                if (line.isEmpty()) {
                    continue;
                }

                // Splitting each line into smaller parts based on spaces and symbols
                String[] lexemes = line.split("\\s+|(?=[{}()\\[\\],;])|(?<=[{}()\\[\\],;])");

                // Processing each small part (lexeme)
                for (String lexeme : lexemes) {
                    // Skipping empty lexemes
                    if (lexeme.isEmpty()) {
                        continue;
                    }

                    // Performing further analysis or validation on each lexeme
                    System.out.println("Lexeme: " + lexeme);
                }
            }
        } catch (IOException e) {
            // Printing an error message if a problem occurs during reading or writing files
            System.err.println("Error Here: " + e.getMessage());
        }
    }
}
