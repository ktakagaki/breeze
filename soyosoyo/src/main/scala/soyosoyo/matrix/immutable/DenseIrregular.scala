package soyosoyo.matrix.immutable

import breeze.linalg.DenseMatrix

import scala.reflect.ClassTag
import scala.{specialized => spec}
import soyosoyo.matrix.types.Indices

import scala.collection.mutable.ArrayBuffer


object DenseIrregular

trait DenseIrregular[@spec( Int, Long, Float, Double) V] extends Irregular[V]

// 1 dimensional arrays are always regular, so Dense1 is defined instead of DenseIrregular1
//class DenseIrregular1[@spec(Double, Int, Float, Long) V](

class DenseIrregular2[@spec(Double, Int, Float, Long) V]
  (private val data: Array[Array[V]], cloneData: Boolean = true )
  ( implicit val classTag: ClassTag[V] )
  extends DenseIrregular[V] with Irregular2[V] {

  // <editor-fold defaultstate="collapsed" desc=" constructor-related ">

  loggerRequire( data != null, "data cannot be null" )
  private val dataClone: Array[Array[V]] = if(cloneData) data.clone().map( _.clone ) else data

//  /**Alternate constructor with default.
//    *Defined explicitly, to facilitate calling from Java.*/
//  def this(data: Array[Array[V]]) = this(data, true)

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" dimensions and other property accessors ">

  lazy val isRegular = {
    if( dimension <= 1 ) true
    else {
      val dim1 = dimension(0)
      ( 1 until dimension ).forall( dim1 == dimension( _ ))
    }
  }

  override lazy val dimension: Int = dataClone.length

  override def dimension(x1: Int): Int = {
    val instantiated = Indices.instantiate( x1, dimension )
    loggerRequire( 0 <= instantiated && instantiated < dimension, s"dimension x1 OOB: $x1 (length: ${dimension})" )
    dataClone(x1).length
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Array/Vector/DenseMatrix accessors, flatten ">

  override def toArray(): Array[Array[V]] = dataClone.clone().map( _.clone() )
  override def toVector(): Vector[Vector[V]] = dataClone.toVector.map( _.toVector )
  override def toDenseMatrix(): DenseMatrix[V] = {
    toDenseRegular().toDenseMatrix()
  }
  def toDenseRegular(): DenseRegular2[V] = {
    loggerRequire( isRegular, "This DenseIrregular2 is not regular, and cannot be converted!")
    new DenseRegular2[V]( dataClone, true )
  }
  override def flatten(): Array[V] = {
    val tempReturn = new ArrayBuffer[V]()
    for( x1 <- 0 until dimension )
      for( x2 <- 0 until dimension(x1) ) tempReturn.append( apply(x1, x2) )
    tempReturn.toArray
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" apply ">

  override def apply(i1: Int, i2: Int): V = dataClone(i1)(i2)
  //def apply(i1: Int, i2: Null): Dense1[V] = internal( indexer.getInternalIndex(i1, i2) )


  /**
    * This apply returns an irregular object (and not a regular object),
    * since specifications such as IndexRange(0, -1) may return ragged objects.
    *
    */
  override def apply(r1: Indices, r2: Indices): DenseIrregular2[V] = {
    new DenseIrregular2(
      r1.instantiate( dimension ).toArray.map( (x1: Int) => {
        r2.instantiate( dimension(x1) ).toArray.map( apply(x1, _) )
      }),
      false
    )
  }
  def apply(x1: Int, i2: Indices): soyosoyo.matrix.immutable.Matrix1[V] = ???
  def apply(i1: Indices, x2: Int): soyosoyo.matrix.immutable.Matrix1[V] = ???

  // </editor-fold>


  override def equals[T](matrix: Matrix[T]): Boolean = ???

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