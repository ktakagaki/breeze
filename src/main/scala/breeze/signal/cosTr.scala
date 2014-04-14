package breeze.signal

import breeze.linalg._
import breeze.linalg.{sum, DenseMatrix, DenseVector}
import breeze.generic.UFunc
import breeze.signal.support.JTransformsSupport._
import breeze.numerics.{sin, cos}
import breeze.util.JavaArrayOps


//Derived from fourierTr.scala. Be sure to synchronize any changes.

/**
 * Returns the discrete fourier transform of a DenseVector or DenseMatrix. Currently,
 * DenseVector/DenseMatrix types of Double and Complex are supported. Scaling
 * follows the common signal processing convention, i.e. <b>no scaling on forward DFT</b>,
 * and 1/n scaling for the inverse DFT. Of note, fft(x: DenseMatrix[Double]) will
 * perform the 2D fft in both row and column dimensions, as opposed to the MatLab
 * toolbox syntax, which performs column-wise 1D fft.</p>
 * Implementation is via the implicit trait fft.Impl[ InputType,  OutputType ],
 * which is found in breeze.signal.support.fft.Impl.scala.
 *
 * @return
 * @author ktakagaki, dlwh
 */
object cosTr extends UFunc {

  //ToDo 2: expandify this code
  //ToDo 2: after expandify => provide "n" argument to pad before performing transform
  //ToDo 3: after "n" => provide sinTr

  // <editor-fold defaultstate="collapsed" desc=" DenseVector DCTs ">

  implicit val dvDouble1DCT : cosTr.Impl[DenseVector[Double], DenseVector[Double]] = {
    new cosTr.Impl[DenseVector[Double], DenseVector[Double]] {
      def apply(v: DenseVector[Double]) = {
        val tempArr = v.toArray
        val dct_instance = getDoubleDCT1DInst(v.length)
        dct_instance.forward( tempArr, false ) //does operation in place
        DenseVector(tempArr)
      }
    }
  }

  implicit val dvFloat1DCT: cosTr.Impl[DenseVector[Float], DenseVector[Float]] = {
    new Impl[DenseVector[Float], DenseVector[Float]] {
      def apply(v: DenseVector[Float]) = {
        val tempArr = v.toArray
        val dct_instance = getFloatDCT1DInst(v.length)
        dct_instance.forward(tempArr, false ) //does operation in place
        DenseVector(tempArr)
      }
    }
  }

  implicit val dv1DCT_Int: cosTr.Impl[DenseVector[Int], DenseVector[Double]] = {
    new Impl[DenseVector[Int], DenseVector[Double]] {
      def apply(v: DenseVector[Int]) = fourierTr( v.map( _.toDouble) )
    }
  }

  implicit val dv1DCT_Long: cosTr.Impl[DenseVector[Long], DenseVector[Double]] = {
    new Impl[DenseVector[Long], DenseVector[Double]] {
      def apply(v: DenseVector[Long]) = fourierTr( v.map( _.toDouble) )
    }
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" DenseMatrix DCTs ">

  implicit val dmDouble2DCT : cosTr.Impl[DenseMatrix[Double], DenseMatrix[Double]] = {
    new Impl[DenseMatrix[Double], DenseMatrix[Double]] {
      def apply(v: DenseMatrix[Double]) = {
        val tempMat = JavaArrayOps.dmDToArray2( v )
        val dct_instance = getDoubleDCT2DInst(v.rows, v.cols)
        dct_instance.forward( tempMat, false ) //does operation in place
        JavaArrayOps.array2DToDm( tempMat )
      }
    }
  }

  implicit val dmFloat2DCT : cosTr.Impl[DenseMatrix[Float], DenseMatrix[Float]] = {
    new Impl[DenseMatrix[Float], DenseMatrix[Float]] {
      def apply(v: DenseMatrix[Float]) = {
        val tempMat = JavaArrayOps.dmFToArray2( v )
        val dct_instance = getFloatDCT2DInst(v.rows, v.cols)
        dct_instance.forward( tempMat, false ) //does operation in place
        JavaArrayOps.array2FToDm( tempMat )
      }
    }
  }

  // </editor-fold>

    //ToDo 3: implement once Fourier with range specifications is more mature
//  implicit val dvDouble1DDCTRange: Impl2[DenseVector[Double], Range, DenseVector[Complex]] = {


}
