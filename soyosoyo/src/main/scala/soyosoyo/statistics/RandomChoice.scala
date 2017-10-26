//package soyosoyo.statistics
//
//import breeze.linalg.Options.Dimensions1
//import breeze.macros.expand
//import breeze.math.Complex
//import soyosoyo.matrix.Matrix1
//import soyosoyo.matrix.immutable.{Dense, Dense1, Matrix1, MatrixArray1}
//import soyosoyo.matrix.indexing.Indexer1
//import soyosoyo.subroutines.LoggingExtension
//import soyosoyo.types.RangeInclusiveInt
//
//import scala.reflect.ClassTag
//
///**
//  * Random sampling from base data with repetition.
//  */
//object RandomChoice extends LoggingExtension{
//
////  def randomChoice[V: ClassTag](
////      data: Matrix1[V],
////      n: Int): Dense1[V] = {
////    Dense( randomChoice( data.toArray, n) )
////  }
////
//  def randomChoice[V: ClassTag]( data: Matrix1[V], n: Int): Matrix1[V] = {
//
//    new MatrixArray1(
//      Array
//        .tabulate[Int]( n )( (n: Int) => soyosoyo.random( RangeInclusiveInt( 0, data.length - 1) )  )
//        .map( data(_) )
//    )
//
//  }
//
//  def randomChoice[V: ClassTag]( data: Matrix1[V], weights: Matrix1[Double], n: Int): Matrix1[V] = {
//
//    loggerRequire( data.length >= 1, "Give non-zero number of samples to take from." )
//    loggerRequire( weights.length == data.length, "Number of weights specified differs from data length." )
//    loggerRequire( weights.forall( _ >= 0d ), "Weights must all be >= 0d." )
//
//    //update cumulative weighting vector
//    var weightsBuff: Array[Double] = null
//    var weightsNormBuff: Array[Double] = null
//    if(
//      weightsBuff == null ||
//        weightsBuff.length != weights.length ||
//        //ToDo 2: Change following to Matrix equality
//        !weights.zip( weightsBuff ).forall( (x: (Double, Double)) => x._1 == x._2 )
//    ){
//      weightsBuff = weights.toArray
//      var cum = 0d
//      for( c <- 0 until weights.length ){
//        cum += weights(c)
//      }
//      weightsNormBuff = weightsBuff.map( _ / cum )
//    }
//
//    (for( c <- 0 until n ) yield {
//      var rand = soyosoyo.random()
//      var sampNo = 0
//      while( rand >= 0 && sampNo < weights.length ){
//        rand -= weightsNormBuff( sampNo )
//        sampNo += 1
//      }
//      data( sampNo - 1 )
//    }).toArray
//
//  }
//
//}
