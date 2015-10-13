//package breeze.linalg.immutable
//
//import breeze.linalg.product
//import breeze.linalg.traits.{Indices3, Indices}
//
//import scala.collection.mutable
//import scala.{specialized=>spec}
//
///**
// * Created by ktakagaki on 15/04/09.
// */
//trait Matrix[@spec(Double, Int, Float, Long) V] {
//
//  /** Order of the matrix, from 0(wrapped scalar) to 1(vector) */
//  def order(): Int
//  /** Alias for [[order()]]*/
//  final def getOrder(): Int = order()
//
//  /** Matrix dimensions. Order zero (wrapped scalar) will return an empty Array[Int]. */
//  def dimensions(): Array[Int]
//  /** Matrix dimensions. Order zero (wrapped scalar) will return an empty Array[Int]. */
//  final def getDimensions(): Array[Int] = dimensions()
//
//  /** This is the central point to implement for all children.
//    * it should be implemented as a var or a val, such that the JVM can inline.
//    */
//  def toLinearIndexChecked(): (Seq[Int] => Int)
//  def toLinearIndexUnchecked(): (Seq[Int] => Int)
//
//  /** This is the central point to implement for all children.
//    * it should be implemented as a var or a val, such that the JVM can inline.
//    */
//  def toIndices(): (Int => Seq[Int])
//
//  def apply(indices: Int*): V
//
//  def toArray(): Array = ???
//  def equals(matrix: Matrix): Boolean = ???
//  def reshape(): Matrix = ???
//
//  def iterator: Iterator[(Int, V)] = ???
//  def activeIterator: Iterator[(Int, V)] = ???
//  def valuesIterator: Iterator[V] = ???
//  def activeValuesIterator: Iterator[V] = ???
//  def keysIterator: Iterator[Int] = ???
//  def activeKeysIterator: Iterator[Int] = ???
//
//  //ToDo: Make function delete as in DenseMatrix, perhaps not within object but as delete( DenseMatrixB, Int, Axis)
//
//}