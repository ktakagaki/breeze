package breeze.signal.support

import edu.emory.mathcs.jtransforms.fft.{FloatFFT_2D, FloatFFT_1D, DoubleFFT_1D, DoubleFFT_2D}
import breeze.linalg.{DenseVector, DenseMatrix}
import breeze.math.Complex
import edu.emory.mathcs.jtransforms.dct.{FloatDCT_2D, FloatDCT_1D, DoubleDCT_2D, DoubleDCT_1D}

/** This class encapsulates convenience methods to use the JTransforms package.
 *
 * Created with IntelliJ IDEA.
 * User: takagaki
 * Date: 25.06.13
 * Time: 21:02
 * To change this template use File | Settings | File Templates.
 */
object JTransformsSupport {

  // <editor-fold defaultstate="collapsed" desc=" maintain instances of transforms to eliminate repeated initialization ">
  
  private var fft_instD1D: (Int, DoubleFFT_1D) = (0, null)
  def getDoubleFFT1DInst(length: Int): DoubleFFT_1D = {
    if(length == fft_instD1D._1) fft_instD1D._2
    else {
      fft_instD1D = (length, new DoubleFFT_1D(length))
      fft_instD1D._2
    }
  }
  private var fft_instD2D: (Int, Int, DoubleFFT_2D) = (0, 0, null)
  def getDoubleFFT2DInst(rows: Int, columns: Int): DoubleFFT_2D = {
    if(rows == fft_instD2D._1 && columns == fft_instD2D._2) fft_instD2D._3
    else {
      fft_instD2D = (rows, columns, new DoubleFFT_2D(rows, columns))
      fft_instD2D._3
    }
  }
  private var fft_instF1D: (Int, FloatFFT_1D) = (0, null)
  def getFloatFFT1DInst(length: Int): FloatFFT_1D = {
    if(length == fft_instF1D._1) fft_instF1D._2
    else {
      fft_instF1D = (length, new FloatFFT_1D(length))
      fft_instF1D._2
    }
  }
  private var fft_instF2D: (Int, Int, FloatFFT_2D) = (0, 0, null)
  def getFloatFFT2DInst(rows: Int, columns: Int): FloatFFT_2D = {
    if(rows == fft_instF2D._1 && columns == fft_instF2D._2) fft_instF2D._3
    else {
      fft_instF2D = (rows, columns, new FloatFFT_2D(rows, columns))
      fft_instF2D._3
    }
  }


  private var dct_instD1D: (Int, DoubleDCT_1D) = (0, null)
  def getDoubleDCT1DInst(length: Int): DoubleDCT_1D = {
    if(length == dct_instD1D._1) dct_instD1D._2
    else {
      dct_instD1D = (length, new DoubleDCT_1D(length))
      dct_instD1D._2
    }
  }
  private var dct_instD2D: (Int, Int, DoubleDCT_2D) = (0, 0, null)
  def getDoubleDCT2DInst(rows: Int, columns: Int): DoubleDCT_2D = {
    if(rows == dct_instD2D._1 && columns == dct_instD2D._2) dct_instD2D._3
    else {
      dct_instD2D = (rows, columns, new DoubleDCT_2D(rows, columns))
      dct_instD2D._3
    }
  }
  private var dct_instF1D: (Int, FloatDCT_1D) = (0, null)
  def getFloatDCT1DInst(length: Int): FloatDCT_1D = {
    if(length == dct_instF1D._1) dct_instF1D._2
    else {
      dct_instF1D = (length, new FloatDCT_1D(length))
      dct_instF1D._2
    }
  }
  private var dct_instF2D: (Int, Int, FloatDCT_2D) = (0, 0, null)
  def getFloatDCT2DInst(rows: Int, columns: Int): FloatDCT_2D = {
    if(rows == dct_instF2D._1 && columns == dct_instF2D._2) dct_instF2D._3
    else {
      dct_instF2D = (rows, columns, new FloatDCT_2D(rows, columns))
      dct_instF2D._3
    }
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" convert between DenseVector/DenseMatrix and complex array format for JTransforms ">

  def tempToDenseVector(tempArr: Array[Double]): DenseVector[Complex] = {
    val tempRet = DenseVector.zeros[Complex](tempArr.length/2)
    for (n <- 0 until tempRet.length) tempRet(n) = new Complex( tempArr(2*n), tempArr(2*n+1))
    tempRet
  }

  /**
   * Reformat for input: note difference in format with denseVectorDToTemp
   * @param tempDV
   * @return
   */
  def denseVectorCToTemp(tempDV: DenseVector[Complex]): Array[Double] = {
    val tempRet = new Array[Double](tempDV.length*2)
    for(n <- 0 until tempDV.length) {
      tempDV(n) match {
        case Complex(re, im) => {
          tempRet(2*n) = re
          tempRet(2*n+1) = im
        }
      }
    }
    tempRet
  }

  /**
   * Reformat for input: note difference in format with denseVectorCToTemp
   * @param tempDV
   * @return
   */
  def denseVectorDToTemp(tempDV: DenseVector[Double]): Array[Double] = {
    val tempArr = new Array[Double](tempDV.length*2)
    for(n <- 0 until tempDV.length) tempArr(n) = tempDV(n)
    tempArr
  }

  /**
   * Reformat for input
   * @param tempDM
   * @return
   */
  def denseMatrixCToTemp(tempDM: DenseMatrix[Complex]): Array[Double] = {
    val tempCols = tempDM.cols
    val tempRet = new Array[Double](tempDM.rows * tempCols * 2)
    for(r <- 0 until tempDM.rows; c <- 0 until tempDM.cols) {
      tempDM(r, c) match {
        case Complex(re, im) => {
          val ind = r*2*tempCols + 2*c
          tempRet(ind) = re
          tempRet(ind + 1) = im
        }
      }
    }
    tempRet
  }
  /**
   * Reformat for input
   * @param tempDM
   * @return
   */
  def denseMatrixDToTemp(tempDM: DenseMatrix[Double]): Array[Double] = {
    val tempCols = tempDM.cols
    val tempRet = new Array[Double](tempDM.rows * tempCols * 2)
    for(r <- 0 until tempDM.rows; c <- 0 until tempDM.cols) {
      tempRet(r*2*tempCols + 2*c) = tempDM(r, c)
    }
    tempRet
  }

  def tempToDenseMatrix(tempArr: Array[Double], rows: Int, cols: Int): DenseMatrix[Complex] = {
    val tempRet = DenseMatrix.zeros[Complex](rows, cols)
    for (r <- 0 until rows; c <- 0 until cols) {
      val ind = r*2*cols + 2*c
      tempRet(r, c) = new Complex( tempArr(ind), tempArr(ind + 1))
    }
    tempRet
  }

  // </editor-fold>

}
