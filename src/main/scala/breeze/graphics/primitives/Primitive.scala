package breeze.graphics.primitives

import scalafx.scene.paint.Color
import breeze.linalg.{convert, DenseVector, DenseMatrix}

object Primitive {

  implicit def implicitItoD(i: Int) = i.toDouble
  implicit def implicitDvItoD(i: DenseVector[Int]) = convert( i, Double )
  implicit def implicitDmItoD(i: DenseMatrix[Int]) = convert( i, Double )

}

/**
 * @author ktakagaki
 * @date 1/31/14.
 */
class Primitive {

  //ToDo 2: What characteristics to represent here?
  /**Encapsulates stroke*/
  var color: Color = Color.BLACK
  /**Encapsulates strokeWidth*/
  var thickness: Double = 2

}
