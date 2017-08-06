package wipro.crawler

import org.jsoup.Jsoup
import scala.collection.JavaConverters._

class LinkExtractor {

  var crawledLinks : List[String] = List.empty[String]
  def getAllPageLinks(url : String) = {
    val links = Jsoup.connect(url).timeout(0).get().select("a[href]")
    (for (link <- links.iterator().asScala) yield {
      link.attr("href")
    }).toSeq.distinct
  }

  def filterLinks(links : Seq[String],baseUrl : String) = {
      links.filter(link => link != null && link.length > 0)
        .filter(link => link.contains(baseUrl))
  }

  def crawlDomainLinks(url : String,depth : Int,maxDepth : Int,baseUrl : String) : Unit = {
    if((!crawledLinks.contains(url)) && (depth < maxDepth)){
      crawledLinks = url :: crawledLinks
      if(url.contains(baseUrl)){
        for(link <- getAllPageLinks(url)){
          crawlDomainLinks(link,depth + 1,maxDepth,baseUrl)
        }
      }
    }
  }
}
