// Abramov Ivan
//
// Реалзуйте IntArrayBuffer с интерфейсом IntTraversable
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
  private var storage = new Array[Int](0)
  private var storageSize = 0

  def apply(index: Int): Int = {
    if (isValidIndex(index))
      storage.apply(index)
    else
      throw new IndexOutOfBoundsException()
  }

  def update(index: Int, element: Int): Unit = {
    if (isValidIndex(index))
      storage.update(index, element)
    else
      throw new IndexOutOfBoundsException()
  }

  def clear(): Unit = {
    storageSize = 0
    storage = new Array[Int](storageSize)
  }

  def +=(element: Int): IntArrayBuffer = {
    ensureSize(storageSize + 1)
    storage.update(storageSize - 1, element)

    this
  }

  def ++=(elements: IntTraversable): IntArrayBuffer = {
    elements.foreach(item => this += item)

    this
  }

  def remove(index: Int): Int = {
    val output = storage.apply(index)
    val shiftStart = index + 1
    val shiftLength = size - index - 1
    Array.copy(storage, shiftStart, storage, index, shiftLength)

    output
  }

  override def isEmpty: Boolean = storageSize == 0

  override def size: Int = storageSize

  override def contains(element: Int): Boolean = storage.contains(element)

  override def head: Int = apply(0)

  override def tail: IntArrayBuffer = {
    if (!isValidIndex(storageSize - 1))
      throw new IndexOutOfBoundsException()

    val output = new IntArrayBuffer()
    output.ensureSize(storageSize - 1)
    Array(storage, 1, output.storage, 0, output.storageSize)

    output
  }

  override def ++(traversable: IntTraversable): IntArrayBuffer = {
    val output = new IntArrayBuffer()
    storage.foreach(item => output += item)
    traversable.foreach(item => output += item)

    output
  }

  protected def ensureSize(size: Int): Unit = {
    if (storageSize > size)
      return

    val buffer = new Array[Int](size)
    Array.copy(storage, 0, buffer, 0, storageSize)
    storage = buffer
    storageSize = size
  }

  override def filter(predicate: (Int) => Boolean): IntTraversable = {
    val result = storage.filter(predicate)
    val output = new IntArrayBuffer()
    output.storage = result
    output.storageSize = result.size

    output
  }

  override def map(function: (Int) => Int): IntTraversable = {
    val result = storage.map(function)
    val output = new IntArrayBuffer()
    output.storage = result
    output.storageSize = result.size

    output
  }

  override def flatMap(function: (Int) => IntTraversable): IntTraversable = {
    val result = storage.map(function)
    if (result.isEmpty)
      return new IntArrayBuffer()

    var output = new IntArrayBuffer
    result.foreach(item => output = output ++ item)

    output
  }

  override def foreach(function: (Int) => Unit): Unit = storage.foreach(function)

  def isValidIndex(index: Int): Boolean = (index >= 0) && (index < storageSize)
}

object IntArrayBuffer {
  def empty: IntArrayBuffer = new IntArrayBuffer()

  def apply(elements: Int*): IntArrayBuffer = {
    var output = new IntArrayBuffer()
    elements.foreach(item => output += item)

    output
  }

  def unapplySeq(buffer: IntArrayBuffer): Option[IntArrayBuffer] = {
    if (buffer.isEmpty)
      None
    else
      Some(buffer)
  }
}


// Debug info
// 1
val array1 = IntArrayBuffer(0, 1, 2)
val array2 = IntArrayBuffer(3, 4)
val array3 = array1 ++ array2
array3.foreach(print)
// 2
array3.apply(3)
// 3
array3.update(3, 10)
array3.apply(3)
// 4
array3.clear()
val empty = array3.isEmpty
val zeroSize = array3.size
// 5
val seven = 7
array3 += seven
val oneSize = array3.size
val contain = array3.contains(seven)
val notContain = array3.contains(seven + 1)
// 6
val emptyArray = array3.filter(x => x > seven)
emptyArray.isEmpty
// 7
val array1x2 = array1.map(item => item * 2)
array1x2.foreach(print)

