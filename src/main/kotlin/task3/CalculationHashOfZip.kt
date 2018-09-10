package task3

import com.google.common.io.Files
import interfaces.CalculateHashOfZip
import utils.OperationsWithFilesImpl
import java.io.File

class CalculationHashOfZip : CalculateHashOfZip {
    override fun calculateHashOfZip(pathToFile: String) {
        val file = OperationsWithFilesImpl().getFileFromFolderByFormat(pathToFile, ".gz")
        val md5 = OperationsWithFilesImpl().getHashFromFileByMd5(file)
        val targetFile = File(pathToFile + "${file.name.substringBefore(".")}.md5")
        Files.write(md5.toByteArray(), targetFile)
    }
}