//ALDS1_1_B
import scala.io.StdIn.readLine

object GreatestCommonDivisor extends App {
  val imput = readLine()
  val Array(imputX, imputY) = imput.split(" ")
  val x = BigInt(imputX)
  val y = BigInt(imputY)
  val result = x.gcd(y)
  println(result)
}
