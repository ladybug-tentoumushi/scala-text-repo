//ALDS1_1_C
import org.graalvm.compiler.graph.Node.Input

import scala.io.StdIn.readLine

object AOJPrimeNumbers extends App {
  val n = readLine().toInt
  val inputs = Iterator.continually(readLine()).take(n).toList.map(_.toInt)
  val result = countNumbers(inputs)
  println(result)

  def primeNumber(input: Int): Boolean = input match {
    case x if x <= 1 => false
    case 2 | 3 => true
    case x if x % 2 == 0 || x % 3 == 0 => false
    case x =>
      val limit = math.sqrt(x).toInt
      def checkNumber(i: Int): Boolean =
        if (i > limit) true
        else if (x % i == 0) false
        else checkNumber(i + 2)
      checkNumber(5)
  }

  def countNumbers(input: List[Int]): Int = {
    inputs.count(primeNumber)
  }

}
