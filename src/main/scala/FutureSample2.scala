import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object FutureSample2 {

  def main(args: Array[String]): Unit = {
    val f1 = Future {                         //Futureを作成
      Thread.sleep(1000)                      //1000ms後に以下の処理を実施
      10                                      //10を出力
    }

    val f2 = Future {
      Thread.sleep(500)
      20
    }

    val f3 = Future {
      Thread.sleep(2000)
      30
    }

    val f4 = for {                          //Futureを並列で実行し、各Futureの結果を合計する処理（使った並列処理でよく使う）
      result1 <- f1                         //result1がf1(Future1)を受け取るように定義
      result2 <- f2
      result3 <- f3
    } yield result1 + result2 + result3     //yieldで指定された処理を実行→結果を新しいFutureとして返す

    f4.foreach(result =>                    //foreachはFutureクラスに定義されているメソッド。Futureの実行結果を処理する（コールバック関数（Futureが終わったあとに呼び出されるメソッド）を受け取る）
    println(s"The result is : +$result"))   //Futureが成功した場合、この処理が実施される

    Thread.sleep(3000)
  }
}
