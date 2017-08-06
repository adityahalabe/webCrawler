package wipro.crawler

import akka.actor.Actor
import wipro.crawler.LinkExtractor

class InternalLinkCrawler(baseUrl : String,currentLink:String,maxDepth : Int) extends Actor{

  self ! currentLink
  def receive = {
    case  (currentUrl : String) => {
        val linkExtractor = new LinkExtractor()
        linkExtractor.crawlDomainLinks(currentLink,1,maxDepth,baseUrl)
        context.parent ! (currentUrl,linkExtractor.crawledLinks.filter(_ != currentUrl))
    }
  }
}
