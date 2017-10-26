package soyosoyo.types

import soyosoyo.subroutines.LoggingExtension

object Indices {

  implicit def seqToIndexList( seq: Seq[Int] ): Indices = {
    if( seq.length == 1 ){ IndicesSingle( seq(0) ) }
    else if (seq.length == 0 ) IndicesNull
    else IndicesList( seq.toVector )

  }

  /**Instantiates a virtual index (may be negative, counting from end, to real array index (>0).*/
  def instantiate( index: Int, dimensionLength: Int ): Int =
    if (index < 0) dimensionLength + index else index

  //    if( seq.length == 1 ) IndicesSingleInstantiated( seq( 0) )
  //    else {
  //      val stepCandidate = seq(1) - seq(0)
  //      loggerRequire( stepCandidate != 0, "there are duplicate entries in the Seq, i.e. step of zero")
  //      if( seq.length > 2 ){
  //        var c = 1
  //        while( c < seq.length ){
  //          loggerRequire( seq(c) - seq(c-1) == stepCandidate, "step must be constant within Seq" )
  //          c += 1
  //        }
  //      }
  //      IndicesInstantiated( start = seq( 0), last = seq( 1), step = stepCandidate )
  //    }
  //  }
}

// <editor-fold defaultstate="collapsed" desc=" trait Indices / trait IndicesInstantiated ">

/**Parent for index specifiers (range/single/null),
  * allows instantiation (i.e. resolution of negative indices)
  */
trait Indices extends LoggingExtension {

  /**
    * Resolve any negative dimensions (which count from end), and make sure no indices are OOB.
    *
    * @param dimensionLength
    * @return
    */
  def instantiate(dimensionLength: Int): IndicesInstantiated

}

/**Instantiated index specifiers (range/single/null), i.e. no negative or OOB indices.
  *An IndexRange instantiated for one data object should, as a rule, not be used
  */
trait IndicesInstantiated extends Indices {

  /**Number of instantiated sequence elements.*/
  def length: Int

  def instantiate(dimensionLength: Int) = {
    if( dimensionLength == this.dimensionLength ) this
    else throw loggerError(s"This range has already been instantiated with a different dimensionLength ${this.dimensionLength}")
  }

  /**Array of instantiated sequence elements.*/
  def toArray: Array[Int]

  /**The dimension length at which this index range has been instantiated*/
  val dimensionLength: Int

}

// </editor-fold>

case object IndicesNull extends IndicesInstantiated{

  override final val dimensionLength = -1
  override final val length = 0
  override final val toArray = new Array[Int](0)

  override def instantiate(dimensionLength: Int) = this

}

// <editor-fold defaultstate="collapsed" desc=" Indices instances ">

case class IndicesAll(val step: Int = 1) extends Indices {

  override def instantiate( dimensionLength: Int ): IndicesInstantiated =
    dimensionLength match {
      case 0 => IndicesNull
      case 1 => IndicesSingleInstantiated(0, dimensionLength)
      case _ => {
        if( step > 0 ) IndicesRangeInstantiated( 0, dimensionLength - 1, step, dimensionLength )
        else IndicesRangeInstantiated( dimensionLength - 1, 0, step, dimensionLength )
      }
  }

}

/**
  * Encapsulates index ranges in one dimension, for slicing.
  *
  * Standard classes such as scala Range could have been used, but are avoided due
  * to needing to specify negative indices (i.e. from end).
  *
  * Created by ktakagaki on 16/01/26.
  */
case class IndicesRange(val start: Int, val last: Int, val step: Int = Integer.MIN_VALUE ) extends Indices {

  loggerRequire( 0 != step || start != last, "Step cannot be zero if start and end are different.")

  def %%( step: Int ) = {
    if( this.step == Integer.MIN_VALUE) IndicesRange( start, last, step )
    else throw loggerError( "step already specified, cannot specify further" )
  }

  /**
    * Returns an
    * [[IndicesInstantiated IndexRangeInstantiated]],
    * where negative (i.e. from end) indices are resolved to real indices
    */
  override def instantiate(dimensionLength: Int): IndicesInstantiated = {

    val tempNewStart = Indices.instantiate( start, dimensionLength )
    val tempNewLast = Indices.instantiate( last, dimensionLength )

    if(tempNewStart == tempNewLast) IndicesSingleInstantiated( tempNewStart, dimensionLength )
    else IndicesRangeInstantiated( tempNewStart,
                                   tempNewLast,
                                   if( step == Integer.MIN_VALUE ) 1 else step,
                                   dimensionLength)

  }

}

case class IndicesSingle(val index: Int) extends Indices {

  override final def instantiate(dimensionLength: Int) = {
    val tempret = if( index < 0 ) dimensionLength + index - 1 else index
    IndicesSingleInstantiated( tempret, dimensionLength )
  }

}

case class IndicesList( val indices: Vector[Int] ) extends Indices {

  override final def instantiate( dimensionLength: Int ) = {
    IndicesListInstantiated( indices.map( (i: Int) => Indices.instantiate(i, dimensionLength) ), dimensionLength )
  }

}

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc=" IndicesInstantiated instances ">

case class IndicesRangeInstantiated(val start: Int, val last: Int, val step: Int, override val dimensionLength: Int)
  extends IndicesInstantiated {

  loggerRequire(
    0 <= start && start < dimensionLength,
    "instantiated start dimension OOB"
  )
  loggerRequire(
    0 <= last && last < dimensionLength,
    "instantiated last dimension OOB"
  )
  loggerRequire(
    (0 < step && start <= last)
      || (step < 0 && last <= start)
      || (step == 0 && start == last),
    s"Either start/last size relationship or step sign is wrong: $start %% $last %% $step."
  )

  override lazy val length: Int =
    if( step == 0 ) 1
    else (last - start)/step + 1

  override lazy val toArray = Array.tabulate[Int]( length )( _ + start )

}


case class IndicesSingleInstantiated(val index: Int, override val dimensionLength: Int)
  extends IndicesInstantiated{

  loggerRequire(0 < index && index <= dimensionLength, s"instantiated index $index OOB of dimensionLength $dimensionLength")

  override final val length = 1
  override lazy val toArray = Array( index )

}

case class IndicesListInstantiated(val indices: Vector[Int], override val dimensionLength: Int)
  extends IndicesInstantiated{

  loggerRequire(
    indices.forall( (i: Int) => {0 <= i && i <= dimensionLength}),
    s"instantiated indices $indices OOB of dimensionLength $dimensionLength"
  )

  override final val length = 1
  override lazy val toArray = indices.toArray

}

// </editor-fold>