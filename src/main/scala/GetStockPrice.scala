import scala.io.Source
import scala.util.{Try,Success, Failure}

object GetStockPrice {
  def main(args: Array[String]): Unit = {
    //val tickerSymbol = "花王"
    //val targetPrice = 5000
    val stocks = List(("花王", 5000, s"https://finance.yahoo.co.jp/search/?query=%E8%8A%B1%E7%8E%8B"), ("任天堂", 5500, "https://finance.yahoo.co.jp/search/?query=%E4%BB%BB%E5%A4%A9%E5%A0%82"), ("ソニー", 10000, "https://finance.yahoo.co.jp/search/?query=%E3%82%BD%E3%83%8B%E3%83%BC"))
    //val url = s"https://finance.yahoo.co.jp/search/?query=%E8%8A%B1%E7%8E%8B"

    for ((tickerSymbol, targetPrice, url) <- stocks) {

      Try(Source.fromURL(url)) match {
        case Success(response) =>
          val priceRegex = """<span class="_1-yujUee"><span class="_3rXWJKZF">([\d,]+\d+)""".r
          val price = priceRegex.findFirstMatchIn(response.mkString).map(_.group(1).replaceAll(",", "").toInt)
          price match {
            case Some(price) if price < targetPrice =>
              println(s"$tickerSymbol の株価が $targetPrice 以下になりました。")
            case Some(price) => println(s"$tickerSymbol は $price です。")
            case _ => println(s"$tickerSymbol の株価を取得できませんでした。")
          }
        case Failure(exception) =>
          println(s"エラー: $exception")
      }
    }
  }
}