import MarketData._

sealed trait Currency {
  val name = ""
  def convert(amount: Double, currency: Currency, date: Date = Date.now): Double
}

object RUB extends Currency {
  override val name = "RUB"
  override def convert(amount: Double, currency: Currency, date: Date): Double = {
    currency match {
      case `RUB` => amount
      case `USD` => amount / MarketData.getExchangeRate(USD, date)
      case `EUR` => amount / MarketData.getExchangeRate(EUR, date)
    }
  }
}

object EUR extends Currency {
  override val name = "EUR"
  override def convert(amount: Double, currency: Currency, date: Date): Double = {
    currency match {
      case `RUB` => amount * MarketData.getExchangeRate(EUR, date)
      case `USD` => amount *  MarketData.getExchangeRate(EUR, date) / MarketData.getExchangeRate(USD, date)
      case `EUR` => amount
    }
  }
}

object USD extends Currency {
  override val name = "USD"
  override def convert(amount: Double, currency: Currency, date: Date): Double = {
    currency match {
      case `RUB` => amount * MarketData.getExchangeRate(USD, date)
      case `USD` => amount
      case `EUR` => amount * MarketData.getExchangeRate(USD, date) / MarketData.getExchangeRate(EUR, date)
    }
  }
}


