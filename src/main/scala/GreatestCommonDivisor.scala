//ALDS1_1_B
import scala.io.StdIn.readLine

object GreatestCommonDivisor extends App {
  val imput = readLine()
  val Array(x, y) = imput.split(" ").map(BigInt(_))
  val result = x.gcd(y)
  println(result)
}
