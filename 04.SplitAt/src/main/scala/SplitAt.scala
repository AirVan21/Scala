sealed trait Peano {
  def toInt: Int
}

case object Zero extends Peano {
  override def toInt: Int = 0
}

case class Succ[T <: Peano](n : T) extends Peano {
  override def toInt: Int = 1 + n.toInt
}

object Peano {
  type Zero = Zero.type
}

sealed trait HList {
  def ::[H](h: H): HCons[H, this.type] = HCons(h, this)
}

case object HNil extends HList
case class HCons[+H, +T <: HList](head: H, tail: T) extends HList

object HList {
  import Peano._

  type ::[+H, +T <: HList] = HCons[H, T]
  type HNil = HNil.type

  trait Appendable[L <: HList, R <: HList, Res <: HList] {
    def apply(l: L, r: R): Res
  }

  trait Splittable[T <: Peano, L <: HList, Left <: HList, Right <: HList] {
    def apply(n : T, l: L): (Left, Right)
  }

  object Appendable {
    implicit def base[R <: HList]: Appendable[HNil, R, R] = new Appendable[HNil, R, R] {
      override def apply(l: HNil, r: R): R = r
    }

    implicit def step[H, L <: HList, R <: HList, Res <: HList](implicit appendable: Appendable[L, R, Res]): Appendable[H :: L, R, H :: Res] = {
      new Appendable[H :: L, R, H :: Res] {
        override def apply(l: H :: L, r: R): H :: Res = {
          l.head :: appendable.apply(l.tail, r)
        }
      }
    }
  }

  object Splittable {
    implicit def base[L <: HList]: Splittable[Zero, L, HNil, L] = new Splittable[Zero, L, HNil, L] {
      override def apply(n: Zero, l: L): (HNil, L) = (HNil, l)
    }

    implicit def step[T <: Peano, H, L <: HList, Left <: HList, Right <: HList] (implicit splittable: Splittable[T, L, Left, Right]) = {
      new Splittable[Succ[T], H :: L, H :: Left, Right] {
        override def apply(n: Succ[T], l: H :: L): (H :: Left, Right) = splittable(n.n, l.tail) match {
          case (left, right) => (HCons(l.head, left), right)
        }
      }
    }
  }

  def append[L <: HList, R <: HList, Res <: HList](l: L, r: R) (implicit appendable: Appendable[L, R, Res]): Res = {
    appendable(l, r)
  }

  def splitAt[T <: Peano, L <: HList, Left <: HList, Right <: HList](index: T, l : L) (implicit splittable: Splittable[T, L, Left, Right]) : (Left, Right) = {
    splittable(index, l)
  }
}