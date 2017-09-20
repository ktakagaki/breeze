package soyosoyo.types.immutable

import breeze.generic.UFunc
import breeze.linalg.indexing.{Indexer, SliceRange, Slicer}
import breeze.linalg._
import breeze.linalg.support.CanTraverseValues
import breeze.linalg.support.CanTraverseValues.ValuesVisitor
import breeze.math.Complex
import breeze.util.SerializableLogging

import scala.{specialized => spec}
import scala.reflect.runtime.universe._

/**
  * Trait describing an immutable square (non-ragged) multidimensional array.
  *
 * Created by ktakagaki on 15/04/09.
 */
trait ViewMD[@spec(Double, Int, Float, Long, Complex) V,
             D <: Dimensions] extends ArrayMD {


}

class View1D_2D[@spec(Double, Int, Float, Long, Complex) V]
  extends Array1D[V](
    val ori: Array2D[V], val
  ) {

  def apply(dim1: Int): V
  def apply(ran1: SliceRange): Tensor1

  def map(ufunc: UFunc): Tensor1 =

  implicit def canTraverseValues[V]: CanTraverseValues[Tensor1[V], V] = {

    new CanTraverseValues[Tensor1[V], V] {

      def isTraversableAgain(from: Tensor1[V]): Boolean = true

      /** Iterates all key-value pairs from the given collection. */
      def traverse(from: Tensor1[V], fn: ValuesVisitor[V]): Unit = {
        import from._
        val idealMajorStride = if(isTranspose) cols else rows

        if(majorStride == idealMajorStride) {
          fn.visitArray(data, offset, rows*cols, 1)
        } else if(!from.isTranspose) {
          var j = 0
          while (j < from.cols) {
            fn.visitArray(data, offset + j * majorStride, rows, 1)
            j += 1
          }
        } else {
          var j = 0
          while (j < from.cols) {
            var i = 0
            while(i < from.rows) {
              fn.visit(from(i, j))
              i += 1
            }
            j += 1
          }
        }
      }

    }
  }
}

trait Array2D[@spec(Double, Int, Float, Long, Complex) V]
  extends ArrayMD[V, Dimensions2] {

  def apply(dim1: Int, dim2: Int): V
  def apply(dim1: Int, ran2: SliceRange): Tensor1
  def apply(ran1: SliceRange, ran2: SliceRange): Tensor2

}

//trait Tensor3[@spec(Double, Int, Float, Long) V] extends ArrayMD[V, Dimensions3] {
//
//  def apply(dim1: Int, dim2: Int, dim3: Int): V
//
//  def apply(dim1: Int, dim2: Int, ran3: SliceRange): Tensor1
//  def apply(dim1: Int, ran2: SliceRange, dim3: Int): Tensor1
//  def apply(ran1: SliceRange, dim2: Int, dim3: Int): Tensor1
//
//  def apply(dim1: Int, ran2: SliceRange, ran3: SliceRange): Tensor2
//  def apply(ran1: SliceRange, dim2: Int, ran3: SliceRange): Tensor2
//  def apply(ran1: SliceRange, ran2: SliceRange, dim3: Int): Tensor2
//
//  def apply(ran1: SliceRange, ran2: SliceRange, ran3: SliceRange): Tensor3
//
//}

