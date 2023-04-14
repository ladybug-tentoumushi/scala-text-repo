//ALDS1_3_A

import scala.io.StdIn.readLine
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
