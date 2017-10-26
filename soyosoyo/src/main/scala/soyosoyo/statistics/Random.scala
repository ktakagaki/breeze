package soyosoyo.statistics

import soyosoyo.matrix.immutable.{Dense, DenseIrregular, Dense1}
import soyosoyo.matrix.indexing.{Indexer1}
import soyosoyo.subroutines.LoggingExtension
import soyosoyo.types.{RangeInclusiveD, RangeInclusiveI}


object Random extends LoggingExtension {

  def random(range: RangeInclusiveI): Int = {
    val rangeSpan = range.x2 - range.x1
    scala.util.Random.nextInt(rangeSpan) + range.x1
  }

//  def apply(range: RangeInclusiveInt, dimensions: Dimensions1): DenseIrregular1[Int] = {
//    val rangeSpan = range.x2 - range.x1
//    val tempret = Array.tabulate[Int](dimensions.dim1)(
//      (i: Int) => scala.util.Random.nextInt(rangeSpan) + range.x1
//    )
//    Dense( tempret )
//  }

  def random(): Double = scala.util.Random.nextDouble()

  def random(range: RangeInclusiveD): Double = {
    val rangeSpan = range.x2 - range.x1
    scala.util.Random.nextDouble()*rangeSpan + range.x1
  }

//  def apply(range: RangeInclusiveDouble, dimensions: Dimensions1): Dense1[Double] = {
//    val rangeSpan = range.x2 - range.x1
//    val tempret = Array.tabulate[Double](dimensions.dim1)(
//      (i: Int) => scala.util.Random.nextDouble()*rangeSpan + range.x1
//    )
//    DenseIrregular( tempret )
//  }

}
