package soyosoyo.matrix.immutable

import breeze.linalg.DenseMatrix
import soyosoyo.types.Indices

import scala.reflect.ClassTag
import scala.{specialized => spec}

class DenseRegular2[@spec(Double, Int, Float, Long) V]
(private val data: Array[Array[V]], cloneData: Boolean = true )( implicit val classTag: ClassTag[V] )
  extends Matrix2[V] with Regular2[V] {

  // <editor-fold defaultstate="collapsed" desc=" constructor-related ">

  loggerRequire( data != null, "data cannot be null" )
  private val dataClone: Array[Array[V]] = if( cloneData ) data.clone().map( _.clone() ) else data

//  /**Alternate constructor with default.
//    *Defined explicitly, to facilitate calling from Java.*/
//  def this(data: Array[V]) = this(data, true)

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" dimensions and other property accessors ">

  override lazy val dimension: Int = dataClone.length

  override def dimension(x1: Int): Int = {
    val instantiated = Indices.instantiate( index = x1, dimensionLength = dimension)
    loggerRequire( 0 <= instantiated && instantiated < dimension, s"dimension x1 OOB: $x1 (length: ${dimension})" )
    dataClone(x1).length
  }

  //ToDo: first dimension length zero...?
  override lazy val dimensions = Array( dimension, dimension(0))

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Array/Vector/DenseVector accessors ">

  override def toArray: Array[Array[V]] = dataClone.clone().map( _.clone() )
  override def toVector: Vector[Vector[V]] = dataClone.toVector.map( _.toVector )
  override def toDenseMatrix: DenseMatrix[V] = {
    new DenseMatrix[V]( /*rows =*/ dimensions(1), /*cols =*/ dimensions(0), /*data =*/ flatten() )
  }
  override def flatten(): Array[V] = {
    val tempReturn = classTag.newArray(dimensions(0)*dimensions(1))
    var c = 0
    for( x1 <- 0 until dimension )
      for( x2 <- 0 until dimension(x1) ) {
        tempReturn( 0 ) = apply(x1, x2)
        c += 1
      }
    tempReturn
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" apply ">

  override def apply(x1: Int, x2: Int): V = dataClone( x1 )( x2 )
  override def apply(i1: Indices, x2: Int): Dense1[V] = new Dense1( i1.instantiate( dimension ).toArray.map( data( _ )( x2 ) ) )
  override def apply(x1: Int, i2: Indices): Dense1[V] = ???
  override def apply(i1: Indices, i2: Indices): DenseRegular2[V] = {
    new DenseRegular2(
      i1.instantiate( dimension ).toArray.map( (x1: Int) => {
        i2.instantiate( dimension( x1) ).toArray.map( apply( x1, _) )
      }),
      false
    )
  }

  // </editor-fold>


  override def equals[T](matrix: Matrix[T]): Boolean = ???

}
