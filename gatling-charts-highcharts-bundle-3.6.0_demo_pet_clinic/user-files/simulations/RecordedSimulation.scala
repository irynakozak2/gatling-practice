
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import java.text.SimpleDateFormat
import java.util.Date
import scala.util.Random
import Requests._

class PetClinicAdvanced extends Simulation {

	val th_min = 1
	val th_max = 2
	val test_duration = System.getProperty("duration", "60").toInt
	val test_users = System.getProperty("users", "1").toInt
	val test_ramp_up = System.getProperty("ramp_up", "10").toInt

	val httpProtocol = http
		.baseUrl("http://localhost:8081")
		.disableFollowRedirect
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,it;q=0.6,uk;q=0.5")
		.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36")
	
	val scn = scenario("PetClinicAdvanced")

	.randomSwitch(	

		80.0 -> exec(HomePage.openHomePage)
		.pause(th_min, th_max)
		.exec(Owner.getFindOwnersPage)
		.pause(th_min, th_max)
		.exec(Owner.getAddOwnerPage)
		.pause(th_min, th_max)
		.exec(Owner.postAddOwner)
		.pause(th_min, th_max)
		.exec(Pet.getAddPet)
		.pause(th_min, th_max)
		.exec(Pet.postAddPet)
		.pause(th_min, th_max)
		.exec(Pet.getEditPetPage)
		.pause(th_min, th_max)
		.exec(Pet.postEditPetName)
		.pause(th_min, th_max)
		.exec(Visit.getAddNewVisitPage)
		.pause(th_min, th_max)
		.exec(Visit.postAddNewVisit)
		.pause(th_min, th_max),

		20.0 -> exec(Owner.getFindOwnersPage)
		.pause(th_min, th_max)
		.exec(Owner.getFindOwnersByLastname)
		.pause(th_min, th_max)
		.exec(Owner.getEditOwnerPage)
		.pause(th_min, th_max)
		.exec(Owner.postUpdateOwner)
		.pause(th_min, th_max)
		.exec(Vets.getVets)
		.pause(th_min, th_max)
	)

	setUp(scn.inject(rampUsers(test_users).during(test_duration))).protocols(httpProtocol)
}