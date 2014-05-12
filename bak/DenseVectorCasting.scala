package breeze.kentaTests

import org.scalatest.FunSuite
import breeze.linalg.DenseVector

/**
 * @author ktakagaki
 * @date 2/14/14.
 */
class DenseVectorCasting extends FunSuite {

  val tempDataDouble = DenseVector.rand[Double](1000000)

//  test("via map") {
//    var dataTemp = DenseVector[Long](tempDataDouble.length)
//    for( count <-0 until 10000){
//      dataTemp :+= tempDataDouble.map(_.toLong)
//    }
//  }

//  test("via array") {
//    var dataTemp = DenseVector[Long](tempDataDouble.length)
//    for( count <-0 until 10000){
//
//      dataTemp :+= DenseVector( castArrayToDouble( tempDataDouble.toArray ) )
//    }
//  }

  test("via array without cast") {
    var dataTemp = DenseVector[Long](tempDataDouble.length)
    for( count <-0 until 10000*1000){
      dataTemp :+= castDenseVectorToDouble( tempDataDouble )
    }
  }

  def castArrayToDouble(array: Array[Double]): Array[Long] = {
    var tempret = Array[Long](array.length)
    for( c <- 0 until tempret.length){
      tempret(c) = array(c).toLong
    }
    tempret
  }
  def castDenseVectorToDouble(array: DenseVector[Double]): DenseVector[Long] = {
    var tempret = Array[Long](array.length)
    for( c <- 0 until tempret.length){
      tempret(c) = array(c).toLong
    }
    DenseVector( tempret )
  }

}
