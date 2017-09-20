//package breeze.soyosoyo.indexing
//
//import breeze.util.SerializableLogging
//
///**
//  * Encapsulates index ranges in one dimension, for slicing.
//  *
//  * Standard classes such as scala Range could have been used, but are avoided due
//  * to needing to specify negative indices (i.e. from end).
//  *
//  * Created by ktakagaki on 16/01/26.
//  */
//case class IndexRange(val start: Int, val last: Int, val step: Int = 1)
//  extends SerializableLogging {
//
//  if( step == 0)
//    throw logger.error( "step cannot be zero", IllegalArgumentException)
//
//  /**
//    * Returns an
//    * [[breeze.soyosoyo.indexing.IndexRangeInstantiated IndexRangeInstantiated]],
//    * where negative (i.e. from end) indices are resolved to real indices
//    */
//  def instantiate(dimensionLength: Int): IndexRangeInstantiated = {
//    val tempret = new IndexRangeInstantiated(
//      if (start < 0) dimensionLength + start - 1 else start,
//      if (last < 0) dimensionLength + last - 1 else last,
//      step
//    )
//
//    if( tempret.start < 0 || dimensionLength <= tempret.start)
//      throw logger.error( "instantiated start dimension OOB", IllegalArgumentException)
//    if( tempret.last < 0 || dimensionLength <= tempret.last)
//      throw logger.error( "instantiated start dimension OOB", IllegalArgumentException)
//
//    tempret
//  }
//
//  /**Javabean-style alias for [[instantiate()]] */
//  def getIndexRangeInstantiated(dimensionLength: Int): IndexRangeInstantiated =
//    instantiate( dimensionLength )
//
//}
//
//case class IndexRangeInstantiated(val start: Int, val last: Int, val step: Int = 1)
//  extends IndexRange {
//
//  override final def getIndexRangeInstantiated(dimensionLength: Int) = {
//
//    if( this.start < 0 || dimensionLength <= this.start)
//      throw logger.error( "instantiated start dimension OOB", IllegalArgumentException)
//    if( this.last < 0 || dimensionLength <= this.last)
//      throw logger.error( "instantiated last dimension OOB", IllegalArgumentException
////  Covered in parent class
////  if( step == 0)
////    throw logger.error( "step cannot be zero", IllegalArgumentException)
//    if( step > 0 && start > last )
//      throw logger.error("if step is positive, start must be <= last")
//    if( step < 0 && start < last )
//      throw logger.error("if step is negative , start must be >= last")
//    this //immutable requires no defensive clone
//
//  }
//
//  lazy val def length: Int = (last - start)/step + 1
//
//}
//
//case class IndexSingle(val index: Int)
//  extends SerializableLogging {
//
//  override final def getIndexInstantiated(dimensionLength: Int) = {
//    val tempret = if( index < 0 ) dimensionLength + index - 1 else index
//    if (tempret < 0 || dimensionLength <= tempret)
//      throw logger.error("instantiated index OOB", IllegalArgumentException)
//  }
//
//}
//
//case class IndexInstantiatedSingle(val index: Int)
//  extends IndexSingle {
//
//  if (index < 0)
//    throw logger.error("instantiated index negative", IllegalArgumentException)
//
//  override final def getIndexRangeInstantiated(dimensionLength: Int) = {
//    if ( dimensionLength <= index )
//      throw logger.error("instantiated index OOB", IllegalArgumentException)
//  }
//
//}