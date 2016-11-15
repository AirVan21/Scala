import Converters._
import MarketData._
import Date._
import org.scalatest._
import org.scalactic.TolerantNumerics

class ConverterTest extends FunSuite {

  test("MarketData") {
    val target = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=2/3/2016"
    val date = new Date(2, 3, 2016)
    assert(target.equals(MarketData.buildRequest(date)))

    val usd = 65.5548
    val eur = 70.6156
    assertResult(usd)(MarketData.getExchangeRate(USD, Date.now))
    assertResult(eur)(MarketData.getExchangeRate(EUR, Date.now))
  }

  test("Converter") {
    var usd = 65.5548
    var eur = 70.6156
    var rub = 1.0

    assertResult(usd)(1.usd to RUB: Double)
    assertResult(eur)(1.eur to RUB: Double)
    assertResult(rub)(1.rub to RUB: Double)

    // for 21--11--2010
    usd = 24.4328
    eur = 35.9724

    assertResult(usd * 5.5)(5.5.usd to RUB on 21--11--2007: Double)
    assertResult(eur * 5.5)(5.5.eur to RUB on 21--11--2007: Double)
    assertResult(rub * 5.5)(5.5.rub to RUB on 21--11--2007: Double)
  }
}