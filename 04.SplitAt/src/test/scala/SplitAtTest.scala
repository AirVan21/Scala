import org.scalatest._
import Peano._
import HList._

class SplitAtTest extends FunSuite {
  test("PeanoNumber") {
    assertResult(0)(Zero.toInt)
    assertResult(1)(Succ(Zero).toInt)

    val peanoThree = Succ(Succ(Succ(Zero)))
    assertResult(3)(peanoThree.toInt)
  }

  test("SplitTest") {
    val testList = "a" :: "b" :: 42 :: HNil

    val (left0, right0) = splitAt(Zero, testList)
    assertResult(HNil)(left0)
    assertResult(testList)(right0)

    val (left1, right1) = splitAt(Succ(Zero), testList)
    assertResult("a" :: HNil)(left1)
    assertResult("b" :: 42 :: HNil)(right1)
  }
}

