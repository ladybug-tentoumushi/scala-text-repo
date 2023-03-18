import scala.concurrent.Future                            //ScalaのFutureライブラリ（非同期処理を実行できるライブラリ）を呼び出す*
import scala.concurrent.ExecutionContext.Implicits.global //並行処理するためのスレッド（メモリ？）を用意するライブラリ。普通の並列処理をしたいならこれでいい（大量の処理をしたいときは別）*
//*Futureを作成する（非同期処理を実行する）にはconcurrent.Futureをインポートする必要がある。
//Futureを使用するにはExecutionContextをインポートする必要がある。

object FutureSample1 {

  def main(args: Array[String]): Unit = {
    val s = "Hello"                                       //変数sにHelloを代入
    val f: Future[String] = Future {                      //Future（非同期処理を実行できる型）を作成（標準ライブラリのFuture.applyメソッドまたはFuture.successful/Future.failedメソッドを使用して作成できる。）。変数fに代入
      Thread.sleep(1000)                                  //スレッドを指定された時間（1000msec）停止する（タイムアウト処理なんかに使われる）
      s + "future!"                                       //文字列s + future!を返す（メソッドの最後に書かれた処理が戻り値になる（Futureの戻り値＝文字列s + future!））
    }

    f.foreach { s =>                                      //foreach = f(Future)が終了したら呼び出されるメソッド（コールバック関数）を受け取る
      println(s)                                          //処理の結果が返って来るだけなので、defやvalは必要ない
    }

    println(f.isCompleted)                                //isCompletedメソッドを使って、Futureの完了状態を出力させる→まだ完了してないのでfolse

    Thread.sleep(5000)                                     //５秒後にFutureが完了してforeachで定義した処理が実行（Hello future!が出力）

    println(f.isCompleted)                                  //もう一度isCompletedメソッドを使って、Futureの完了状態を出力させる


    val f2: Future[String] = Future {                       //f2という新しいFutureを作成
      Thread.sleep(1000)//1000ms後に↓の処理を実施
      throw new RuntimeException("わざと失敗")//例外処理でエラーをださせる
    }

    f2.failed.foreach(e =>                                  //Futureの処理が失敗した場合の処理
    println(e.getMessage))

    println(f2.isCompleted)

    Thread.sleep(5000)

    println(f2.isCompleted)
  }
}
