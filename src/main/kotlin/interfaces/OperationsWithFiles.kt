package interfaces

import java.io.File
import java.io.InputStream

interface OperationsWithFiles {
    fun getHashFromFileByMd5(file: File): String

    fun getContentFromFile(file: File): String

    fun getFileFromFolderByFormat(pathToFolder: String, format: String): File

    fun uploadZipToFolder(inputStream: InputStream, fileName: String, fileFormat: String, pathToFile: String)
}