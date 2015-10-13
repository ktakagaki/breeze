package breeze.linalg.indexing

/**
 * Created by ktakagaki on 15/10/13.
 */
abstract class Dimensions(val dimensions)

class Dimensions3 extends Dimensions
class Dimensions2 extends Dimensions3
class Dimensions1 extends Dimensions2