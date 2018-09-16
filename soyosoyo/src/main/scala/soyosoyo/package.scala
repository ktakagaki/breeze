import breeze.math.Complex
import soyosoyo.matrix.immutable.{Dense1, Matrix1}
import soyosoyo.subroutines.LoggingExtension
import soyosoyo.matrix.types._

import scala.reflect.ClassTag

/** Soyosoyo is an attempt to provide an improved API for the functionality provided by
  * Scala breeze.
  *
  * <h2>Key Changes</h2>
  * Key changes from the standard Scala breeze syntax and architecture include:
  *
  * - Matrix objects are multidimensional, with class denoting order (e.g. Matrix1, Matrix2, Matrix3, ...).
  *   This is meant to facilitate incorporation of higher dimensional objects into breeze.
  *
  * - Updated indexing, slicing, and view syntax to facilitate multidimensionality and mutability.
  *
  * - Default Matrix objects are immutable (and not necessarily regular).
  *   Mutable Matrix objects inherit from their immutable equivalents. This
  *   allows mutable and immutable matrices to be used interchangeably on right hand side,
  *   while only mutable matrices can occupy the "left hand side".
  *
  *   {{{
  *     import soyosoyo._
  *     val mutable = DenseMutable( {{1, 2, 3, 4}, {4, 5, 6, 7}} )
  *     mutable.view( %% , 2 %% 3 ) := DenseImmutable({{1, 1}, {1, 1}
  *   }}}
  *
  * - Row major syntax, to facilitate higher dimensional Matrices
  *   (maintaining compatibility with standard Java/Scala Array indexing)
  *
  * - Speed up compilation by simplifying inheritance hierarchy and implicit resolution
  *   - As a rule, Class signatures are fixed and not be inferred
  *   - Mapping and other dimension-changing operations are functional
  *     (attempt to simplify [[breeze.generic.UFunc UFunc]]).
  *
  * - Full accessibility (except for fancy syntactic sugar) from plain Java7
  *
  * - Functions are applicable to POJO/POSOs (Array[], mutable) and POSOs (Vector[], immutable) where possible.
  *   (All functions, except assignments {{{:=}}} and XxxxTo functions {{{addTo}}}, {{{multiplyTo}}}, etc.
  *   are immutable, and for Arrays and Mutable soyosoyo objects, will return clones)
  *
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

//  implicit def intToIndexRangeImplicitConstructor(start: Int): IndexRangeImplicitConstructor = {
//    new IndexRangeImplicitConstructor(start)
//  }
//
//  class IndexRangeImplicitConstructor(start: Int){
//    def %%( last: Int ): IndicesRange = IndicesRange( start, last )
//  }

  implicit def spanToIndicesRangeImplicit(span: Span2I): IndicesRange = {
    new IndicesRange( start = span.x1, last = span.x2)
  }

  implicit def intToSpanImplicitConstructor(start: Int): SpanImplicitConstructor = {
    new SpanImplicitConstructor(start)
  }

  class SpanImplicitConstructor(start: Int){
    def %%( last: Int ): Span2I = Span2I( start, last )
  }

  final def span() = IndicesAll
  final def span(start: Int, last: Int) = Span2I(start, last)
  final def span(start: Int, last: Int, step: Int) = Span3I(start, last, step)

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
  /**Inelegant but fixed signature generic*/
  final def convert[V <: Any](data: Array[V], tpe: Double): Array[Double] = {
    data(0) match {
      case x: Int => convert( data.asInstanceOf[Array[Int]], tpe )
      case x: Long => convert( data.asInstanceOf[Array[Long]], tpe )
      case x: Float => convert( data.asInstanceOf[Array[Float]], tpe )
      case x: Double => convert( data.asInstanceOf[Array[Double]], tpe )
      case x: Complex => convert( data.asInstanceOf[Array[Complex]], tpe )
      case _ => throw loggerError("Incorrect array component type")
    }
  }
  //ToDo 2: extend for non-dense types
  final def convert[V: ClassTag](data: Matrix1[V], tpe: Double): Matrix1[Double] =
    new Dense1( convert( data.toArray, tpe) )

  // </editor-fold>

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" soyosoyo.matrix ">

  // <editor-fold defaultstate="collapsed" desc=" delete ">

  import soyosoyo.matrix.Delete

  //  @expand
  final def delete[ V: ClassTag](//@expand.args(Double, Float, Int, Long) V: ClassTag](
      data: Matrix1[V], indexRange: Indices): Matrix1[V] =
    Delete.delete( data, indexRange )

  final def delete[V: ClassTag]( data: Array[V], indexRange: Indices): Array[V] =
    Delete.delete( data, indexRange)

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc=" isEqual ">

  final def isEqual[V: ClassTag, W: ClassTag](
    data1: Array[V], data2: Array[W] ):Boolean = matrix.IsEqual.isEqual( data1, data2 )

  // </editor-fold>

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" soyosoyo.statistics ">

  // <editor-fold defaultstate="collapsed" desc=" random ">

  import soyosoyo.statistics.Random

  def random(range: Span2I): Int = Random.random( range )

  def random(): Double = Random.random()

  def random(range: Span2D): Double = Random.random( range)

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc=" randomSample ">

  import soyosoyo.statistics.RandomSample

  def randomSample[V: ClassTag]( data: Matrix1[V] ): Dense1[V] =
    RandomSample.randomSample(data, 1)
  def randomSample[V: ClassTag]( data: Matrix1[V], n: Int): Dense1[V] =
    RandomSample.randomSample(data, n)
  def randomSample[V: ClassTag]( data: Matrix1[V], weights: Matrix1[Double], n: Int): Dense1[V] =
    RandomSample.randomSample(data, weights, n)
  def randomSample[V: ClassTag]( data: Array[V] ): Array[V] =
    RandomSample.randomSample(data, 1)
  def randomSample[V: ClassTag]( data: Array[V], n: Int): Array[V] =
    RandomSample.randomSample(data, n)
  def randomSample[V: ClassTag]( data: Array[V], weights: Array[Double], n: Int): Array[V] =
    RandomSample.randomSample(data, weights, n)

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc=" randomChoice ">

  import soyosoyo.statistics.RandomChoice

  def randomChoice[V: ClassTag]( data: Matrix1[V] ): Dense1[V] =
    RandomChoice.randomChoice(data, 1)
  def randomChoice[V: ClassTag]( data: Matrix1[V], n: Int): Dense1[V] =
    RandomChoice.randomChoice(data, n)
  def randomChoice[V: ClassTag]( data: Matrix1[V], weights: Matrix1[Double], n: Int): Dense1[V] =
    RandomChoice.randomChoice(data, weights, n)
  def randomChoice[V: ClassTag]( data: Array[V] ): Array[V] =
    RandomChoice.randomChoice(data, 1)
  def randomChoice[V: ClassTag]( data: Array[V], n: Int ): Array[V] =
    RandomChoice.randomChoice(data, n)
  def randomChoice[V: ClassTag]( data: Array[V], weights: Array[Double], n: Int): Array[V] =
    RandomChoice.randomChoice(data, weights, n)

  // </editor-fold>

  // </editor-fold>

}
