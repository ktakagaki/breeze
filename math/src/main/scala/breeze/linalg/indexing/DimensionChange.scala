package breeze.linalg

/**
  * This trait marks indices as reducing the dimensionality of a matrix (upon slicing)
  * by a certain amount
  * Created by ktakagaki on 15/10/13.
  */
trait DimensionChange extends Indices {
  val reduction: Int
}

trait DimensionChange3To3 extends DimensionChange {
  override val reduction = 0
}
trait DimensionChange3To2 extends DimensionChange {
  override val reduction = 1
}
trait DimensionChange3To1 extends DimensionChange {
  override val reduction = 2
}
trait DimensionChange3To0 extends DimensionChange {
  override val reduction = 3
}

trait DimensionChange2To2 extends DimensionChange {
  override val reduction = 0
}
trait DimensionChange2To1 extends DimensionChange {
  override val reduction = 1
}
trait DimensionChange2To0 extends DimensionChange {
  override val reduction = 2
}

trait DimensionChange1To1 extends DimensionChange {
  override val reduction = 0
}
trait DimensionChange1To0 extends DimensionChange {
  override val reduction = 0
}