//package breeze.linalg.indexing
//
///**
//  * Encapsulates slice ranges.
//  *
//  * Constructs such as scala Range could have been used, but are avoided due
//  * to needing to specify negative indices (i.e. from end)
//  *
//  * Created by ktakagaki on 16/01/26.
//  */
//case class Slicer(val start: Int, val last: Int, val step: Int = 1){
//
//  /**
//    * Returns an [[breeze.linalg.indexing.InstantiatedSlicer InstantiatedSlicer]],
//    * where negative (i.e. from end) indices are resolved to real indices
//    *
//    */
//  def getInstantiatedSlicer(dimension: Int): InstantiatedSlicer = {
//
//    val instantiatedStart = if(start < 0) dimension + start else start
//    val instantiatedLast  = if(last  < 0) dimension + last else last
//    assert( instantiatedStart > 0, s"start ($start) should be within [-dimension($dimension), dimension-1]!")
//    assert( instantiatedLast > 0, s"last ($last) should be within [-dimension($dimension), dimension-1]!" )
//    assert( if(start<last) step>1 else step<1, s"start ($start) to last ($last) requires a step of the other sign than step($step)")
//
//    new InstantiatedSlicer( instantiatedStart, instantiatedLast, step, dimension )
//
//  }
//
//}
//
///**
//  * [[breeze.linalg.indexing.Slicer Slicer]], but marked to have no negative
//  * values (i.e. counts from end for start/last)
//  *
//  */
//case class InstantiatedSlicer(override val start: Int, override val last: Int, override val step: Int, val dimension: Int)
//  extends Slicer(start, last, step) {
//
//  override def getInstantiatedSlicer(dimension: Int) = this
//
//}
