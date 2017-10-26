import breeze.macros.expand
import breeze.math.Complex
import soyosoyo.matrix.immutable.{Dense1, Matrix1}
import soyosoyo.statistics.Random
import soyosoyo.subroutines.LoggingExtension
import soyosoyo.types._

import scala.reflect.ClassTag

/** Soyosoyo is an attempt to provide an improved API for the functionality provided by
  * Scala breeze.
  *
  * <h2>Key Changes</h2>
  * Key changes from current Scala breeze syntax include:
  *
  * - Matrix objects are multidimensional, with class denoting order (e.g. Matrix1, Matrix2, Matrix3, ...).
  *
  * - Default Matrix objects are immutable (and not necessarily regular).
  *   Mutable Matrix objects inherit from their immutable equivalents. This
  *   allows mutable and immutable matrices to be used interchangeably on right hand side.
  *
  * - Updated indexing, slicing, and view syntax to facilitate multidimensionality and mutability.
  *
  * - Speed up compilation by simplifying inheritance hierarchy and implicit resolution
  *   - As a rule, Class signatures are fixed and not be inferred
  *   - Mapping and other dimension-changing operations are functional
  *     (attempt to simplify [[breeze.generic.UFunc UFunc]]).
  *
  * - Full accessibility (except for fancy syntactic sugar) from plain Java7
  *
  * - Applicability to POJOs (Array[]) where possible.
  *
  * <h2>Basic use</h2>
  * All commonly used functionality is available through package objects:
  * {{{
  * import soyosoyo._
  * }}}
  * (These package objects provide aliases to the actual implementations, which are defined in
  * [[soyosoyo.matrix]], [[soyosoyo.statistics]], [[soyosoyo.math]], [[soyosoyo.graphics]], etc.)
  *
  * <h2>Documentation</h2>
  * The main documentation will be in Scaladoc.
  *
  *
  */
