package task1

import org.apache.commons.codec.digest.DigestUtils
import java.io.File
import java.io.FileInputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.streams.toList

const val MD5_FORMAT_NAME = "md5"
const val FILE_NAME = "TemplateImportEmpl"
const val CSV_FORMAT_NAME = "csv"


fun checkEqualsMd5AndCsvFiles(pathToFiles: String): Boolean {
    val filesFromFolder = getFilesFromFolder(pathToFiles)

    if (filesFromFolder.isEmpty()) {
        throw Exception("Не удалось обнаружить файлы в папке ${pathToFiles}")
    }

    val cvsHash = readMd5File(filesFromFolder, FILE_NAME)
    val md5Hash = DigestUtils.md5Hex(readCvsFile(filesFromFolder))

    return cvsHash == md5Hash
}

fun readMd5File(files: List<File>, fileName: String, fileFormat: String = MD5_FORMAT_NAME): String {
    var content: String? = null
    for (file in files) {
        if (file.name == "$fileName.$fileFormat") {
            content = Scanner(file).useDelimiter("\\n").next().trim()
        }
    }

    if (content != null) return content else throw Exception("Не удалось обнаружить файл формата $fileFormat в папке")
}

fun readCvsFile(files: List<File>) = FileInputStream(files.first { it.name.contains(CSV_FORMAT_NAME) })

//TODO воткнуть проверку файла на формат
fun getFilesFromFolder(pathToFolder: String) = Files
        .walk(Paths.get(pathToFolder))
        .filter { !it.toFile().isDirectory }
        .map { it.toFile() }
        .toList()
