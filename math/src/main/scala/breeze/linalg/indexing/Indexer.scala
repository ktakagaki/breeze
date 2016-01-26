package breeze.linalg.indexing

import breeze.linalg.{Indices2, Indices1, Indices}

/**
  * Interface to convert n-dimensional indices to internal flat array index
  * for dense matrix representation.
  *
  * Created by ktakagaki on 16/01/26.
  */
abstract class Indexer[ I <: Indices ] {
  def getInternalIndex( i: I ): Int
}

abstract class Indexer1 extends Indexer[ Indices1 ]
case object IndexerSimple1 extends Indexer1 {
  override final def getInternalIndex( i: Indices1 ) = i.index0
}
case class IndexerGeneral1( stride0: Int, offset0: Int ) extends Indexer1 {
  override final def getInternalIndex( i: Indices1 ) = i.index0
}

abstract class Indexer2 extends Indexer[ Indices2 ]
case class IndexerSimple2( stride1: Int ) extends Indexer2 {
  override final def getInternalIndex( i: Indices2 ) = i.index0 + i.index1 * stride1
}
case class IndexerGeneral2( stride0: Int, offset0: Int, stride1: Int, offset1: Int ) extends Indexer2 {
  override def getInternalIndex( i: Indices2 ) = (i.index0 + offset0) + (i.index1 + offset1)*stride1
}

object Indexer {
  def transpose( i: Indexer1 ): Indexer2 = {
    i match {
      case IndexerSimple1 => IndexerSimple2(1)
      case IndexerGeneral1( 1, 0 ) => IndexerSimple2(1)
      case IndexerGeneral1( xStr0, xOffs0 ) => IndexerGeneral2(1, 0, xStr0, xOffs0)
    }
  }
}