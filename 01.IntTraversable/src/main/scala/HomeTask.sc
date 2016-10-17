// Abramov Ivan
//
// TODO Прочитайте содержимое данного файла.
// В случае неудачи верните сообщение соответствующего исключения.

import java.io.{FileNotFoundException, IOException}

def readThisWorksheet(): String = {
  val pathToFile = "GitHub/Scala/01.IntTraversable/src/main/scala/HomeTask.sc"
  val file = scala.io.Source.fromFile(pathToFile)
  var text = ""

  try {
    text = file.mkString
  } catch {
    case e: FileNotFoundException => text = e.getMessage
    case e: IOException => text = e.getMessage
    case e: Exception => text = e.getMessage
  } finally {
    file.close()
  }

  text
}

val text = readThisWorksheet()
