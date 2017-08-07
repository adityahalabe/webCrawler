package controllers

import akka.util.Timeout
import wipro.crawler.actors.Crawler
import scala.concurrent.duration._
import akka.pattern.ask
import javax.inject._
import akka.actor.{Props, ActorSystem}
import com.google.inject.Inject
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class WebCrawlerController @Inject()(actorSystem: ActorSystem)  extends Controller {
  val baseUrl = "http://wiprodigital.com"
  val maxDepth = 3

  def getLinks = Action {
    (actorSystem.actorOf(Props[Crawler](new Crawler(baseUrl,maxDepth))) ! "start")
    Ok(views.html.requestAccepted(baseUrl,maxDepth))

  }
  /*def getLinks = Action.async {
    implicit val timeout: Timeout = 2.minute
    (actorSystem.actorOf(Props[Crawler](new Crawler(baseUrl,maxDepth))) ? "start").mapTo[List[String]].map {
      links => Ok(links.mkString("\n"))
    }
  }*/

}
