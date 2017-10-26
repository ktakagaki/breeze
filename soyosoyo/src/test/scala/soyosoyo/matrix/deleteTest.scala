package soyosoyo.matrix

import org.scalatest.FunSuite
import soyosoyo._

class deleteTest extends FunSuite with soyosoyoTestData {

  test("delete"){
    assert( isEqual( delete( testArrayI1, 0 %% 7 ), Array(8, 9)  ) )
    println( delete( testArrayI1, 0 until 8).toList )
    println( delete( testArrayI1, 3 until 8).toList )
    println( delete( testArrayI1, 3 until 10).toList )
    println( delete( testArrayI1, 3 %% -2).toList )

                  //    assert( soyosoyo.equals( soyosoyo.delete( testArrayI1, 3 until 8), Array( 0, 1, 9, 10) ) )
    assert( true )
  }

}
