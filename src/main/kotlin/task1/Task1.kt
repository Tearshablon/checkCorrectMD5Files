package task1

import org.apache.commons.codec.digest.DigestUtils
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.streams.toList

const val CSV_FORMAT_NAME = ".csv"
const val MD5_FORMAT_NAME = ".md5"

fun checkEquelsMd5AndCsvFiles(pathToFiles: String): Boolean {
    val files = getFiles(pathToFiles)

    if (files.isEmpty()) {
        throw Exception("Не удалось обнаружить файлы в папке ${pathToFiles}")
    }

    val contentOfFormatMD5 = getContentFromFilesByFormat(files, CSV_FORMAT_NAME)
    println(getMD5Hex(contentOfFormatMD5))

    return false
}

fun getContentFromFilesByFormat(files: List<File>, format: String): String {
    var content: String? = null
    for (file in files) {
        if (file.name.contains(format)) {
            content = Scanner(file).useDelimiter("\\Z").next()
        }
    }

    if (content != null) return content else throw Exception("Не удалось обнаружить файлы формата ${format} в папке")
}

//TODO воткнуть проверку файла на формат
fun getFiles(pathTo: String) = Files
        .walk(Paths.get(pathTo))
        .filter { !it.toFile().isDirectory }
        .map { it.toFile() }
        .toList()

fun getMD5Hex(fileContext: String) = DigestUtils.md5Hex(fileContext)