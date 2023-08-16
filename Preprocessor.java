import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Preprocessor {
    public static void main(String[] args) {
        // Checking if the program is given exactly one input when run
        if (args.length != 1) {
            System.out.println("Usage: java Preprocessor <inputFileName>");
            return; // Exiting if the input is not provided correctly
        }

        // Getting the input filename from the command-line argument
        String inputFileName = args[0];
        
        // Choosing the name for the output file as "out1.txt"
        String outputFileName = "out1.txt";

        // Setting up resources to read input and write output using try-with-resources
        try (
            FileReader fileReader = new FileReader(inputFileName);
            FileWriter fileWriter = new FileWriter(outputFileName);
            Scanner scanner = new Scanner(fileReader)
        ) {
        	
            // Processing each line in the input file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                // Calling the processLine method to transform the line and write to output
                processLine(line, fileWriter);
            }

        } catch (IOException e) {
            // Printing any errors that happen during reading or writing files
            e.printStackTrace();
        }

        // Displaying the contents of the output file
        try (Scanner outputScanner = new Scanner(new FileReader(outputFileName))) {
        	
            // Reading and printing each line from the output file
            while (outputScanner.hasNextLine()) {
                System.out.println(outputScanner.nextLine());
            }
        } catch (IOException e) {
            // Printing any errors that happen during reading or writing files
            e.printStackTrace();
        }
    }

    // A method to process a single line and make changes step wise
    private static void processLine(String line, FileWriter fileWriter) {
    	
        // Step 1: Removing empty lines (lines with only spaces or tabs)
        if (!line.trim().isEmpty()) {
        	
            // Step 2: Removing comments (both single-line and multi-line comments)
            line = line.replaceAll("//.*|/\\*(.|\\n)*?\\*/", "");
            
            // Step 3: Removing unnecessary spaces and tabs by trimming and replacing consecutive whitespace with a single space
            line = line.trim().replaceAll("\\s+", " ");
            
            // Step 4: Skipping import statements and annotations by checking the line's beginning
            if (!line.startsWith("import") && !line.startsWith("@")) {
            	
                // Writing the modified line to the output using the provided FileWriter
                try {
                    fileWriter.write(line + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
