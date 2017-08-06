package controllers

import akka.util.Timeout
import wipro.crawler.Crawler
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
    implicit val timeout: Timeout = 15.minute
    (actorSystem.actorOf(Props[Crawler](new Crawler(baseUrl,maxDepth))) ! "start")

    Ok("Request Accepted : See console logs for crawling results.")

  }


}
