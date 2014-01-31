package breeze.graphics.primitives

import breeze.linalg.DenseMatrix

object PrLine{

  def apply(points: DenseMatrix[Double]) = new PrLine( points )

}

/**
 * @author ktakagaki
 * @date 1/31/14.
 */
class PrLine(var points: DenseMatrix[Double]) {



}
