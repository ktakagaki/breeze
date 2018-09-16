package soyosoyo.matrix.immutable

import breeze.linalg.DenseVector
import soyosoyo.matrix.types.Indices

import scala.{specialized => spec}

trait Regular[@spec( Int, Long, Float, Double) V] extends Irregular[V] {

  final override val isRegular = true

  /**An array of dimensions.*/
  def dimensions(): Array[Int]

}

trait Regular2[@spec(Int, Long, Float, Double) V]
  extends Matrix2[V] with Regular[V] with Irregular2[V] {

}
