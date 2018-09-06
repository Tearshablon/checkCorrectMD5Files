package task1

import org.apache.commons.codec.digest.DigestUtils
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

const val MD5_FORMAT_NAME = "md5"
const val CSV_FORMAT_NAME = "csv"


fun checkEqualsMd5AndCsvFiles(pathToFiles: String): Boolean {
    val csvFile = getFileFromFolder(pathToFiles, CSV_FORMAT_NAME)
    val md5File = getFileFromFolder(pathToFiles, MD5_FORMAT_NAME)
    val cvsHash = getCsvHash(csvFile)
    val md5Hash = getMd5Hash(md5File)
    return cvsHash == md5Hash
}

fun getCsvHash(file: File) = DigestUtils.md5Hex(file.inputStream())

fun getMd5Hash(file: File) = Scanner(file).useDelimiter("\\n").next().trim()

fun getFileFromFolder(pathToFolder: String, format: String) = Files
        .walk(Paths.get(pathToFolder))
        .filter { !it.toFile().isDirectory }
        .filter { it.toFile().name.contains(format) }
        .map { it.toFile() }
        .findFirst()
        .orElseThrow { throw Exception("Не удалось обнаружить файлы в папке ${format}") }

