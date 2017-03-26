package breeze.linalg


/**
  * Encapsulates tensor dimensions.
  * Different child classes are provided for each tensor rank, to facilitate mapping
  * and other dimensionality shifting functions.
  *
  * Created by ktakagaki on 15/10/14.
  */
abstract class Dimensions

case class Dimensions3(val dim1: Int, val dim2: Int, val dim3: Int) extends Dimensions

case class Dimensions2(val dim1: Int, val dim2: Int) extends Dimensions

case class Dimensions1(val dim1: Int) extends Dimensions

/**Marks scalar value*/
case object Dimensions0 extends Dimensions