package object soyosoyo extends LoggingExtension {

  // <editor-fold defaultstate="collapsed" desc=" implicits for IndicesRange and span ">

  val %% = IndicesAll

  implicit def intToIndexRangeImplicitConstructor(start: Int): IndexRangeImplicitConstructor = {
    new IndexRangeImplicitConstructor(start)
  }

  class IndexRangeImplicitConstructor(start: Int){
    def %%( last: Int ): IndicesRange = IndicesRange( start, last )
  }

  final def span() = IndicesAll
  final def span(start: Int, last: Int) = IndicesRange(start, last)
  final def span(start: Int, last: Int, step: Int) = IndicesRange(start, last, step)

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" direct programming ">

  // <editor-fold defaultstate="collapsed" desc=" convert ">

  final def convert(data: Array[Int], tpe: Double): Array[Double] = data.map( _.toDouble )
  final def convert(data: Array[Long], tpe: Double): Array[Double] = data.map( _.toDouble )
  final def convert(data: Array[Float], tpe: Double): Array[Double] = data.map( _.toDouble )
  final def convert(data: Array[Double], tpe: Double): Array[Double] = data
  final def convert(data: Array[Complex], tpe: Double): Array[Double] = {
    loggerWarn( "Converting complex into Double, taking only real part.")
    data.map( _.real )
  }
  final def convert[V <: Any](data: Array[V], tpe: Double): Array[Double] = {
    data(0) match {
      case x: Int => data.asInstanceOf[Array[Int]].map( _.toDouble )
      case x: Long => data.asInstanceOf[Array[Long]].map( _.toDouble )
      case x: Float => data.asInstanceOf[Array[Float]].map( _.toDouble )
      case x: Double => data.asInstanceOf[Array[Double]]
      case x: Complex => {
        loggerWarn( "Converting complex into Double, taking only real part.")
        data.asInstanceOf[Array[Complex]].map( _.real )
      }
      case _ => throw loggerError("Incorrect array component type")
    }
  }
  final def convert[V](data: Matrix1[V], tpe: Double)(implicit classTag: ClassTag[V]): Matrix1[Double] =
    new Dense1( convert( data.toArray, tpe) )

  // </editor-fold>

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" soyosoyo.matrix ">

  import soyosoyo.matrix

  // <editor-fold defaultstate="collapsed" desc=" delete ">

  @expand
  final def delete[@expand.args(Double, Float, Int, Long) V: ClassTag](
      data: Matrix1[V],
      indexRange: Indices): Matrix1[V] = matrix.Delete.delete( data, indexRange )

  final def delete[V: ClassTag]( data: Array[V], indexRange: Indices): Array[V] = matrix.Delete.delete( data, indexRange)
//  final def delete( data: Array[Int], indexRange: Indices): Array[Int] = matrix.Delete.delete( data, indexRange)
//  final def delete( data: Array[Long], indexRange: Indices): Array[Long] = matrix.Delete.delete( data, indexRange)
//  final def delete( data: Array[Float], indexRange: Indices): Array[Float] = matrix.Delete.delete( data, indexRange)
//  final def delete( data: Array[Double], indexRange: Indices): Array[Double] = matrix.Delete.delete( data, indexRange)
//  final def delete( data: Array[Complex], indexRange: Indices): Array[Complex] = matrix.Delete.delete( data, indexRange)

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc=" isEqual ">

  final def isEqual[V: ClassTag, W: ClassTag](
    data1: Array[V], data2: Array[W] ):Boolean = matrix.IsEqual.isEqual( data1, data2 )

  // </editor-fold>

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" soyosoyo.statistics ">

  import soyosoyo.statistics

  // <editor-fold defaultstate="collapsed" desc=" random ">

  def random(range: RangeInclusiveI): Int = Random.random( range )

  def random(): Double = Random.random()

  def random(range: RangeInclusiveD): Double = Random.random( range)

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc=" randomSample ">

  def randomSample[V: ClassTag]( data: Matrix1[V] ): Dense1[V] = soyosoyo.statistics.RandomSample.randomSample(data, 1)
  def randomSample[V: ClassTag]( data: Matrix1[V], n: Int): Dense1[V] = soyosoyo.statistics.RandomSample.randomSample(data, n)
  def randomSample[V: ClassTag]( data: Array[V] ): Array[V] = soyosoyo.statistics.RandomSample.randomSample(data, 1)
  def randomSample[V: ClassTag]( data: Array[V], n: Int): Array[V] = soyosoyo.statistics.RandomSample.randomSample(data, n)

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc=" randomChoice ">
//
//  @expand
//  def randomChoice[@expand.args(Double, Float, Int, Long, Complex) V](
//      data: Matrix1[V]): Dense1[V] = soyosoyo.statistics.RandomChoice.randomChoice(data, 1)
//  @expand
//  def randomChoice[@expand.args(Double, Float, Int, Long, Complex) V](
//     data: Matrix1[V],
//     n: Int): Dense1[V] = soyosoyo.statistics.RandomChoice.randomChoice(data, n)
//  @expand
//  def randomChoice[@expand.args(Double, Float, Int, Long, Complex) V](
//      data: Array[V]): Array[V] = soyosoyo.statistics.RandomChoice.randomChoice(data, 1)
//  @expand
//  def randomChoice[@expand.args(Double, Float, Int, Long, Complex) V](
//     data: Array[V],
//     n: Int): Array[V] = soyosoyo.statistics.RandomChoice.randomChoice(data, n)
//  @expand
//  def randomChoice[@expand.args(Double, Float, Int, Long, Complex) V](
//      data: Matrix1[V],
//      weights: Matrix1[V],
//      n: Int): Array[V] = Dense( soyosoyo.statistics.RandomChoice.randomChoice(data.toArray, convert(weights, 0d), n) ).toArray
//  @expand
//  def randomChoice[@expand.args(Double, Float, Int, Long, Complex) V](
//     data: Array[V],
//     weights: Array[Double],
//     n: Int): Array[V] = soyosoyo.statistics.RandomChoice.randomChoice(data, weights, n)


  // </editor-fold>

  // </editor-fold>

}
