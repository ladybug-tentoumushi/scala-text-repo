//ALDS1_14_A
import scala.io.StdIn.readLine

object Main extends App {
    val t = readLine()
    val p = readLine()
    val matchIndexes = println(search(t, p))

  def search(t:String, p:String): Seq[Int] = {
    var matchIndexes = Seq[Int]()

    for (i <- 0 to t.length - p.length) {
      val partText = t.slice(i, i + p.length)
      if (partText == p) {
        matchIndexes :+= i
      }
      }
     matchIndexes
  }
}