//ALDS1_2_A
import scala.io.StdIn.readLine

object Main extends App {
  val n: Int = readLine().toInt
  val nums: Array[Int] = readLine.split(" ").map(_.toInt)


  def bubbleSort(nums: Array[Int], n: Int): (Array[Int], Int) = {
    var count = 0
    if (n <= 1) (nums, count)
    else {
      for (i <- 0 until n - 1) {
        if (nums(i) > nums(i + 1)) {
          val temp = nums(i)
          nums(i) = nums(i + 1)
          nums(i + 1) = temp
          count += 1
        }
      }
      val result = bubbleSort(nums: Array[Int], n - 1)
      (result._1, count + result._2)
    }
  }

  val (resultnums, swaps) = bubbleSort(nums, n)
  println(resultnums.mkString(" "))
  println(swaps)
}

/*object AOJBubbleSort extends App {
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
*/