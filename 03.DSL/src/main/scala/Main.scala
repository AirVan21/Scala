import Date._
import Converters._

object Main extends App {
  println(1.usd to RUB on 21--11--2007: Double)
  println(1.eur to RUB on 21--11--2007: Double)
  println(1.eur to USD on 21--11--2007: Double)
}