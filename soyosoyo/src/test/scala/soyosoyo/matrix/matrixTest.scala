package soyosoyo.matrix

import org.scalatest.FunSuite
import soyosoyo._

class matrixTest extends FunSuite with soyosoyoTestData {

  test("delete"){
    assert( isEqual( delete( testArrayI1, 0 %% 7 ), Array(8, 9)  ) )
    assert( isEqual( delete( testArrayI1, 0 until 8), Array(8, 9) ) )
    assert( isEqual( delete( testArrayI1, 0 to 7), Array(8, 9) ) )
    assert( isEqual( delete( testArrayI1, 3 %% -2), Array(0, 1, 2, 9) ) )
    //assert( isEqual( delete( testArrayI1, 3 to -2), Array(0, 1, 2, 9) ) )

  }

}
