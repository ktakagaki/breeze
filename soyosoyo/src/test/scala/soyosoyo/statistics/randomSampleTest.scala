package soyosoyo.statistics

import org.scalatest.FunSuite
import soyosoyo.soyosoyoTestData
import soyosoyo._

class randomSampleTest extends FunSuite with soyosoyoTestData {

  test("randomSample"){

    println(testArrayI1.length)

    println( randomSample( testArrayI1, 3 ).toList )
    println( randomSample( testArrayI1, 5 ).toList )
    println( randomSample( testArrayI1, 9 ).toList )
    println( randomSample( testArrayI1, 10 ).toList )
   }

  test("randomChoice"){

    println( randomChoice( testArrayI1, 3 ).toList )
    println( randomChoice( testArrayI1, 5 ).toList )
    println( randomChoice( testArrayI1, 9 ).toList )
    println( randomChoice( testArrayI1, 10 ).toList )
    println( randomChoice( testArrayI1, 20 ).toList )
    println( randomChoice( testArrayI1,
                           Array(1d, 5d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d),
                           20 ).toList )

  }

}

