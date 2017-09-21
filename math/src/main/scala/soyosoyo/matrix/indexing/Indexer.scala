package soyosoyo.matrix.indexing

/**
  * Interface to convert n-dimensional indices to internal flat array index
  * for dense matrix representation.
  *
  * Created by ktakagaki on 16/01/26.
  */
abstract class Indexer{
  //def getInternalIndex
}

// <editor-fold defaultstate="collapsed" desc=" 0 dimensions ">

case object IndexerSimple0 extends Indexer {
  val getInternalIndex = 0
}

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" 1 dimensions ">

abstract class Indexer1 extends Indexer {
  def getInternalIndex( i1: Int ): Int
}
case object IndexerSimple1 extends Indexer1 {
  override final def getInternalIndex( i1: Int ) = i1
}
case class IndexerGeneral1( stride1: Int, offset1: Int ) extends Indexer1 {
  override final def getInternalIndex( i1: Int ) = stride1 * i1 + offset1
}

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" 2 dimensions ">

abstract class Indexer2 extends Indexer{
  def getInternalIndex( i1: Int, i2: Int ): Int
  def transpose(): Indexer2
}
case class IndexerSimple2( stride1: Int, stride2: Int ) extends Indexer2 {
  override final def getInternalIndex( i1: Int, i2: Int  ): Int = {
    stride1 * i1  + stride2 * i2
  }
  override final def transpose() = IndexerSimple2(stride2, stride1)
}
case class IndexerGeneral2( stride1: Int, offset1: Int, stride2: Int, offset2: Int ) extends Indexer2 {
  override final def getInternalIndex( i1: Int, i2: Int  ): Int = {
    stride1 * i1 + offset1 + stride2 * i2 + offset2
  }
  override final def transpose() = IndexerGeneral2(stride2, offset2, stride1, offset1)
}

// </editor-fold>

object Indexer {

//  def transpose( i: Indexer1 ): Indexer2 = {
//    i match {
//      case IndexerSimple1 => IndexerSimple2(1)
//      case IndexerGeneral1( 1, 0 ) => IndexerSimple2(1)
//      case IndexerGeneral1( xStr0, xOffs0 ) => IndexerGeneral2(1, 0, xStr0, xOffs0)
//    }
//  }

}