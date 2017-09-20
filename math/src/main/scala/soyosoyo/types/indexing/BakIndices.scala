//package breeze.linalg
//
///**
//  * Encapsulates indexing information for DenseN,
//  * and ultimately gives an iterator to map through a flat internal array by index.
//  *
//  * Using tuple for this purpose would have been an option, except that
//  * uniform access to variable dimension DenseNs would become more difficult
//  * and there is no natural parent class that can be referred to.
//  *
//  * Created by ktakagaki on 15/04/14.
//  */
//abstract class Indices {
//
//  // val depth: Int
//  // final lazy val iterator: Iterator[Int] = internalList.iterator
//  // def getInstantiatedIndices( matrix/dimensions ): InstantiatedIndex
//  // def getValidIndices( matrix/dimensions ): InstantiatedIndex
//
//}
//case class Indices1(val index0: Int) extends Indices with DimensionChange1To0 {
//
//  //  override val dimensions = 1
//
//}
//case class Indices2(val index0: Int, val index1: Int) extends Indices with DimensionChange2To0 {
//
//  //  override val dimensions = 2
//
//}
//case class Indices3(val index0: Int, val index1: Int, val index2: Int) extends Indices with DimensionChange3To0 {
//
//  //  override val dimensions = 3
//
//}
//
//object Indices {
//
//  implicit def seqToIndices(indices: Seq[Int]) = {
//    indices.length match {
//      case 1 => Indices1(indices(0))
//      case 2 => Indices2(indices(0), indices(1))
//      case 3 => Indices3(indices(0), indices(1), indices(2))
//      case _ => throw new IllegalArgumentException("currently supporting up to 3 indices")
//    }
//  }
//
//  implicit def tuple1ToIndices(tuple: Tuple1[Int]) = Indices1(tuple._1)
//  implicit def tuple2ToIndices(tuple: Tuple2[Int, Int]) = Indices2(tuple._1, tuple._2)
//  implicit def tuple3ToIndices(tuple: Tuple3[Int, Int, Int]) = Indices3(tuple._1, tuple._2, tuple._3)
//
//}