import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Random, Success}

object FutureOptionUsageSample {
  val random = new Random()
  val waitMaxMilliSec = 3000

  def main(args: Array[String]): Unit = {
    val futureMilliSec: Future[Int] = Future {
      val waitMilliSec = random.nextInt(waitMaxMilliSec)
      if(waitMilliSec < 1000) throw new RuntimeException(s"waitMilliSec is ${waitMilliSec}" )
      Thread.sleep(waitMilliSec)
      waitMilliSec
    }

    val futureSec: Future[Double] = futureMilliSec.map(i => i.toDouble / 1000)

    futureSec onComplete {
      case Success(waitSec) => println(s"Success! ${waitSec} sec")
      case Failure(t) => println(s"Failure: ${t.getMessage}")
    }

    Thread.sleep(3000)
  }
}


//1　val futureMilliSec: Future[Int] = Future {...　　　　Futureオブジェクトを作成
//2　futureSec onComplete {...　　　　　　　　　　　　　　　　　　　　コールバック関数を作成（onCompleteでOptionを使って成功した場合と失敗した場合で処理を変えてる）