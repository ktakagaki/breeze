//package breeze.linalg
//
//
///**
//  * Encapsulates multidimensional array dimensions.
//  * Different child classes are provided for each array rank,
//  * to facilitate static type inference during mapping
//  * and other dimensionality shifting functions.
//  *
//  * Created by ktakagaki on 15/10/14.
//  */
//abstract class Dimensions{
//
//}
//
///**Marks scalar value*/
//case object Dimensions0 extends Dimensions
//
//case class Dimensions1(val dim1: Int)
//  extends Dimensions{
//
//
//}
//
//case class DimensionsSquare2(val dim1: Int, val dim2: Int)
//  extends DimensionsSquare{
//  override def toArray() = Array(dim1, dim2)
//}
//
////case class DimensionsSquare3(val dim1: Int, val dim2: Int, val dim3: Int)
////  extends DimensionsSquare
