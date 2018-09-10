import task1.MatcherMd5AndCsv
import task2.CompressorFileToZip
import task3.CalculationHashOfZip


const val PATH_TO_FILES_TASK_1 = "src/main/resources/files_task_1/"
const val PATH_TO_FILES_TASK_2 = "src/main/resources/files_task_2/"
const val PATH_TO_FILES_TASK_3 = "src/main/resources/files_task_3/"

fun main(args: Array<String>) {

    val resultCheck = MatcherMd5AndCsv().checkEqualsMd5AndCsvFiles(PATH_TO_FILES_TASK_1)
    println(resultCheck)

    CompressorFileToZip().compressFilesToZip(PATH_TO_FILES_TASK_2)

    CalculationHashOfZip().calculateHashOfZip(PATH_TO_FILES_TASK_3)

}




