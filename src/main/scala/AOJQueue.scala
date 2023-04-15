//ALDS1_3_B

import scala.collection.mutable._
import scala.io.StdIn.readLine

object AOJQueue extends App {
  val Array(n, qTime) = readLine().split(" ").map(_.toInt)
  val Array(p, times) = readLine().split(" ")
  var i = p.filter(_ == "1")
  val t = times.toInt

  def queue(qTime: Int, t: Array[Int]): Array[Int] = {
    var q: Queue[Int] = Queue()
    var time = 0
    while(q.isEmpty) {
      for(x <- t) {
        if(x > qTime) {
          q.enqueue(x - qTime)
          time = time + qTime
        }
        else {
        q.dequeue
        time = i.toInt * qTime
        }
      }
    }
  }
}
