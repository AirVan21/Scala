class CurrencyTranslator(amount: Double, source: Currency) {
  def to(currency: Currency) = new CurrencyDateTranslator(amount, source, currency)
}

case class CurrencyDateTranslator(amount: Double, source: Currency, target: Currency) {
  def on(date: Date) = new Converter(amount, source, target, date)
}

case class Converter(amount: Double, source: Currency, target: Currency, date: Date)

object  Converters {
  implicit class CurrencyConverterFromDouble(amount: Double) {
    def rub = new CurrencyTranslator(amount, RUB)
    def usd = new CurrencyTranslator(amount, USD)
    def eur = new CurrencyTranslator(amount, EUR)
  }

  implicit class CurrencyConverterFromInt(amount: Int) {
    def rub = new CurrencyTranslator(amount, RUB)
    def usd = new CurrencyTranslator(amount, USD)
    def eur = new CurrencyTranslator(amount, EUR)
  }

  implicit def ToDouble(converter: Converter): Double = {
    converter.source.convert(converter.amount, converter.target, converter.date)
  }

  implicit def ToDouble(converter: CurrencyDateTranslator): Double = {
    converter.source.convert(converter.amount, converter.target)
  }
}