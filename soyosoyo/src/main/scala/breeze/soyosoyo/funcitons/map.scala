package breeze.linalg.functions;

/**
  * Created by ktakagaki on 16/03/05.
  */
object map extends UFunc {

  @expand
  @expand.valify
  implicit def mapArray2Dto1D[@expand.args(Int, Long, Float, Double, Complex) V] =
    new Impl[Array2D[V], MapDimension2D1D, Array1D[V]] {
      def apply(v: Array2D[V], ): Double = v1.abs.toDouble
    }

}
