package wipro.crawler

import akka.util.Timeout
import scala.concurrent.duration._
import akka.actor.{Props, ActorSystem}

object Application {
  implicit val timeout: Timeout = 15.minute
  val baseUrl = "http://wiprodigital.com"
  val maxDepth = 3

  def main(args: Array[String]) {
    val actorSystem = ActorSystem()
    (actorSystem.actorOf(Props[Crawler](new Crawler(baseUrl,maxDepth))) ! "start")
  }
}
