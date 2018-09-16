package soyosoyo.statistics

import soyosoyo.matrix.immutable.{Dense, Dense1, Matrix1}
import soyosoyo.matrix.types.Span2I
import soyosoyo.subroutines.LoggingExtension

import scala.reflect.ClassTag

/**
  * Random sampling from base data with repetition.
  */
object RandomChoice extends LoggingExtension{

  def randomChoice[V: ClassTag]( data: Matrix1[V], n: Int): Dense1[V] = {
    new Dense1( randomChoice( data.toArray, n) )
  }

  def randomChoice[V: ClassTag]( data: Array[V], n: Int): Array[V] = {
      Array
        .tabulate[Int]( n )( (n: Int) => soyosoyo.random( Span2I( 0, data.length - 1) )  )
        .map( data(_) )
  }

  def randomChoice[V: ClassTag]( data: Matrix1[V], weights: Matrix1[Double], n: Int): Dense1[V] = {
    new Dense1( randomChoice( data.toArray, weights.toArray, n ))
  }

  def randomChoice[V: ClassTag]( data: Array[V], weights: Array[Double], n: Int ): Array[V] = {

    loggerRequire( data.length >= 1, "Give non-zero number of samples to take from." )
    loggerRequire( weights.length == data.length, "Number of weights specified differs from data length." )
    loggerRequire( weights.forall( _ >= 0d ), "Weights must all be >= 0d." )

    //update cumulative weighting vector
    var weightsBuff = weights.clone
    var cum = 0d
    for( c <- 0 until weights.length ){
      cum += weights(c)
    }
    var weightsNormBuff = weightsBuff.map( _ / cum )

    (for( c <- 0 until n ) yield {
      var rand = soyosoyo.random()
      var sampNo = 0
      while( rand >= 0 && sampNo < weights.length ){
        rand -= weightsNormBuff( sampNo )
        sampNo += 1
      }
      data( sampNo - 1 )
    }).toArray

  }

}
