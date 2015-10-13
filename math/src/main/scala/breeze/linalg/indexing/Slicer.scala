package breeze.linalg.indexing

/**Encapsulates indices and dimensional information for DenseMatrixB.
  * Inherits from Seq[Int] to allow apply(Int*) type syntax.
  * Using tuple for this purpose would have been an option, except that
  * variable access becomes more difficult, and there is no natural parent class
  * that can be referred to.
  *
  * Created by ktakagaki on 15/04/14.
  */
abstract class Slicer[D :> Indices, DR :> D] /*extends Seq[Int]*/{
//  final lazy val iterator: Iterator[Int] = internalList.iterator
//  final lazy val length: Int = internalList.length
  abstract val dimensions: Int
}
case class Slicer1(val index0: Int) extends Slicer {
  override val dimensions = 1
}
case class Slicer2(val index0: Int, val index1: Int) extends Slicer {
  override val dimensions = 2
}
case class Slicer3(val index0: Int, val index1: Int, val index2: Int) extends Slicer {
  override val dimensions = 3
}

object Slicer {
  implicit def tup(indices: Seq[Int]) = {
    indices.length match {
      case 1 => Indices1(indices(0))
      case 2 => Indices2(indices(0), indices(1))
      case 3 => Indices3(indices(0), indices(1), indices(2))
      case _ => throw new IllegalArgumentException("currently supporting up to 3 indices")
    }
  }
}