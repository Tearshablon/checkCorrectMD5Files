package utils

import interfaces.OperationsWithFiles
import java.io.File
import java.io.FileInputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.streams.toList

class OperationsWithFilesImpl : OperationsWithFiles {

    val CSV_FORMAT_NAME = "csv"
    val MD5_FORMAT_NAME = "md5"

    override fun readCvsFile(files: List<File>, fileName: String) {
        FileInputStream(files.first { it.name.contains("$fileName.$CSV_FORMAT_NAME") })
    }

    override fun readMd5File(files: List<File>, fileName: String): String {
        var content: String? = null
        for (file in files) {
            if (file.name == "$fileName.$MD5_FORMAT_NAME") {
                content = Scanner(file).useDelimiter("\\n").next().trim()
            }
        }

        if (content != null) return content else throw Exception("Не удалось обнаружить файл формата .md5 в папке")
    }

    override fun getFilesFromFolder(pathToFolder: String) = Files
            .walk(Paths.get(pathToFolder))
            .filter { !it.toFile().isDirectory }
            .map { it.toFile() }
            .toList()
}