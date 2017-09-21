package soyosoyo.matrix

import scala.reflect.ClassTag
import scala.{specialized => spec}
import soyosoyo.matrix.indexing.{Dimensions, Indexer}

abstract class Matrix[@spec(Int, Long, Float, Double) V, D <: Dimensions, I <: Indexer] {

  val classTag: ClassTag[V]
  val order: Int

}
