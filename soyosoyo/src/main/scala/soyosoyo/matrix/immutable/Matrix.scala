package soyosoyo.matrix.immutable

import breeze.linalg.DenseVector
import soyosoyo.subroutines.LoggingExtension
import soyosoyo.types.Indices

import scala.reflect.ClassTag
import scala.{specialized => spec}

/**
  * [[soyosoyo.matrix.immutable.Matrix Matrix]] is the parent of all
  * matrix traits in soyosoyo. It is inherited by [[Irregular immutable.Irregular]], which adds
  * accessors which do not assume a regular matrix, and this is further
  * inherited by [[soyosoyo.matrix.immutable.Regular Regular]],
  * which adds accessors which apply only to regular (non-ragged) matrices.
  *
  * This hierarchy of traits is also inherited and extended in individual implementations
  * together with [[soyosoyo.matrix.mutable.Mutable Mutable]], which includes mutation methods,
  * and implementations such as Dense and Sparse.
  *
  */
trait Matrix[@spec(Int, Long, Float, Double) V] extends LoggingExtension {

  // <editor-fold defaultstate="collapsed" desc=" dimensions and other property accessors ">

  /**Order of the object. This is immutable, even in mutable
    * matrix-like objects.
    */
  val order: Int

  /**Whether matrix is regular or ragged*/
  val isRegular: Boolean

  // </editor-fold>

  def flatten(): Array[V]

  def equals[T](matrix: Matrix[T]): Boolean

  implicit val classTag: ClassTag[V]
  lazy val tpe = classTag.runtimeClass

}

trait Matrix1[@spec(Int, Long, Float, Double) V]  extends Matrix[V] with Regular[V] {

  def apply(x1: Int): V
  def apply(i1: Indices): Matrix1[V]

  //  //self type infers that this trait assumes mixin to Matrix
  //  //this construct allows a mixin trait to use class functionality without inheriting the parent class
  //  this: Matrix[V, Dimensions1] =>

  final override val order = 1

  // <editor-fold defaultstate="collapsed" desc=" Array/Vector/DenseVector accessors ">

  /** Gives Array representation.
    * Scala signature must be managed carefully given override of scala collections*/
  def toArray: Array[V]
  /** Gives Vector representation.
    * Scala signature must be managed carefully given override of scala collections*/
  final def toVector: Vector[V] = toArray.toVector
  final def toDenseVector: DenseVector[V] = new DenseVector( toArray )

  // </editor-fold>

  def length(): Int

  def iterator: Iterator[V]

}


trait Matrix2[@spec(Int, Long, Float, Double) V]  extends Matrix[V] {

  final override val order = 2

  def apply(x1: Int,     x2: Int): V
  def apply(i1: Indices, x2: Int): Matrix1[V]
  def apply(x1: Int,     i2: Indices): Matrix1[V]
  def apply(i1: Indices, i2: Indices): Matrix2[V]

}