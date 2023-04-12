//ALDS1_14_A
import scala.io.StdIn.readLine

object NaiveStrSearch extends App {
  val t = readLine()
  val p = readLine()
  search(t, p).foreach(println)

  def search(t:String, p:String): Seq[Int] = {
    (0 to t.length - p.length).flatMap { i =>
      val partText = t.slice(i, i + p.length)
      if (partText == p) Some(i) else None
      }
    }
}