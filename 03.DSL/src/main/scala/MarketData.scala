import scala.xml.XML

object MarketData {
  val baseURL = "http://www.cbr.ru/scripts/XML_daily.asp?date_req="

  def getExchangeRate(currency: Currency, date: Date): Double = {
    val request = buildRequest(date)
    val document = XML.load(request)
    val currencies = document
      .child
      .toList
      .filter(item => item.isInstanceOf[scala.xml.Elem])
    val currencyNode = currencies.filter(item => item.child.exists(_.text == currency.name))
    val rate = currencyNode
      .head
      .child
      .filter(item => item.label.contains("Value"))
      .head
      .text
    rate.replace(',', '.').toDouble
  }

  def buildRequest(input: Date): String = {
    var date = if (input == null) Date.now else input
    val dateURL = s"${date.day}/${date.month}/${date.year}"
    (baseURL + dateURL)
  }
}
