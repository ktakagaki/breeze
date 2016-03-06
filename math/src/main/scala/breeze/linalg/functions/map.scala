package breeze.linalg.functions

import breeze.generic.UFunc
import breeze.linalg.immutable.Tensor3
import breeze.linalg.norm._
import breeze.macros.expand

/**
  * Created by ktakagaki on 16/03/05.
  */
object map extends UFunc {

  @expand
  @expand.valify
  implicit def mapTensor3to2[@expand.args(Int, Long, Float, Double) T]: Impl[Tensor3[T], Double] = new Impl[T, Double] {
    def apply(v: Tensor3[T], ): Double = v1.abs.toDouble
  }

}
