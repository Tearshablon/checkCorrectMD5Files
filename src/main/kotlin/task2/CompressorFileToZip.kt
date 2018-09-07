package task2

import interfaces.CompressFileToZip
import utils.OperationsWithFilesImpl

class CompressorFileToZip : CompressFileToZip {
    override fun compressFilesToZip(pathToFiles: String) {
        val file = OperationsWithFilesImpl().getSingleFileFromFolder(pathToFiles)
        val content = OperationsWithFilesImpl().getContentFromFile(file)
        val stringFile = StringFile(file.name, content)
        val zipFiles = ZipFiles(listOf(stringFile)).compressFiles()
        OperationsWithFilesImpl().uploadZipToFolder(zipFiles, file.name, ".gz", pathToFiles)
    }
}