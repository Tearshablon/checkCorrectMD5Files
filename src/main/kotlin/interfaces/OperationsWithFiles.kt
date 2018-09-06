package interfaces

import java.io.File

interface OperationsWithFiles {

    //TODO �������� �������� ����� �� ������
    fun getFilesFromFolder(pathToFolder: String): List<File>

    fun readMd5File(files: List<File>, fileName: String): String

    fun readCvsFile(files: List<File>, fileName: String)
}