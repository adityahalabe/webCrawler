package wipro.crawler

import akka.actor.{Actor, Props}

class Crawler (baseUrl : String,maxDepth : Int) extends Actor{

  def receive = {
    case "start" => {
      val linkExtractor = new LinkExtractor()
      val links = linkExtractor.getAllPageLinks(baseUrl)
      self ! (baseUrl,links.filter(_ != baseUrl))
      linkExtractor.filterLinks(links,baseUrl) foreach {
        link => context.actorOf(Props[InternalLinkCrawler](new InternalLinkCrawler(baseUrl,link,maxDepth)))
      }
    }
    case  (root : String,links : Seq[String]) => {
      println("----------------------------------------------------------------------------------------------------------")
      println(s"## Link --> $root : Total links originating from this link : " + links.length)
      println(links.mkString("\n"))
      println("----------------------------------------------------------------------------------------------------------")
    }
  }
}