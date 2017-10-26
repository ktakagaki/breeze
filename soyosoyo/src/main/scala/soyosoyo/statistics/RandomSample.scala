package soyosoyo.statistics

import breeze.linalg.sum
import breeze.macros.expand

import scala.{specialized => spec}
import soyosoyo._
import soyosoyo.matrix.immutable.{Dense, Dense1, Matrix1}
import soyosoyo.subroutines.LoggingExtension
import soyosoyo.types.{IndicesSingle, IndicesSingleInstantiated}

import scala.annotation.tailrec
import scala.reflect.ClassTag

object RandomSample extends LoggingExtension {

  /**
    * Sampling in random order from base data, with no two samples repeated.
    */
//  @expand
//  def randomSample[@expand.args(Double, Float, Int, Long) V: ClassTag](
  def randomSample[V: ClassTag]( data: Matrix1[V], n: Int ): Dense1[V] = {

    loggerRequire( n <= data.length, "Length of selection must be shorter than data length" )
    new Dense1[V]( randomSample( data.toArray, n) )

  }

//  @expand
//  def randomSample[@expand.args(Double, Float, Int, Long) V: ClassTag](
  def randomSample[V: ClassTag]( data: Array[V], n: Int ): Array[V] = {

    val tempret = scala.util.Random.shuffle( data.toVector )
    tempret.slice(0, n).toArray

  }

//  @expand
//  def randomSample[@expand.args(Double, Float, Int, Long) V: ClassTag](
  def randomSample[V: ClassTag]( data: Matrix1[V], weights: Matrix1[Double], n: Int ): Dense1[V] = {

    new Dense1[V]( randomSample( data.toArray, weights.toArray, n) )

  }

//  @expand
//  def randomSample[@expand.args(Double, Float, Int, Long) V: ClassTag](
  def randomSample[V: ClassTag]( data: Array[V], weights: Array[Double], n: Int ): Array[V] = {

    loggerRequire( n <= data.length, "Length of selection must be shorter than data length" )
    loggerRequire( weights.length == data.length, "Number of weights specified differs from data length." )
    loggerRequire( weights.forall( _ >= 0d ), "Weights must all be >= 0d." )

    randomSampleImpl(data, weights, Array[V](), n).toArray

  }

  // <editor-fold defaultstate="collapsed" desc=" randomSampleImpl ">

  @tailrec
//  @expand
//  private def randomSampleImpl[@expand.args(Double, Float, Int, Long) V: ClassTag](
  private def randomSampleImpl[V: ClassTag](
      data: Array[V], weights: Array[Double], result: Array[V], n: Int): Array[V] = {

    if( n <= 0 ) result
    else {
      val cum = sum( weights )
      val weightsNorm = weights.map( _ / cum )

      var sampNo = 0
      var rand = random()
      rand -= weightsNorm( 0 )
      while( rand >= 0 && sampNo < data.length ) {
        sampNo += 1
        rand -= weightsNorm( sampNo )
      }
      randomSampleImpl(
        delete( data, IndicesSingle( sampNo ) ),
        delete( weights, IndicesSingle( sampNo ) ),
        result :+ data( sampNo ),
        n - 1 )
    }

  }
  // </editor-fold>


}
