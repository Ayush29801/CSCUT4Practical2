import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FilesInOut {

    public static void main(String[] args) {
        try {
            boolean upperCase = false;
            String[] inputFilePaths = {"input.txt", "inputm.txt"};
            String outputFilePath = "formatted.txt";

            if (args.length == 3 && args[0].equals("-u")) {
                upperCase = true;
                inputFilePaths[0] = args[1];
                inputFilePaths[1] = args[2];
            } else if (args.length == 2) {
                inputFilePaths[0] = args[0];
                inputFilePaths[1] = args[1];
            } else {
                System.out.println("Usage: java FormatNames [-u] input_file1 input_file2 output_file");
                return;
            }

            FileWriter writer = new FileWriter(outputFilePath);

            for (String inputFilePath : inputFilePaths) {
                BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] tokens = line.split(" ");
                    String firstName = capitalize(tokens[0]);
                    String lastName = capitalize(tokens[1]);
                    String dateOfBirth = tokens[2].substring(0, 2) + "/" + tokens[2].substring(2, 4) + "/" + tokens[2].substring(4);

                    if (upperCase) {
                        firstName = firstName.toUpperCase();
                        lastName = lastName.toUpperCase();
                    }

                    writer.write(String.format("%-20s%s\n", firstName + " " + lastName, dateOfBirth));
                }

                reader.close();
            }

            writer.close();
            System.out.println("Formatted file has been generated.");
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }

    private static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
