package breeze.linalg.indexing

/**
 * Created by ktakagaki on 15/10/13.
 */
abstract class Dimensions(val dimensions: Int)

case object Dimensions3 extends Dimensions(3)
case object Dimensions2 extends Dimensions(2)
case object Dimensions1 extends Dimensions(1)