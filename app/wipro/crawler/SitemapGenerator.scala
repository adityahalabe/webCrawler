package wipro.crawler

import java.io.{PrintWriter, File}
import com.redfin.sitemapgenerator.{W3CDateFormat, WebSitemapGenerator}

object SitemapGenerator {

  val uniqueLinksPath = "app/output/uniqueLinks.txt"
  val linkWiseMapPath = "app/output/linkMap.txt"

  def writeUniqueLinks(links : List[String],baseUrl : String, totalActorsCreated : Int): Unit = {
    val file = new File(uniqueLinksPath)
    new PrintWriter(file) {
      write(s"Base URL ===> $baseUrl\n")
      append("----------------------------------------------------------------------------------------------------------\n")
      append(s"Total number of actors created : $totalActorsCreated\n")
      append("----------------------------------------------------------------------------------------------------------\n")
      append("Following are the unique links : \n")
      append(links.mkString("\n"))
      close
    }
  }

  def writeMap(crawledData : scala.collection.mutable.Map[String, Seq[String]],baseUrl : String, totalActorsCreated : Int): Unit = {
    val file = new File(linkWiseMapPath)
    new PrintWriter(file) {

      write(s"Base URL ===> $baseUrl\n")
      append("----------------------------------------------------------------------------------------------------------\n")
      append(s"Total number of actors created : $totalActorsCreated\n")
      append("----------------------------------------------------------------------------------------------------------\n")
      append("Following are the link wise mapping : \n")

      for ((root,links) <- crawledData){
        append(s"Internal Link ===>  $root\n")
        append(s"Total links originating from this link : " + links.length + ". \n")
        write(links.mkString("\n"))
        write("\n=========================================================================================================\n")
      }
      close
    }
  }
}
