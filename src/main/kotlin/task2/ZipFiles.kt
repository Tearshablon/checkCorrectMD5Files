package task2

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

//const val PATH_TO_FILES_TASK_2 = "src/main/resources/files_task_2/test.gz"

class ZipFiles(val stringFiles: List<StringFile>) {

    fun compressFilesToZip(): InputStream {
        val baos = ByteArrayOutputStream()
        try {
            ZipOutputStream(baos).use { zos ->
                for (stringFile in stringFiles) {
                    val entry = ZipEntry(stringFile.name)
                    zos.putNextEntry(entry)
                    zos.write(stringFile.content.toByteArray())
                    zos.closeEntry()
                }
            }
        } catch (ioe: IOException) {
            throw Exception("Ошибка в сжатии файлов", ioe)
        }

        return ByteArrayInputStream(baos.toByteArray())
    }
}