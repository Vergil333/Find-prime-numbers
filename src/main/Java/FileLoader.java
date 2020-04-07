import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

public final class FileLoader {
    private FileLoader() {
    }

    /**
     * @return sample file loaded from resources
     */
    static InputStream getDefaultExcelFile() throws FileNotFoundException {
        String sampleDataFile = "sample-data.xlsx";
        InputStream file;

        try {
            file = ClassLoader.getSystemResourceAsStream(sampleDataFile);
        } catch (Exception e) {
            throw new FileNotFoundException("Failed to load default file.");
        }

        return file;
    }

    /**
     * Try to load the file.
     * Return to decision making if file path is invalid.
     *
     * @param filePath path to file
     *
     * @return stream of given file
     */
    static InputStream loadExcelFile(String filePath) throws FileNotFoundException {
        try {
            return new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            System.out.format("File not found: %s", filePath);
            String decision = whatNext();
            return evaluateDecision(decision);
        }
    }

    /**
     * Reads path to the file.
     */
    static String askForFile() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter path to file: ");
        return input.nextLine();
    }

    /**
     * Simple question that will be evaluated depending on the answer.
     * @return just passing the answer
     */
    static String whatNext() {
        Scanner input = new Scanner(System.in);

        System.out.println("Retry? (Yes/No/Default");
        return input.nextLine();
    }

    /**
     * Make further actions by user's answer.
     * If answer does not match then assume the answer to be path to file (user error prevention).
     *
     * @param decision answer from previous function
     *
     * @return stream of given file
     */
    static InputStream evaluateDecision(String decision) throws FileNotFoundException {
        String newFilePath = decision;

        switch (decision) {
            case "No":
                throw new CancellationException("Canceled by user.");
            case "Yes":
                newFilePath = askForFile();
                break;
            case "Default":
                return getDefaultExcelFile();
        }

        return loadExcelFile(newFilePath);
    }
}
