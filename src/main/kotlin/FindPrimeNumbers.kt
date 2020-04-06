import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.InputStream

/**
 * Filter cells and print only prime numbers.
 *
 * @param file InputStream of loaded file
 * @param columnNumber default 1, optional
 * @param sheetNumber default first sheet, optional
 */
fun printPrimesFromExcel(file: InputStream, columnNumber: Int = 1, sheetNumber: Int = 0) {
    val xlWb = WorkbookFactory.create(file)
    val xlWs = xlWb.getSheetAt(sheetNumber)

    xlWs.rowIterator().asSequence()
            .mapNotNull { row -> row.getCell(columnNumber).stringCellValue } // row to string
            .filter { string -> """^\d+${'$'}""".toRegex().matches(string) } // filter only whole numbers
            .mapNotNull { string -> string.toLongOrNull() } // string to long
            .filter { number -> isPrimeNumber(number) } // filter only prime numbers
            .forEach { number -> println(number) }
}

fun isPrimeNumber(number: Long): Boolean {
    for (i in 2..number / 2) {
        if (number.rem(i) == 0L) {
            return false
        }
    }
    return true
}

/**
 * Tries to take file path from arguments if any given. Loads if the file exists.
 * Asks for a file path if initial argument is not valid.
 * When valid file is loaded, the prime numbers are printed.
 *
 * @param args program arguments
 */
fun main(args: Array<String>) {

    // try to get path from args
    val argsFilePath: String? = try {args[0]} catch (e: ArrayIndexOutOfBoundsException) {null}

    // check args
    val file: InputStream = if (argsFilePath.isNullOrBlank()) {
        println("No file path in cli arguments.")
        loadExcelFile(askForFile())
    } else {
        loadExcelFile(argsFilePath)
    }

    printPrimesFromExcel(file)
}