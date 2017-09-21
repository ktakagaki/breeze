package soyosoyo.statistics

import soyosoyo.matrix.immutable.{Dense, Dense1}
import soyosoyo.matrix.indexing.Dimensions1

package object random {

  def randomI(): Int = scala.util.Random.nextInt()
  def randomI(range: Range): Int = {
    val rangeSpan = range.end - range.start
    scala.util.Random.nextInt(rangeSpan) + range.start
  }

  def randomI1(range: Range, dimensions: Dimensions1): Dense1[Int] = {
    val rangeSpan = range.end - range.start
    val tempret = Array.tabulate[Int](dimensions.dim1)(
      (i: Int) => scala.util.Random.nextInt(rangeSpan) + range.start
    )
    Dense( tempret )
  }

  def randomD(): Double = scala.util.Random.nextDouble()
  def randomD1(n: Int): Dense1[Double] = {
    val tempret = Array.tabulate[Double](n)( (i: Int) => scala.util.Random.nextInt() )
    Dense( tempret )
  }

}
