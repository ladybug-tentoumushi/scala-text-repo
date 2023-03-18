import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object FutureSample {

  def main(args: Array[String]): Unit = {
    val s = "Hello"
    val f: Future[String] = Future {                            //Futureオブジェクトfを生成
      Thread.sleep(1000)                                        //{}内のコードがapplyメソッドに渡される
    s + " future!"
    }

    f.foreach { s =>                                            //.foreachはFutureが完了したときに実行されるコールバック関数を指定するメソッド
      println(s)
    }

    println(f.isCompleted) // false                             //isCompletedメソッドは、Futureが完了したかどうかを判定するためのメソッド

    Thread.sleep(5000) // Hello future!

    println(f.isCompleted) // true

    val f2: Future[String] = Future {                           //Futureオブジェクトf2を生成
      Thread.sleep(1000)
      throw new RuntimeException("わざと失敗")
    }

    f2.failed.foreach { e =>                                      //failedメソッドは、Futureが例外を投げた場合に、例外オブジェクトを取得するためのメソッド
      println(e.getMessage)
    }

    println(f2.isCompleted) // false

    Thread.sleep(5000) // わざと失敗                               //Thread.sleepは、現在のスレッドを指定した時間だけスリープ状態にします

    println(f2.isCompleted) // true
  }
}

// １　val f: Future[String] = Future...    Futureオブジェクトを作成
// ２　f.foreach { s =>...                 コールバック関数作成（Future終了後の処理を指定）
// ３　val f2: Future[String] = Future {…　 例外が発生するFutureオブジェクトを作成　
// ４ f2.failed.foreach { e =>...          コールバック関数作成（Future終了後の処理を指定）