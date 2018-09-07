import com.google.common.io.Files
import task2.StringFile
import task2.ZipFiles
import utils.OperationsWithFilesImpl
import java.io.File
import java.io.InputStream

const val PATH_TO_FILES_TASK_1 = "src/main/resources/files_task_1/"
const val PATH_TO_FILES_TASK_2 = "src/main/resources/files_task_2/"

fun main(args: Array<String>) {
    val resultCheck = OperationsWithFilesImpl().checkEqualsMd5AndCsvFiles(PATH_TO_FILES_TASK_1)
    println(resultCheck)

    val file = OperationsWithFilesImpl().getSingleFileFromFolder(PATH_TO_FILES_TASK_2)
    val name = file.name
    val content = OperationsWithFilesImpl().getContentFromFile(file)
    val stringFile = StringFile(name, content)
    val zipFiles = ZipFiles(listOf(stringFile)).compressFilesToZip()
    upload(zipFiles, name, ".gz")
}


fun upload(inputStream: InputStream, fileName: String, fileFormat: String) {
    val buffer = ByteArray(inputStream.available())
    inputStream.read(buffer)
    val targetFile = File(PATH_TO_FILES_TASK_2 + "$fileName$fileFormat")
    Files.write(buffer, targetFile)
}



