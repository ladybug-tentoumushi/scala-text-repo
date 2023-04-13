//ALDS1_1_D
import scala.io.StdIn.readLine

object AOJMaximumProfit extends App {
  val n = readLine().toInt
  val nums = Iterator.continually(readLine()).take(n).toList.map(_.toInt)
  val result = maxDiff(nums)
  println(result)

  def maxDiff(nums: List[Int]): Int = {
    if (nums.isEmpty || nums.length == 1) {
      throw new IllegalArgumentException("error")
    } else {
      nums.tail.foldLeft(nums.head, nums.tail.head - nums.head) {
        case ((min, maxDiff), x) =>
          val diff = x - min
          (if (x < min) x else min, if (diff > maxDiff) diff else maxDiff)
      }._2
    }
  }
}
