package utils

import interfaces.OperationsWithFiles
import org.apache.commons.codec.digest.DigestUtils
import java.io.File
import java.io.FileInputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.streams.toList

class OperationsWithFilesImpl : OperationsWithFiles {

    val CSV_FORMAT_NAME = "csv"
    val MD5_FORMAT_NAME = "md5"
    val FILE_NAME = "TemplateImportEmpl"

    override fun readCvsFile(files: List<File>, fileName: String) = FileInputStream(files
            .first { it.name.contains("$fileName.$CSV_FORMAT_NAME") })


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
//            .filter{it.fileName.equals("TemplateImportEmpl.md5")}
            .map { it.toFile() }
            .toList()

    override fun checkEqualsMd5AndCsvFiles(pathToFolder: String, fileName: String): Boolean {
        val filesFromFolder = getFilesFromFolder(pathToFolder)
        val cvsHash = readMd5File(filesFromFolder, fileName)
        val md5Hash = formHashFileThroughtMd5(pathToFolder, fileName)

        return cvsHash == md5Hash
    }

    override fun formHashFileThroughtMd5(pathToFolder: String, fileName: String) = DigestUtils.md5Hex(readCvsFile(getFilesFromFolder(pathToFolder), fileName))
}