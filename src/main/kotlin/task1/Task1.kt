package task1

import org.apache.commons.codec.digest.DigestUtils
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.streams.toList

const val PATH_TO_FILES = "src/main/resources/files/"
const val CSV_FORMAT_NAME = ".csv"
const val MD5_FORMAT_NAME = ".md5"

fun main(args: Array<String>) {
    val files = getFiles(PATH_TO_FILES)

    if (files.isEmpty()) {
        throw Exception("Не удалось обнаружить файлы в папке ${PATH_TO_FILES}")
    }

    val contentOfFormatMD5 = getContentFromFilesByFormat(files, CSV_FORMAT_NAME)
    println(getMD5Hex(contentOfFormatMD5))
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