package interfaces

import java.io.File
import java.io.FileInputStream

interface OperationsWithFiles {

    //TODO воткнуть проверку файла на формат
    fun getFilesFromFolder(pathToFolder: String): List<File>

    fun readMd5File(files: List<File>, fileName: String): String

    fun readCvsFile(files: List<File>, fileName: String): FileInputStream

    //todo вынести в отдельный класс
    fun checkEqualsMd5AndCsvFiles(pathToFiles: String, fileName: String): Boolean

    fun formHashFileThroughtMd5(pathToFolder: String, fileName: String): String
}