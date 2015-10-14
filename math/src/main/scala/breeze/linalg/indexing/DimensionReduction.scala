package breeze.linalg

/**
 * Created by ktakagaki on 15/10/13.
 */
trait DimensionReduction extends Indices {
  val reduction: Int
}

trait DimensionReduction3 extends DimensionReduction {
  override val reduction = 3
}
trait DimensionReduction2 extends DimensionReduction {
  override val reduction = 2
}
trait DimensionReduction1 extends DimensionReduction {
  override val reduction = 1
}
trait DimensionReduction0 extends DimensionReduction {
  override val reduction = 0
}
