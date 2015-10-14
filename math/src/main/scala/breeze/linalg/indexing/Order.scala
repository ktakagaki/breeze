package breeze.linalg

/**
 * Created by ktakagaki on 15/10/13.
 */
abstract class Order(val order: Int)

class Order3 extends Order(3)
class Order2 extends Order(2)
class Order1 extends Order(1)
class Order0 extends Order(0)