//package soyosoyo.matrix.indexing
//
//
////ToDo: ragged dimensions
//
//object Dimensions{
////  implicit def implIntToDimensions1(i: Int) = Dimensions1(i)
////  implicit def implIntIntToDimensions2( i: (Int, Int)) = Dimensions2(i._1, i._2)
//}
//
///**
//  * Encapsulates multidimensional array dimensions.
//  * Different child classes are provided for each array rank,
//  * to facilitate static type inference during mapping
//  * and other dimensionality shifting functions.
//  *
//  * Created by ktakagaki on 15/10/14.
//  */
//abstract class Dimensions{
//  val order: Int
//  val totalElements: Int
//  def toArray: Array[Int]
//}
//
///**Marks scalar value*/
//trait Dimensions0 extends Dimensions {
//  override val order = 0
//  override val totalElements = 1
//  override lazy val toArray = Array[Int]()
//}
//
//trait Dimensions1 extends Dimensions{
//
//  assert( dim1 >= 0, "Dimensions must be larger than zero")
//
//  override val order = 1
//  override val totalElements = dim1
//  override lazy val toArray = Array(dim1)
//}
//
//case class Dimensions2(val dim1: Int, val dim2: Int) extends Dimensions{
//
//  assert( dim1 >= 0, "Dimensions must be larger than zero")
//  assert( dim2 >= 0, "Dimensions must be larger than zero")
//
//  override val order = 2
//  override lazy val totalElements = dim1 * dim2
//  override lazy val toArray = Array(dim1, dim2)
//}
//
//case class Dimensions3(val dim1: Int, val dim2: Int, val dim3: Int)
//  extends Dimensions {
//
//  assert( dim1 >= 0, "Dimensions must be larger than zero")
//  assert( dim2 >= 0, "Dimensions must be larger than zero")
//  assert( dim3 >= 0, "Dimensions must be larger than zero")
//
//  override val order = 3
//  override lazy val totalElements = dim1 * dim2 * dim3
//  override lazy val toArray = Array(dim1, dim2, dim3)
//}