package breeze.linalg.immutable

import breeze.linalg.indexing.{Indexer, Slicer}

import scala.{specialized=>spec}
import breeze.linalg.{Dimensions}

/**
 * Created by ktakagaki on 15/04/09.
 */
trait Matrix[@spec(Double, Int, Float, Long) V/*, Ord <: Order*/] {

 /** Matrix dimensions. Order zero (wrapped scalar) will return an empty Array[Int]. */
  def dimensions(): Dimensions//[Ord]
  /** Matrix dimensions. Order zero (wrapped scalar) will return an empty Array[Int]. */
  final def getDimensions() = dimensions()

  def apply(slicer: Slicer*): V
//  def apply(indices: Int*): V
//  def apply(indices: Indices[Ord]): Matrix[V, OutputOrder]

  def equals(matrix: Matrix): Boolean = ???
  def reshape(): Matrix = ???

  def iterator: Iterator[(Int, V)] = ???
  def activeIterator: Iterator[(Int, V)] = ???
  def valuesIterator: Iterator[V] = ???
  def activeValuesIterator: Iterator[V] = ???
  def keysIterator: Iterator[Int] = ???
  def activeKeysIterator: Iterator[Int] = ???

  //ToDo: Make function delete as in DenseMatrix, perhaps not within object but as delete( DenseMatrixB, Int, Axis)

}