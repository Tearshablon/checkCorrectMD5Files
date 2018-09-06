package interfaces

import java.io.File

interface OperationsWithFiles {

    //TODO воткнуть проверку файла на формат
    fun getFilesFromFolder(pathToFolder: String): List<File>

    fun readMd5File(files: List<File>, fileName: String): String

    fun readCvsFile(files: List<File>, fileName: String)
}