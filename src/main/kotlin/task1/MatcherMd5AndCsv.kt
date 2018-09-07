package task1

import interfaces.MatchEqualsMd5AndCsv
import utils.OperationsWithFilesImpl

class MatcherMd5AndCsv : MatchEqualsMd5AndCsv {

    val CSV_FORMAT_NAME = "csv"
    val MD5_FORMAT_NAME = "md5"

    override fun checkEqualsMd5AndCsvFiles(pathToFiles: String): Boolean {
        val operationsWithFiles = OperationsWithFilesImpl()
        val csvFile = operationsWithFiles.getFileFromFolderByFormat(pathToFiles, CSV_FORMAT_NAME)
        val md5File = operationsWithFiles.getFileFromFolderByFormat(pathToFiles, MD5_FORMAT_NAME)
        val cvsHash = operationsWithFiles.getHashFromFileByMd5(csvFile)
        val md5Hash = operationsWithFiles.getContentFromFile(md5File)
        return cvsHash == md5Hash
    }
}