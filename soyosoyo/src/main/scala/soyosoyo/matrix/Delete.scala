package soyosoyo.matrix

import soyosoyo.matrix.immutable.{Dense1, Matrix1}
import soyosoyo.types.{Indices, IndicesNull, IndicesRangeInstantiated, IndicesSingleInstantiated}

import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag


/**
  *
  */
object Delete {

  final def delete[V: ClassTag]( data: Matrix1[V], indexRange: Indices ): Matrix1[V] = {

    val irInst = indexRange.instantiate( data.length )
    new Dense1( delete[V]( data.toArray, irInst ) )

  }

  final def delete[V: ClassTag]( data: Array[V], indices: Indices ): Array[V] = {

    indices.instantiate( data.length ) match {
      case IndicesNull => data.clone()
      //case IndicesAll( _ ) => new Array[V](0)

      case IndicesSingleInstantiated( i, dl ) => deleteDenseRange( data, i, i, dl)

      case IndicesRangeInstantiated( start, last, 1, dl ) => deleteDenseRange( data, start, last, dl )
      case IndicesRangeInstantiated( start, last, -1, dl ) => deleteDenseRange( data, last, start, dl )

      case x =>
        deleteDiscreteSet( data, x.toArray.distinct.toSet, x.dimensionLength )
    }
  }

  private def deleteDenseRange[V: ClassTag]( data: Array[V], lowest: Int, highest: Int, dataLength: Int) =
    if( lowest == 0 ) data.drop( highest + 1 )
    else if( highest == dataLength - 1 ) data.take( lowest )
    else data.take( lowest ) ++ data.drop( highest + 1 )
  private def deleteDiscreteSet[V: ClassTag]( data: Array[V], set: Set[Int], dataLength: Int) = {
    val tempReturn = new ArrayBuffer[V]()
    for( c <- 0 until data.length ) if( !set.contains( c ) ) tempReturn.append( data( c ) )
    tempReturn.toArray
  }

}
