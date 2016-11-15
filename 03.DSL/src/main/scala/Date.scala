import java.time.LocalDate

case class Date(day: Int, month: Int, year: Int)

case class DayMonth(day: Int, month: Int) {
  def --(year: Int) = Date(day, month, year)
}

case class Day(day: Int) {
  def --(month: Int) = DayMonth(day, month)
}

object Date {
  implicit def toDate(value: Int): Day = Day(value)

  def now: Date = {
    val date = LocalDate.now
    new Date(date.getDayOfMonth, date.getMonth.getValue, date.getYear)
  }
}

