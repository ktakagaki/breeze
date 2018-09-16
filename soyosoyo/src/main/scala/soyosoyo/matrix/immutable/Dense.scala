package soyosoyo.matrix.immutable

import breeze.linalg.DenseVector
import breeze.macros.expand
import breeze.math.Complex
import soyosoyo._
import soyosoyo.matrix.types.Indices

import scala.reflect.ClassTag
import scala.{specialized => spec}

object Dense {

  @expand
  def dense[@expand.args( Int, Long, Float, Double, Complex) V: ClassTag]( data: Array[Array[V]] ) : Irregular2[V] = {
    val tempret = new DenseIrregular2[V]( data )
    if( tempret.isRegular ) tempret.toDenseRegular() else tempret
  }

  @expand
  def dense[@expand.args( Int, Long, Float, Double, Complex) V: ClassTag]( data: Array[V] ): Dense1[V] =
    new Dense1[V]( data )
  //def dense[@expand.args( Int, Long, Float, Double, Complex) V]( data: Array[V] )(implicit classTag: ClassTag[V]): Dense1[V] = new Dense1[V]( data )


}

/**
  *
  * Dense [irregular] matrix 1D.
  *
  * @param data pass in real data
  * @param cloneData whether to clone data input before use. 'true' as default will create protective copy to ensure
  *              immutability, but 'false' may be used with care, in order to save memory overhead.
  * @tparam V type class, defined in Matrix[V]
  */
final class Dense1[@spec( Int, Long, Float, Double) V]
  (val data: Array[V], cloneData: Boolean = true )
  ( implicit val classTag: ClassTag[V] )
  extends Matrix1[V]  {

  // <editor-fold defaultstate="collapsed" desc=" constructor-related ">

  loggerRequire( data != null, "data cannot be null" )
  private val dataClone: Array[V] = if( cloneData ) data.clone() else data

//  /**Alternate constructor with default.
//    *Defined explicitly, to facilitate calling from Java.*/
//  def this(data: Array[V]) = {
//    implicit classTag: ClassTag[V]
//    this(data, true)
//  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Array/Vector/DenseVector accessors ">

  override def toArray: Array[V] = dataClone.clone()
  override def flatten() = this.toArray

  // </editor-fold>

  override lazy val length = dataClone.length
  override lazy val dimensions = Array( length )


  override def apply(x1: Int) = dataClone(x1)
  override def apply(r: Indices) =
    new Dense1( r.instantiate(length).toArray.map( dataClone(_) ) )

  override def iterator: Iterator[V] = dataClone.iterator

  def equals( that: Dense1[V] ): Boolean = {
    isEqual( this.toArray, that.toArray )
  }
  override def equals[T](that: Matrix[T]): Boolean = false

}
