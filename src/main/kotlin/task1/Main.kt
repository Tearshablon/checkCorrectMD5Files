package task1

const val PATH_TO_FILES = "src/main/resources/files/"

fun main(args: Array<String>) {
    println(checkEquelsMd5AndCsvFiles(PATH_TO_FILES))
}