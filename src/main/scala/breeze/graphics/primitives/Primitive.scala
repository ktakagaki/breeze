package breeze.graphics.primitives

import scalafx.scene.paint.Color
import breeze.linalg.{DenseVector, DenseMatrix}

object Primitive {
  //ToDo 3: Make generic.
  implicit def implicitItoD(i: Int) = i.toDouble
//  implicit def implicitItoD[Int](dv: DenseVector[Int]): DenseVector[Double] = {
//    new DenseVector[Double](dv.toArray)
//  }
//  implicit def implicitItoD[Int](dm: DenseMatrix[Int]): DenseMatrix[Double] = {
//    new DenseMatrix[Double](dm.toArray)
//  }
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
