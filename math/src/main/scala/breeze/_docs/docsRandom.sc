//for wiki random docs

import breeze.linalg._


val randD1 = randomDouble()  //Create a random double in [0, 1]

val randD2 = randomDouble(2)  //Create a 2-element DenseVector[Double]

val randD3 = randomDouble((2, 3))  //Create a 2-row, 3-column DenseMatrix[Double]

val randD4 = randomDouble(2, (1.5, 2d))  //Sample between [1.5, 2]

val randD5 = randomDouble((2, 3), (1.5, 2d))  //Sample between [1.5, 2]


val randI1 = randomInt()  //Sample between [0, 1]

val randI2 = randomInt(4)  //Create a 4-element DenseVector[Int]

val randI4 = randomInt(3, (10, 11))  //Sample between [10, 11]


val randn2 = randn((1,3))  //Gaussian sampling



//Shuffle

val dv = randomDouble(3)

shuffle(dv)

