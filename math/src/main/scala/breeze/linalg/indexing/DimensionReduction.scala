package breeze.linalg.indexing

/**
 * Created by ktakagaki on 15/10/13.
 */
trait DimensionReduction {
  val reduction: Int
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
