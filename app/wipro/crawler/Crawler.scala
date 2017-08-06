package wipro.crawler

import akka.actor.{Actor, Props}

class Crawler (baseUrl : String,maxDepth : Int) extends Actor{

  var totalActorsCreated = 1
  var totalCompleted = 0
  var crawledData = scala.collection.mutable.Map[String, Seq[String]]()
  def receive = {
    case "start" => {
      val linkExtractor = new LinkExtractor()
      val links = linkExtractor.getAllPageLinks(baseUrl)
      self ! (baseUrl,links.filter(_ != baseUrl))
      linkExtractor.filterLinks(links,baseUrl) foreach {
        link => context.actorOf(Props[InternalLinkCrawler](new InternalLinkCrawler(baseUrl,link,maxDepth)))
          totalActorsCreated += 1
      }
      println(s"-------------Total Actors created for task: $totalActorsCreated")

    }
    case  (root : String,links : Seq[String]) => {
      crawledData += (root -> links)
      totalCompleted += 1
      println(s"-------------Completed for URL : $root \t\t\t||  Completed Actor count : $totalCompleted")
      if(totalActorsCreated == totalCompleted){
        self ! "done"
      }

    }
    case "done" => {
      for ((root,links) <- crawledData){
        println(s"## Link --> $root : Total links originating from this link : " + links.length)
        println(links.mkString("\n"))
        println("----------------------------------------------------------------------------------------------------------")
      }
    }
  }
}