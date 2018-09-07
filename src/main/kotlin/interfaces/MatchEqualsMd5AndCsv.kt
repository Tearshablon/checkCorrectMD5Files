package interfaces

interface MatchEqualsMd5AndCsv {
    fun checkEqualsMd5AndCsvFiles(pathToFiles: String): Boolean
}