package task2

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class ZipFiles(val stringFiles: List<StringFile>) {

    fun zip(): InputStream {
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
            throw RuntimeException(ioe)
        }

        return ByteArrayInputStream(baos.toByteArray())
    }
}