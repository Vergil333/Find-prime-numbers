import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.concurrent.CancellationException

/**
 * @return sample file loaded from resources
 */
fun getDefaultExcelFile(): InputStream {
    val sampleDataFile = "sample-data.xlsx"
    return ClassLoader.getSystemClassLoader().getResourceAsStream(sampleDataFile)
            ?: throw FileNotFoundException("Failed to load default file.")
}

/**
 * Try to load the file.
 * Return to decision making if file path is invalid.
 *
 * @param filePath path to file
 *
 * @return stream of given file
 */
fun loadExcelFile(filePath: String): InputStream {
    return try { FileInputStream(filePath) } catch (e: FileNotFoundException) {
        System.err.println("File not found: $filePath")
        val decision = whatNext()
        evaluateDecision(decision)
    }
}

/**
 * Reads path to the file.
 */
fun askForFile(): String {
    println("Enter path to file: ")
    return readLine().orEmpty()
}

/**
 * Simple question that will be evaluated depending on the answer.
 * @return just passing the answer
 */
fun whatNext(): String {
    println("Retry? (Yes/No/Default)")
    return readLine().orEmpty()
}

/**
 * Make further actions by user's answer.
 * If answer does not match then assume the answer to be path to file (user error prevention).
 *
 * @param decision answer from previous function
 *
 * @return stream of given file
 */
fun evaluateDecision(decision: String): InputStream {
    var newFilePath = decision
    when {
        decision.equals("No", true) -> throw CancellationException("Canceled by user.")
        decision.equals("Yes", true) -> newFilePath = askForFile()
        decision.equals("Default", true) -> return getDefaultExcelFile()
    }
    return loadExcelFile(newFilePath)
}