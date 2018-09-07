package utils

import interfaces.OperationsWithFiles
import org.apache.commons.codec.digest.DigestUtils
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

class OperationsWithFilesImpl : OperationsWithFiles {

    val CSV_FORMAT_NAME = "csv"
    val MD5_FORMAT_NAME = "md5"

    override fun getHashFromFileByMd5(file: File) = DigestUtils.md5Hex(file.inputStream())

    override fun getContentFromFile(file: File) = Scanner(file).useDelimiter("\\n").next().trim()

    override fun getFileFromFolderByFormat(pathToFolder: String, format: String) = Files
            .walk(Paths.get(pathToFolder))
            .filter { !it.toFile().isDirectory }
            .filter { it.toFile().name.contains(format) }
            .map { it.toFile() }
            .findFirst()
            .orElseThrow { throw Exception("Не удалось обнаружить файл в папке ${format}") }

    override fun getSingleFileFromFolder(pathToFolder: String) = Files
            .walk(Paths.get(pathToFolder))
            .filter { !it.toFile().isDirectory }
            .map { it.toFile() }
            .findFirst()
            .orElseThrow { throw Exception("Не удалось обнаружить файл в папке") }


    override fun checkEqualsMd5AndCsvFiles(pathToFiles: String): Boolean {
        val csvFile = getFileFromFolderByFormat(pathToFiles, CSV_FORMAT_NAME)
        val md5File = getFileFromFolderByFormat(pathToFiles, MD5_FORMAT_NAME)
        val cvsHash = getHashFromFileByMd5(csvFile)
        val md5Hash = getContentFromFile(md5File)
        return cvsHash == md5Hash
    }
}