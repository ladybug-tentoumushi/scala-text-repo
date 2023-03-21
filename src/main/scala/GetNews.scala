import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

case class News ()

object GetNews {
  def main(args: Array[String]): Unit = {

    def getNhkNews(word: String): List[News] = ???
    def getSearchGoogleNews(word: String): List[News] = ???

    val word = "大谷翔平"

    val nhkNews: Future[List[News]] = Future {getNhkNews(word) }
    val googleNews: Future[List[News]] = Future {getSearchGoogleNews(word) }

    val compositeNews: Future[(List[News], List[News])] = for {
      nhk <- nhkNews
      google <- googleNews
    } yield (nhk, google)

    val allNews: Future[List[News]] = compositeNews.map{
      case (nhk, google) => nhk:::google
    }

    allNews.onComplete {
      case Success((allnews)) => println(s"$allnews")
      case Failure(t) => println(t.getMessage)
    }
    Thread.sleep(5000)
  }
}
