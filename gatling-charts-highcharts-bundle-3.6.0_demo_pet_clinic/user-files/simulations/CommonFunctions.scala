import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.Random
import java.text.SimpleDateFormat
import java.util.Date

object CommonFunctions {

  val stringCharsSeq = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
  val intCharsSeq = "123456789"

  def getRandomString(n:Int) = (1 to n).map(_ => stringCharsSeq(Random.nextInt(stringCharsSeq.length))).mkString
  def getRandomInt(n:Int) = (1 to n).map(_ => intCharsSeq(Random.nextInt(intCharsSeq.length))).mkString

  def getTimeFormat() = {
    new SimpleDateFormat("yyyy/MM/dd").format(new Date).toString
  }
}