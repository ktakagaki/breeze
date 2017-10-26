package soyosoyo.subroutines

import com.typesafe.scalalogging.LazyLogging

/**Trait to implement logging within [[nounou.elements.NNElement]] children.
  * @author ktakagaki
  * //@date 07/15/2014.
  */
trait LoggingExtension extends LazyLogging {

  /** '''[LoggingExt]''' Logs a message using slf4j, and returns a new [scala.IllegalArgumentException].
    * Use as follows:
    * ````
    * throw loggerError("This is an error with params {}", param1.toString)
    * ````
    *
    */
  def loggerError(message: String, params: AnyRef*): IllegalArgumentException = {
    logger.error( message, params )
    new IllegalArgumentException(message)
  }

  def loggerWarn(message: String, params: AnyRef*): Unit = {
    logger.warn( message, params )
  }


  /** '''[LoggingExt]''' If input 'boolean' is false, logs a message using slf4j and throws an error.
    */
  @throws[IllegalArgumentException]
  def loggerRequire(boolean: Boolean, message: String, params: AnyRef*): Unit = {
    if(!boolean){
      logger.error(message, params)
      throw new IllegalArgumentException( "loggerRequire:" + message)
    }
  }

}
