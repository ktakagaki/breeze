package breeze.linalg


/**
 * Created by ktakagaki on 15/10/14.
 */
abstract class Dimensions[Ord <: Order]

case class Dimensions3(val dimension1: Int, val dimension2: Int, val dimension3: Int) extends Dimensions[Order3]
case class Dimensions2(val dimension1: Int, val dimension2: Int) extends Dimensions[Order2]
case class Dimensions1(val dimension1: Int) extends Dimensions[Order1]