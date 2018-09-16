package soyosoyo.matrix.types

import soyosoyo.subroutines.LoggingExtension

abstract class Span extends LoggingExtension

case class Span2I(x1: Int, x2: Int) extends Span{
//  loggerRequire( x1 <= x2, s"must be $x1 <= $x2" )
}
case class Span2L(x1: Long, x2: Long) extends Span{
//  loggerRequire( x1 <= x2, s"must be $x1 <= $x2" )
}
case class Span2F(x1: Float, x2: Float) extends Span{
//  loggerRequire( x1 <= x2, s"must be $x1 <= $x2" )
}
case class Span2D(x1: Double, x2: Double) extends Span{
//  loggerRequire( x1 <= x2, s"must be $x1 <= $x2" )
}

case class Span3I(x1: Int, x2: Int, x3: Int) extends Span
case class Span3L(x1: Long, x2: Long, x3: Long) extends Span
case class Span3F(x1: Float, x2: Float, x3: Float) extends Span
case class Span3D(x1: Double, x2: Double, x3: Double) extends Span