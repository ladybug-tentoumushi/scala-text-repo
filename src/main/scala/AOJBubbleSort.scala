//ALDS1_2_A
import scala.io.StdIn.readLine

object AOJBubbleSort extends App {
  val n:Int = readLine().toInt
  val nums: List[Int] = readLine.split(" ").toList.map(_.toInt)
  val result = bubbleSort(nums)
  println(result)

  def bubbleSort(nums: List[Int]): List[Int] = nums match {
    case Nil => Nil
    case x :: Nil => List(x)
    case x :: y  :: rest =>
      if (x > y) y :: bubbleSort(x :: rest)
      else x :: bubbleSort(y :: rest)
  }

}
