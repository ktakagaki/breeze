package soyosoyo.matrix.indexing

import org.scalatest.FunSuite
import soyosoyo._
import soyosoyo.soyosoyoTestData
import soyosoyo.matrix.types.{IndicesRange, IndicesRangeInstantiated}

class IndexingTest  extends FunSuite with soyosoyoTestData {

  test("implicit IndexRange"){

    assert( 1 %% 5 == IndicesRange(1, 5, Int.MinValue) )
    assert( 5 %% 2 %% -2 == IndicesRange(5,2,-2) )
    assert( (1 %% -2 %% 1).instantiate(10) == IndicesRangeInstantiated(1,7,1,10) )

  }

}
