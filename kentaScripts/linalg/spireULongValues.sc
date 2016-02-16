import breeze.io.{ByteConverterBigEndian, ByteConverterLittleEndian}
import spire.math.ULong
var long0 = 0L
var longMax = 9223372036854775807L
var biULongMax = BigInt("18446744073709551615")
var sul0 = ULong(long0)
var sulLongMax = ULong(longMax)
var sulULongMax = ULong("18446744073709551615")
var sulULongMaxOF = ULong("18446744073709551616")

ByteConverterLittleEndian.uInt64ToBytes(ULong(259)).toList
ByteConverterLittleEndian.uInt64ToBytes(sulLongMax).toList
ByteConverterLittleEndian.uInt64ToBytes(sulULongMax).toList
ByteConverterLittleEndian.uInt64ToBytes(sulULongMaxOF).toList

ByteConverterBigEndian.uInt64ToBytes(ULong(259)).toList
ByteConverterBigEndian.uInt64ToBytes(sulLongMax).toList
ByteConverterBigEndian.uInt64ToBytes(sulULongMax).toList
ByteConverterBigEndian.uInt64ToBytes(sulULongMaxOF).toList

sulULongMax + ULong(1)
sul0 - ULong(1)
ULong(2) * ULong(4)
(ULong(2) * ULong(4)).getClass().toString
(ULong(2) * ULong(4)).toInt
(ULong(2) * ULong(4)).toBigInt
sulLongMax.longValue()
sulLongMax.longValue().toOctalString
sulLongMax.toByteArray.map( Integer.toOctalString(_) )

sul0.longValue()
sul0.longValue().toOctalString

sulULongMax.longValue()
sulULongMax.longValue().toOctalString

