import task1.checkEqualsMd5AndCsvFiles

const val PATH_TO_FILES = "src/main/resources/files_task_1/"

fun main(args: Array<String>) {
    println(checkEqualsMd5AndCsvFiles(PATH_TO_FILES))
}