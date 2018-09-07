package interfaces

import java.io.File

interface OperationsWithFiles {
    fun getHashFromFileByMd5(file: File) :String

    fun getContentFromFile(file: File) :String

    fun getFileFromFolderByFormat(pathToFolder: String, format: String) :File

    fun getSingleFileFromFolder(pathToFolder: String) :File

    fun checkEqualsMd5AndCsvFiles(pathToFiles: String): Boolean
}