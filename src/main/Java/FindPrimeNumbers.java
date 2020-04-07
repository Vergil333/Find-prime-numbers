import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FindPrimeNumbers {

    /**
     * Tries to take file path from arguments if any given. Loads if the file exists.
     * Asks for a file path if initial argument is not valid.
     * When valid file is loaded, the prime numbers are printed.
     *
     * @param args program arguments
     */
    public static void main(String[] args) throws IOException {
        String argsFilePath = null;
        InputStream file = null;

        try {
            argsFilePath = args[0];
        } catch (ArrayIndexOutOfBoundsException ignored) {}

        if (argsFilePath == null) {
            System.out.println("No file path in cli arguments.");
            file = FileLoader.loadExcelFile(FileLoader.askForFile());
        } else {
            FileLoader.loadExcelFile(argsFilePath);
        }

        printPrimesFromExcel(file);
    }

    static Boolean isPrimeNumber(Long number) {
        for(int i = 2; i <= number/2; ++i)
        {
            // condition for nonprime number
            if(number % i == 0L) return false;
        }
        return true;
    }

    /**
     * Filter cells and print only prime numbers.
     *
     * @param file InputStream of loaded file
     */
    static void printPrimesFromExcel(InputStream file) throws IOException {
        int defaultColumn = 1;
        int defaultSheet = 0;

        Workbook xlWb = WorkbookFactory.create(file);
        Sheet xlWs = xlWb.getSheetAt(defaultSheet);

        Stream<Row> rowStream = StreamSupport.stream(
        Spliterators.spliteratorUnknownSize(xlWs.rowIterator(), Spliterator.ORDERED),
        false);

        rowStream
                .map(row -> row.getCell(defaultColumn).getStringCellValue())
                .filter(string -> string.matches("^\\d+$"))
                .map(Long::new)
                .filter(FindPrimeNumbers::isPrimeNumber)
                .forEach(System.out::println);
    }

}
