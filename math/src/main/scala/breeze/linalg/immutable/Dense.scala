package breeze.linalg.immutable

import breeze.linalg.indexing.Indexer
import breeze.linalg.{Dimensions, Indices, Indices3, Indices2, Indices1}

import scala.{specialized => spec}

/**
  * Attempt to create DenseMatrix equivalent which is immutable and allows higher order matrices.
  * Mutable versions should be implementable in the future,
  * by inheriting a mutability trait which allows update.
  * Seq[V] is used here to ensure immutability.
  *
  * @param internal Private internal representation as flat Seq (Array)
  * @param dimensions
  * @param indexer
  * @tparam V value type (specialized for Int/Long/Float/Double
  * @tparam I
  */
abstract class Dense[@spec(Int, Long, Float, Double) V, I <: Indexer](
                                                              private val internal: Seq[V],
                                                              protected[breeze] val dimensions: Dimensions,
                                                              protected[breeze] val indexer: I
)
  extends Matrix[V] {

  assert(internal != null, "argument data cannot be null")
  assert(dimensions != null, "argument dims cannot be null")
//  assert(offsets != null, "offset data cannot be null")
//  assert(strides != null, "strides data cannot be null")

  override val order = dimensions.length
  assert(getOrder <= 3, "Currently supporting only up to 3D matrices")

//  assert(getOrder == offsets.length, s"offsets length ($offsets.length) must be equal to dimensions length ($getOrder)")
//  assert(getOrder == strides.length, s"strides length ($strides.length) must be equal to dimensions length ($getOrder)")

  /** Dimensions returns a protective copy of the input dimensions. */
  final def getDimensions() = dimensions.toArray.clone()
}

trait DenseIndices extends Indices {
  def toLinearIndex(offsets: Offsets, strides: Strides): Int
}
class DenseIndices1(override val index0: Int) extends Indices1(index0){

}
class DenseIndices2(override val index0: Int, override val index1: Int) extends Indices2(index0, index1){

}
class DenseIndices3(override val index0: Int, override val index1: Int, override val index2: Int) extends
      Indices3(index0, index1, index2){

}

class Dense2[@spec(Double, Int, Float, Long) V](
                                                       override protected val internal: Seq[V],
                                                       override protected val dimensions: Indices3,
                                                       override protected val offsets:    Indices3,
                                                       override protected val strides:    Indices3
                                                       ) extends Dense[V](internalData, dimensions, offsets, strides) {
  /** This is the central point to implement for all children.
    * it should be implemented as a var or a val, such that the JVM can inline.
    */inline
  override def toLinearIndexChecked(): (Seq[Int]) => Int = ???

  override def apply(indices: Int*): V = ???

  /** This is the central point to implement for all children.
    * it should be implemented as a var or a val, such that the JVM can inline.
    */
  override def linearIndexToIndices(): (Int) => Seq[Int] = ???

}

class Dense3[@spec(Double, Int, Float, Long) V](
                         override protected val internalData: Seq[V],
                         override protected val dimensions: Indices3,
                         override protected val offsets:    Indices3,
                         override protected val strides:    Indices3
) extends Dense[V](internalData, dimensions, offsets, strides) {

  def apply(range1: Range, range2: Range, range3: Range): Dense3[V]

  def apply(index1:   Int, range2: Range, range3: Range): Dense2[V]
  def apply(range1: Range, index2:   Int, range3: Range): Dense2[V]
  def apply(range1: Range, range2: Range, index3:   Int): Dense2[V]
  final def apply(index1:   Int): Dense2[V] = apply(index1, Range.All, Range.All)
  final def apply(index1:   Int, range2: Range): Dense2[V] = apply(insdex1, range2, Range.All)
  final def apply(range1: Range, index2:   Int): Dense2[V] = apply(range1, index2, Range.All)

  def apply(range1: Range, range2: Range, range3: Range): DenseMatrix1[V]

//  override lazy val toLinearIndexChecked: (Seq[Int] => Int) = {
//    val tempret = ((ind: Seq[Int]) =>
//      (offsets.index0 + strides.index0*ind(0)) +
//        (offsets.index1 + strides.index1*ind(1)) +
//        (offsets.index2 + strides.index2*ind(2))
//    )
//
//    assert(internalData.length > tempret(dimensions),
//      s"Not enough input internalData specified (length = $internalData.length) for given input dimensions"  )
//    tempret
//  }

//  override lazy val linearIndexToIndices: (Int => Seq[Int]) = {
//    ((linearInd: Int) =>
//    Array(  (linearInd - offsets(0))%strides(0), (linearInd - offsets(1))%strides(1), (linearInd - offsets(2))%strides(2) )
//  }

  override def apply(indices: Int*) = internalData( toLinearIndexChecked(indices) )


}