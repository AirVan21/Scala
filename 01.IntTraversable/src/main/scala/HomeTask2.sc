//Реалзуйте IntArrayBuffer с интерфейсом IntTraversable
trait IntTraversable {
  def isEmpty: Boolean

  def size: Int

  def contains(element: Int): Boolean

  def head: Int

  def tail: IntTraversable

  def ++(traversable: IntTraversable): IntTraversable

  def filter(predicate: Int => Boolean): IntTraversable

  def map(function: Int => Int): IntTraversable

  def flatMap(function: Int => IntTraversable): IntTraversable

  def foreach(function: Int => Unit): Unit
}

class IntArrayBuffer extends IntTraversable {
  def apply(index: Int): Int = ???

  def update(index: Int, element: Int): Unit = ???

  def clear(): Unit = ???

  def +=(element: Int): IntArrayBuffer = ???

  def ++=(elements: IntTraversable): IntArrayBuffer = ???

  def remove(index: Int): Int = ???

  override def isEmpty: Boolean = ???

  override def size: Int = ???

  override def contains(element: Int): Boolean = ???

  override def head: Int = ???

  override def tail: IntArrayBuffer = ???

  override def ++(traversable: IntTraversable): IntArrayBuffer = ???

  protected def ensureSize(size: Int): Unit = ???

  override def filter(predicate: (Int) => Boolean): IntTraversable = ???

  override def map(function: (Int) => Int): IntTraversable = ???

  override def flatMap(function: (Int) => IntTraversable): IntTraversable = ???

  override def foreach(function: (Int) => Unit): Unit = ???
}

object IntArrayBuffer {
  def empty: IntArrayBuffer = ???

  def apply(elements: Int*): IntArrayBuffer = ???

  def unapplySeq(buffer: IntArrayBuffer): Option[IntArrayBuffer] = ???
}