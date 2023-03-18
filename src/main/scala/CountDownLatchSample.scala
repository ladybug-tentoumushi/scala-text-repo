//FutureやPromiseの便利な特性を利用して、
// 0〜1000ミリ秒間のランダムな時間を待つ8個のFutureを定義し、
// そのうちの3つが終わり次第すぐに
// その3つの待ち時間を全て出力するという実装をしてみましょう。

import scala.concurrent.{Promise, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

//思考過程
//１　ランダムな時間を生成するメソッドを作る
//　　Promiseの生成(waitTime受け取る)
//　　Futureメソッドの生成(PromiseからwaitTimeをもらうやつ)
//２　Futureを８つ作る
//３　PromiseからwaitTimeが短い上位３つのFutureを受け取る
//４　Futureが結果を出力


object CountDownLatchSample {
  def main(args: Array[String]): Unit = {

    //１　ランダムな時間を生成するメソッドを作る
    def waitRandomTime(): Future[Int] = {
      val promise = Promise[Int]()
      val random = new Random()
      val waitTime = random.nextInt(1000)
      //val t = new Thread(() => {
      Thread.sleep(waitTime)
      promise.success(waitTime) // 結果をPromiseに設定する
      //})
      //t.start()
      promise.future
    }

    // 8つのランダムな時間を待つFutureを生成する
    val futures = Seq.fill(8)(waitRandomTime()) //Seq.fill(8) : 長さが8のリストを生成

    // 最初に終了する3つのFutureを取得して、その結果を出力する
    val first3 = Future.sequence(futures).map(_.take(3))
    //Future.sequence(futures)は、8個のFutureを並列に実行し、すべてのFutureが完了するまで待機
    //map(_.take(3))は、8個のFutureが完了した後に、結果のリストの最初の3つの要素を取得
    //この結果は、新しいFutureに包まれています
    first3.foreach(results => {
      results.foreach(time => {
        println(s"待ち時間: $time ミリ秒")
      })
    })
  }
}
