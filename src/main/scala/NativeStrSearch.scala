//ALDS1_14_A
//import scala.io.StdIn.readLine

object Main {
  def main(args: Array[String]): Unit = {
    if (args.length < 2) {
      println("Usage: NativeStrSearch <T> <P>")
      System.exit(1)
    }
    val t: String = args(0)
    val p: String = args(1)
    nativeStrSearch(t, p)
  }

  def nativeStrSearch(t: String, p: String): Unit = {
    val tLength = t.length
    val pLength = p.length
    val result: List[Int] = (0 to tLength - pLength).flatMap {
      case i if t.substring(i, i + pLength) == p => Some(i)
      case _ => None
    }.toList
    result.foreach(println)
  }
}