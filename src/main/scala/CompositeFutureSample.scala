import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Random}

object CompositeFutureSample {
  val random = new Random()       //randomという名前の変数を定義。scala.util.Randomオブジェクト
  val waitMaxMillSec = 3000       //waitMaxという名前の変数を定義。待ち時間の上限を表す整数

  def main(args: Array[String]): Unit = {                     //mainメソッドを定義
    def waitRandom(futureName: String): Int ={                //waitRandom関数を定義
      val waitMillSec = random.nextInt(waitMaxMillSec)        //nextInt()メソッドは、0から指定された引数(waitMaxMilliSec)の間のランダムな整数を生成します。つまり、waitMaxMilliSecが3000である場合、nextInt()メソッドは0から2999までの整数を生成します。その後、このランダムな整数値が、変数waitMilliSecに割り当てられ、この値が後で使用されて、ランダムな待機時間を表します。
      if(waitMillSec < 500) throw new RuntimeException(s"${futureName} waitMillSec is ${waitMillSec}")  //ランダムな待機時間が500msec未満の場合、RuntimeExceptionをスロー　
      Thread.sleep(waitMillSec)
      waitMillSec
    }

    val futureFirst: Future[Int] = Future{waitRandom("first")}
    val futureSecond: Future[Int] = Future{ waitRandom("seconf")}

    val compositeFuture: Future[(Int, Int)] = for {
      first <- futureFirst
      second <- futureSecond
    } yield(first, second)

    compositeFuture onComplete  {
      case Success((first, second)) => println(s"Success! first:${first} second:${second}")
      case Failure(t) => println(s"Failure: ${t.getMessage}")
    }

    Thread.sleep(5000)
  }
}