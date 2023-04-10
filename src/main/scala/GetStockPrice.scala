import scala.io.Source
import scala.util.{Try,Success, Failure}

object GetStockPrice {
  def main(args: Array[String]): Unit = {
    val tickerSymbol = "花王"
    val targetPrice = 5000
    val url = s"https://finance.yahoo.co.jp/search/?query=%E8%8A%B1%E7%8E%8B"

    Try(Source.fromURL(url))match {
      case Success(response) =>
        val priceRegex = """<span class="_1-yujUee"><span class="_3rXWJKZF">([\d,]+\d+)""".r
        val price = priceRegex.findFirstMatchIn(response.mkString).map(_.group(1).replaceAll(",", "").toInt)
        price match {
          case Some(price) if price < targetPrice =>
            println(s"$tickerSymbol の株価が $targetPrice 以下になりました。")
          case Some(price) =>  println(s"$tickerSymbol は $price です")
          case _ =>  println(s"$tickerSymbol の株価を取得できませんでした。")
        }
      case Failure(exception) =>
        println(s"エラー: $exception")
    }
  }
}