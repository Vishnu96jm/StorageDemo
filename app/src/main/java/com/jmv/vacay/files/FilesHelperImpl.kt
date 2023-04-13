package com.jmv.vacay.files

import java.io.File
import java.io.FileOutputStream

//directory: File - passing the directory in which you store data
class FilesHelperImpl(private val directory: File) : FilesHelper {

  override fun saveData(fileName: String, data: String) {
    /*By using buildFile, create a reference to the file you want to save. And by using
    * buildOutputStream, you create the stream to output the data you want to write to the file.*/
    val file = buildFile(fileName)
    val fileOutputStream = buildOutputStream(file)

    try {
      fileOutputStream.use {
        /*Using fileOutputStream, you write all the data as a ByteArray to the location.*/
        it.write(data.toByteArray())
      }
    } catch (error: Throwable) {
      error.printStackTrace()
    }
  }

  //By using the parent directory, you get all the children first with listFiles()
  override fun getData(): List<File>  = directory.listFiles()?.toList() ?: emptyList()

  override fun deleteData(fileName: String) {
    //you fetch the file using buildFile function
    val targetFile = buildFile(fileName)

    if (targetFile.exists()) {
      targetFile.delete()
    }
  }

  //Helper functions to build files and to get its input stream
  private fun buildFile(fileName: String): File {
    return File(directory, fileName)
  }

  private fun buildOutputStream(file: File): FileOutputStream {
    return FileOutputStream(file)
  }

}