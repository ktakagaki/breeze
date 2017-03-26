package breeze.linalg.indexing

/**
  * Specifies a range in a Tensor slice.
  *
  * Created by ktakagaki on 16/03/04.
  */
case class SliceRange( start: Int, end: Int, step: Int ) {

}

/**
  * Specifies a single index in a Tensor slice.
  *
  * Created by ktakagaki on 16/03/04.
  */
case class Slice( value: Int )
