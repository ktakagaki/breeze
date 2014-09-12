package breeze.stats

import org.scalatest.FunSuite

/**
 * @author ktakagaki
 * @date 09/08/2014.
 */
class CircularStatsTest extends FunSuite {

  test("circular wrap") {
    assert( CircularStats.circularWrap(1d) == 1d )
    assert( CircularStats.circularWrap(- Math.PI) == Math.PI )
    assert( CircularStats.circularWrap(-2d) == -2d )
    assert( CircularStats.circularWrap(-4d) == -4d + 2d * Math.PI )
  }

  test("circular minus") {
    assert( CircularStats.circularMinus(2d, 1d) == 1d )
    assert( CircularStats.circularMinus(2d, 6d) == -4d + 2d * Math.PI )
  }

}
