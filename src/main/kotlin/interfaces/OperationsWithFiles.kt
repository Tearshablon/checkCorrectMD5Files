package interfaces

import java.io.File
import java.io.FileInputStream

interface OperationsWithFiles {

    //TODO �������� �������� ����� �� ������
    fun getFilesFromFolder(pathToFolder: String): List<File>

    fun readMd5File(files: List<File>, fileName: String): String

    fun readCvsFile(files: List<File>, fileName: String): FileInputStream

    //todo ������� � ��������� �����
    fun checkEqualsMd5AndCsvFiles(pathToFiles: String, fileName: String): Boolean

    fun formHashFileThroughtMd5(pathToFolder: String, fileName: String): String
}