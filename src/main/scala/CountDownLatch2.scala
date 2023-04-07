/*0〜1000ミリ秒間のランダムな時間を待つ8個のFutureを定義し、
そのうちの3つが終わり次第すぐにその3つの待ち時間を全て出力する*/

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Random, Success}

object CountDownLatch2 {
  def main(args: Array[String]): Unit = {

    val waitMaxMilliSec = 1001
    val promises: Seq[Promise[Int]] = Seq.fill(8)(Promise[Int])
    //Seq[Promise[Int]]⇒Promise[Int]型のリスト
    //Seq.fill(3)(Promise[Int])⇒長さが3で要素がPromise[Int]のリスト

    promises.foreach(promise => {
      val random = new Random()
      val waitTime = random.nextInt(waitMaxMilliSec)
      Thread.sleep(waitTime)
      promise.success(waitTime)
    })

    val futures: Seq[Future[Int]] = promises.map(_.future)

    futures.take(3).foreach(_.foreach(println))
    Thread.sleep(5000)
  }
}
