package task1

import org.apache.commons.codec.digest.DigestUtils
import java.io.File
import java.io.FileInputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.streams.toList

const val MD5_FORMAT_NAME = ".md5"
const val CSV_FORMAT_NAME = ".csv"

fun checkEquelsMd5AndCsvFiles(pathToFiles: String): Boolean {
    val files = getFiles(pathToFiles)

    if (files.isEmpty()) {
        throw Exception("Не удалось обнаружить файлы в папке ${pathToFiles}")
    }

    val cvsHash = readMd5File(files)
    val md5Hash = DigestUtils.md5Hex(readCvsFile(files))

    return cvsHash.equals(md5Hash)
}

fun readMd5File(files: List<File>): String {
    var content: String? = null
    for (file in files) {
        if (file.name.contains(MD5_FORMAT_NAME)) {
            content = Scanner(file).useDelimiter("\\n").next().trim()
        }
    }

    if (content != null) return content else throw Exception("Не удалось обнаружить файл формата ${MD5_FORMAT_NAME} в папке")
}

fun readCvsFile(files: List<File>) = FileInputStream(files.first { it.name.contains(CSV_FORMAT_NAME) })

//TODO воткнуть проверку файла на формат
fun getFiles(pathTo: String) = Files
        .walk(Paths.get(pathTo))
        .filter { !it.toFile().isDirectory }
        .map { it.toFile() }
        .toList()
