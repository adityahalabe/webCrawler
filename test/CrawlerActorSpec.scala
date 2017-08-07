import akka.actor.{Props, ActorSystem}
import akka.testkit.TestActors.EchoActor
import akka.testkit.{ ImplicitSender, TestActors, TestKit }
import org.scalatest.{ BeforeAndAfterAll, Matchers, WordSpecLike }
import wipro.crawler.actors.Crawler


class CrawlerActorSpec() extends TestKit(ActorSystem("Crawler")) with ImplicitSender
with WordSpecLike with Matchers with BeforeAndAfterAll {

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "An Crawler actor" must {

    "start creating other actors on start " in {
      val crawlerRef = system.actorOf(Props(new Crawler("http://wiprodigital.com",1)))
      crawlerRef ! "start"
      expectNoMsg()
    }

  }
}