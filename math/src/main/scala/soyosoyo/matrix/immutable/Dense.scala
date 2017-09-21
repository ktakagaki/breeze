package soyosoyo.matrix.immutable

import breeze.linalg.{DenseMatrix, DenseVector}

import scala.reflect.ClassTag
import scala.{specialized => spec}

import soyosoyo.matrix.Matrix
import soyosoyo.matrix.indexing._


object Dense{

  def apply[V]( array: Array[V] )(implicit classTag: ClassTag[V]): Dense1[V] = {
    new Dense1[V]( array.clone, Dimensions1(array.length), IndexerSimple1 )
  }

}


/**
  * DenseVector wrapper which is immutable and flexible dimension.
  * Mutable versions should be implementable in the future,
  * by inheriting a mutability trait which allows update.
  *
  * @param internal Private internal representation as flat Seq (Array)
  * @param dimensions
  * @param indexer
  * @tparam V value type (specialized for Int/Long/Float/Double
  * @tparam I
  */
abstract class Dense[@spec(Int, Long, Float, Double) V, D <: Dimensions, I <: Indexer](
                                                              private val internal: Array[V],
                                                              private val dimensions: D,
                                                              private val indexer: I
) extends Matrix[V, D, I]{
//  extends Tensor[V] {

  assert(internal != null, "argument data cannot be null")
  assert(dimensions != null, "argument dims cannot be null")
  assert(indexer != null, "argument indexer cannot be null")

  override val order: Int = dimensions.order
  //assert(getOrder <= 3, "Currently supporting only up to 3D matrices")

//  assert(getOrder == offsets.length, s"offsets length ($offsets.length) must be equal to dimensions length ($getOrder)")
//  assert(getOrder == strides.length, s"strides length ($strides.length) must be equal to dimensions length ($getOrder)")

  /** Dimensions returns a protective copy of the input dimensions. */
  final def getDimensions() = dimensions // = dimensions.toArray.clone()
}

class Dense1[@spec(Double, Int, Float, Long) V](
                                                 private val internal: Array[V],
                                                 private val dimensions: Dimensions1,
                                                 private val indexer: Indexer1
)(implicit val classTag: ClassTag[V]) extends Dense[V, Dimensions1, Indexer1](internal, dimensions, indexer) {

  def apply(i1: Int): V = internal( indexer.getInternalIndex(i1) )
  //def apply(i1: Int, i2: Null): Dense1[V] = internal( indexer.getInternalIndex(i1, i2) )

  def toArray(): Array[V] =
    (for( i1 <- 0 until dimensions.dim1 ) yield internal( indexer.getInternalIndex(i1) ) ).toArray
  def toVector(): Vector[V] =
    toArray().toVector
  def toDenseVector(): DenseVector[V] = DenseVector( toArray() )

}

class Dense2[@spec(Double, Int, Float, Long) V](
                                                   private val internal: Array[V],
                                                   private val dimensions: Dimensions2,
                                                   private val indexer: Indexer2
)(implicit val classTag: ClassTag[V]) extends Dense[V, Dimensions2, Indexer2](internal, dimensions, indexer) {

  def apply(i1: Int, i2: Int): V = internal( indexer.getInternalIndex(i1, i2) )
  //def apply(i1: Int, i2: Null): Dense1[V] = internal( indexer.getInternalIndex(i1, i2) )

  def toArray(): Array[Array[V]] =
    (for( i1 <- 0 until dimensions.dim1 ) yield {
      for (i2 <- 0 until dimensions.dim2) yield internal(indexer.getInternalIndex(i1, i2))
    }.toArray).toArray
  def toVector(): Vector[Vector[V]] = toArray().toVector.map( _.toVector )
  //def toDenseMatrix(): DenseMatrix[V] = DenseMatrix( toArray() )

}

//class Dense3[@spec(Double, Int, Float, Long) V](
//                         override protected val internalData: Seq[V],
//                         override protected val dimensions: Indices3,
//                         override protected val offsets:    Indices3,
//                         override protected val strides:    Indices3
//) extends Dense[V](internalData, dimensions, offsets, strides) {
//
//  def apply(range1: Range, range2: Range, range3: Range): Dense3[V]
//
//  def apply(index1:   Int, range2: Range, range3: Range): Dense2[V]
//  def apply(range1: Range, index2:   Int, range3: Range): Dense2[V]
//  def apply(range1: Range, range2: Range, index3:   Int): Dense2[V]
//  final def apply(index1:   Int): Dense2[V] = apply(index1, Range.All, Range.All)
//  final def apply(index1:   Int, range2: Range): Dense2[V] = apply(insdex1, range2, Range.All)
//  final def apply(range1: Range, index2:   Int): Dense2[V] = apply(range1, index2, Range.All)
//
//  def apply(range1: Range, range2: Range, range3: Range): DenseMatrix1[V]
//
////  override lazy val toLinearIndexChecked: (Seq[Int] => Int) = {
////    val tempret = ((ind: Seq[Int]) =>
////      (offsets.index0 + strides.index0*ind(0)) +
////        (offsets.index1 + strides.index1*ind(1)) +
////        (offsets.index2 + strides.index2*ind(2))
////    )
////
////    assert(internalData.length > tempret(dimensions),
////      s"Not enough input internalData specified (length = $internalData.length) for given input dimensions"  )
////    tempret
////  }
//
////  override lazy val linearIndexToIndices: (Int => Seq[Int]) = {
////    ((linearInd: Int) =>
////    Array(  (linearInd - offsets(0))%strides(0), (linearInd - offsets(1))%strides(1), (linearInd - offsets(2))%strides(2) )
////  }
//
//  override def apply(indices: Int*) = internalData( toLinearIndexChecked(indices) )
//
//
//}