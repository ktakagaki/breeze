package soyosoyo.types

import soyosoyo.subroutines.LoggingExtension

abstract class RangeInclusive extends LoggingExtension
case class RangeInclusiveI(x1: Int, x2: Int) extends RangeInclusive{
  loggerRequire( x1 <= x2, s"must be $x1 <= $x2" )
}
case class RangeInclusiveL(x1: Long, x2: Long) extends RangeInclusive{
  loggerRequire( x1 <= x2, s"must be $x1 <= $x2" )
}
case class RangeInclusiveF(x1: Float, x2: Float) extends RangeInclusive{
  loggerRequire( x1 <= x2, s"must be $x1 <= $x2" )
}
case class RangeInclusiveD(x1: Double, x2: Double) extends RangeInclusive{
  loggerRequire( x1 <= x2, s"must be $x1 <= $x2" )
}