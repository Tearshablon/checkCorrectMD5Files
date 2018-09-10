package utils

import interfaces.OperationsWithFiles
import org.apache.commons.codec.digest.DigestUtils
import java.io.File
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

class OperationsWithFilesImpl : OperationsWithFiles {


    override fun getHashFromFileByMd5(file: File) = DigestUtils.md5Hex(file.inputStream())

    override fun getContentFromFile(file: File) = Scanner(file).useDelimiter("\\Z").next().trim()

    override fun getFileFromFolderByFormat(pathToFolder: String, format: String) = Files
            .walk(Paths.get(pathToFolder))
            .filter { !it.toFile().isDirectory }
            .filter { it.toFile().name.contains(format) }
            .map { it.toFile() }
            .findFirst()
            .orElseThrow { throw Exception("Не удалось обнаружить файл в папке ${format}") }

    override fun uploadZipToFolder(inputStream: InputStream, fileName: String, fileFormat: String, pathToFile: String) {
        val buffer = ByteArray(inputStream.available())
        inputStream.read(buffer)
        val targetFile = File(pathToFile + "$fileName$fileFormat")
        com.google.common.io.Files.write(buffer, targetFile)
    }
}