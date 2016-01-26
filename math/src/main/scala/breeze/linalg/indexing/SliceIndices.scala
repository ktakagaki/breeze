package breeze.linalg.indexing

import breeze.linalg._

/**Encapsulates indices and dimensional information for DenseMatrixB.
  * Inherits from Seq[Int] to allow apply(Int*) type syntax.
  * Using tuple for this purpose would have been an option, except that
  * variable access becomes more difficult, and there is no natural parent class
  * that can be referred to.
  *
  * Created by ktakagaki on 15/04/14.
  */
abstract class SliceIndices {

//  final lazy val iterator: Iterator[Int] = internalList.iterator
//  final lazy val length: Int = internalList.length
/*  abstract val dimensions: Int*/

}
case class SliceIndex1S(val slicer0: Slicer)
  extends Indices with DimensionChange1To0  {

}

case class SliceIndex2SS(val slicer0: Slicer, val slicer1: Slicer)
  extends Indices with DimensionChange2To0 {

}
case class SliceIndex2SI(val slicer0: Slicer, val index1: Int)
  extends Indices with DimensionChange2To1 {

}
case class SliceIndex2IS(val index0: Int, val slicer1: Slicer)
  extends Indices with DimensionChange2To1 {

}


case class SliceIndex3SSS(val slicer0: Slicer, val slicer1: Slicer, val slicer2: Slicer)
  extends Indices with DimensionChange3To3 {
}

case class SliceIndex3ISS(val index0: Int, val slicer1: Slicer, val slicer2: Slicer)
  extends Indices with DimensionChange3To2 {
}
case class SliceIndex3SIS(val slicer0: Slicer, val index1: Int, val slicer2: Slicer)
  extends Indices with DimensionChange3To2 {
}
case class SliceIndex3SSI(val slicer0: Slicer, val slicer1: Slicer, val index2: Int)
  extends Indices with DimensionChange3To2 {
}

case class SliceIndex3ISI(val index0: Int, val slicer1: Slicer, val index2: Int)
  extends Indices with DimensionChange3To1 {
}
case class SliceIndex3SII(val slicer0: Slicer, val index1: Int, val index2: Int)
  extends Indices with DimensionChange3To1 {
}
case class SliceIndex3IIS(val index0: Int, val index1: Int, val slicer2: Slicer)
  extends Indices with DimensionChange3To1 {
}

case class SliceIndex3III(val index0: Int, val index1: Int, val index2: Int)
  extends Indices with DimensionChange3To1 {
}