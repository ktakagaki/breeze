package soyosoyo.matrix

import scala.reflect.ClassTag


object IsEqual {

  final def isEqual[V: ClassTag, W: ClassTag]( data1: Array[V], data2: Array[W] ): Boolean = {
      data1 != null &&
        data2 != null &&
        data1.length == data2.length &&
        data1.zip( data2 ).forall( (x: (V, W)) => x._1 == x._2 )
  }

}
