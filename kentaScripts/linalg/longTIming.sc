import spire.math.ULong

var ms = System.currentTimeMillis()
def updateMs():Unit = {ms = System.currentTimeMillis()}
def printMicro(): String = {
  val newms = System.currentTimeMillis()
  println(newms - ms)
  (newms - ms).toString
}
var long0 = 0L
var longMax = 9223372036854775807L
var bi0 = BigInt(long0)
var biLongMax = BigInt(longMax)
var biULongMax = BigInt("18446744073709551615")
var biULongMaxPlus = BigInt("18446744073709551615") + BigInt("1000000")
var sul0 = ULong( long0 )
var sulLongMax = ULong(longMax)
//var sulULongMax = new ULong(biULongMax)
//var sulULongMaxPlus = new ULong(biULongMaxPlus)

var longSubMat = Array.tabulate(1000005)(_.toLong)

updateMs()
var biSubMat = longSubMat.map( BigInt(_) )
printMicro()
updateMs()
var sulSubMat = longSubMat.map( ULong(_) )
printMicro()
var c = 1000000

println("=== long0 ===")
updateMs()
while(c > 0){
  long0 -= longSubMat(c)
  c -= 1
}
printMicro()
println("=== bi0 ===")
c= 1000000
updateMs()
while(c > 0){
  bi0 -= biSubMat(c)
  c -= 1
}
printMicro()

println("=== biULongMaxPlus ===")
c= 1000000
updateMs()
while(c > 0){
  biULongMaxPlus -= biSubMat(c)
  c -= 1
}
printMicro()

println("=== sul0 ===")
c= 1000000
updateMs()
while(c > 0){
  sul0 -= sulSubMat(c)
  c -= 1
}
printMicro()

println("=== sulLongMax ===")
c= 1000000
updateMs()
while(c > 0){
  sulLongMax -= sulSubMat(c)
  c -= 1
}
printMicro()
