package soyosoyo.matrix.immutable

import breeze.linalg.DenseMatrix
import soyosoyo.types.Indices

import scala.{specialized => spec}


trait Irregular[@spec(Int, Long, Float, Double) V] extends Matrix[V]

// There is no such thing as an irregular 1 dimensional array,
// return and deal with Regular1 instead, promotion to Regular hierarchy
// (child traits) is OK.
//
//trait Irregular1[@spec(Int, Long, Float, Double) V]
//  extends Irregular[V] with Iterable[V]

trait Irregular2[@spec(Int, Long, Float, Double) V] extends Matrix2[V] with Irregular[V] {

  // <editor-fold defaultstate="collapsed" desc=" dimensions and other property accessors ">


  /**Returns first dimension in ragged object.*/
  def dimension(): Int

  /**Returns second dimension in ragged object.*/
  def dimension(x1: Int): Int

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Array/Vector/DenseMatrix accessors ">

  def toArray(): Array[Array[V]]
  def toVector(): Vector[Vector[V]]
  def toDenseMatrix(): DenseMatrix[V]

  // </editor-fold>


}
