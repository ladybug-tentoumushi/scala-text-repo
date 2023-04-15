//ALDS1_3_A
/*ScalaのStackライブラリの参考URL https://www.geeksforgeeks.org/stack-in-scala/
逆ポーランド記法とスタックの考え方の参考 https://qiita.com/drken/items/6a95b57d2e374a3d3292
 */

import scala.collection.mutable.Stack
import scala.io.StdIn.readLine


object AOJStack extends App {
  val inputs: Array[String] = readLine.split(" ")
  val result = stack(inputs)
  println(result)

  def stack(inputs: Array[String]): Int = {
    val s = Stack[Int]()

    if(inputs.size > 2) {
      for(x <- inputs) {
        //val x1 = s.push(x.toInt)
        //val x2 = s.push(x.toInt)
        x match {
          case "+" => s.push(s.pop() + s.pop())
          case "-" => s.push(-s.pop() + s.pop())
          case "*" => s.push(s.pop() * s.pop())
          case "/" => s.push(s.pop() / s.pop())
          case _ => s.push(x.toInt)
        }
      }
      s.top
    } else {
      throw new IllegalArgumentException("要素数が不足してます")
    }
  }
}


/*import scala.io.StdIn.readLine
import scala.collection.mutable.Stack

object AOJStack extends App {
  val inputs:Array[String] = readLine.split(" ")
  val result = stack(inputs)
  println(result)

  def stack(inputs: Array[String]): Int = {
    val stack = new Stack[Int]

    for (x <- inputs) {
      if (x == "+" || x == "-" || x == "*" || x == "/") {
        val right = stack.pop()
        val left = stack.pop()
        x match {
          case "+" => stack.push(left + right)
          case "-" => stack.push(left - right)
          case "*" => stack.push(left * right)
          case "/" => stack.push(left / right)
        }
      } else {
        stack.push(x.toInt)
      }
    }

    stack.top
  }

}
*/