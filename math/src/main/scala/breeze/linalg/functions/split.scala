package breeze.linalg

import breeze.generic.UFunc
import scala.reflect.ClassTag
import spire.implicits._
import breeze.storage.Zero

/**
 * split the array
 *
 * @author stucchio
 */
object split extends UFunc {

  implicit def implIntVec[T: ClassTag]: Impl2[DenseVector[T], Int, Seq[DenseVector[T]]] = new Impl2[DenseVector[T], Int, Seq[DenseVector[T]]] {
    def apply(v: DenseVector[T], n: Int): Seq[DenseVector[T]] = {
      require(n >= 0)
      require(n < v.size)
      require(v.size % n == 0)

      val individualVectorSize = v.size / n
      val result = new collection.mutable.ArrayBuffer[DenseVector[T]]()

      cforRange(0 until n) { k =>
        val offsetInOriginalVector = k*individualVectorSize
        val chunk = new Array[T](individualVectorSize)
        cforRange(0 until individualVectorSize){i =>
          chunk(i) = v(offsetInOriginalVector+i)
        }
        result += DenseVector[T](chunk)
      }
      result
    }
  }

  implicit def implSeqVec[T: ClassTag]: Impl2[DenseVector[T], Seq[Int], Seq[DenseVector[T]]] = new Impl2[DenseVector[T], Seq[Int], Seq[DenseVector[T]]] {
    def apply(v: DenseVector[T], nSeq: Seq[Int]): Seq[DenseVector[T]] = {
      require(nSeq.size < v.size)

      val result = new collection.mutable.ListBuffer[DenseVector[T]]()
      var lastN: Int = 0
      nSeq.foreach(n => {
        val chunk = new Array[T](n - lastN)
        cfor(lastN)(i => i < n, i => i + 1)(i => {
          chunk(i-lastN) = v(i)
        })
        result += DenseVector[T](chunk)
        lastN = n
      })
      if (lastN < v.size) { //If we did not already add last chunk to result, do it now.
        val chunk = new Array[T](v.size - lastN)
        cfor(lastN)(i => i < v.size, i => i + 1)(i => {
          chunk(i-lastN) = v(i)
        })
        result += DenseVector[T](chunk)
      }
      result.toSeq
    }
  }

  implicit def implIntMatrix[T: ClassTag](implicit zero: Zero[T]): Impl3[DenseMatrix[T], Int, Int, Seq[DenseMatrix[T]]] = new Impl3[DenseMatrix[T], Int, Int, Seq[DenseMatrix[T]]] {
    def apply(v: DenseMatrix[T], n: Int, axis: Int): Seq[DenseMatrix[T]] = axis match {
      case 0 => vsplit(v,n)
      case 1 => hsplit(v,n)
      case _ => throw new IllegalArgumentException("Matrices have only two axes.")
    }
  }
}

object hsplit extends UFunc {
  implicit def implIntVec[T: ClassTag]: Impl2[DenseVector[T], Int, Seq[DenseVector[T]]] = new Impl2[DenseVector[T], Int, Seq[DenseVector[T]]] { //For vectors just an alias
    def apply(v: DenseVector[T], n: Int): Seq[DenseVector[T]] = hsplit(v,n)
  }

  implicit def implSeqVec[T: ClassTag]: Impl2[DenseVector[T], Seq[Int], Seq[DenseVector[T]]] = new Impl2[DenseVector[T], Seq[Int], Seq[DenseVector[T]]] { //For vectors just an alias
    def apply(v: DenseVector[T], n: Seq[Int]): Seq[DenseVector[T]] = hsplit(v,n)
  }

  implicit def implIntMat[T: ClassTag](implicit zero: Zero[T]): Impl2[DenseMatrix[T], Int, Seq[DenseMatrix[T]]] = new Impl2[DenseMatrix[T],Int, Seq[DenseMatrix[T]]] { //for matrices
    def apply(v: DenseMatrix[T], n: Int): Seq[DenseMatrix[T]] = {
      require(n >= 0)
      require(n < v.cols)
      require(v.cols % n == 0)

      val result = new collection.mutable.ListBuffer[DenseMatrix[T]]()
      val newCols = v.cols / n
      val newSize = v.rows * newCols

      cfor(0)(k => k < n, k => k+1)(k => {
        val offsetInOriginalMatrix = k*newCols
        val chunk = DenseMatrix.create(v.rows, newCols, new Array[T](newSize))
        cfor(0)(i => i < v.rows, i => i+1)(i => {
          cfor(0)(j => j < newCols, j => j+1)(j => {
            chunk(i,j) = v(i,j+offsetInOriginalMatrix)
          })
        })
        result += chunk
      })
      result.toSeq
    }
  }
}

object vsplit extends UFunc {
  implicit def implIntMat[T: ClassTag](implicit zero: Zero[T]): Impl2[DenseMatrix[T], Int, Seq[DenseMatrix[T]]] = new Impl2[DenseMatrix[T],Int, Seq[DenseMatrix[T]]] { //for matrices
    def apply(v: DenseMatrix[T], n: Int): Seq[DenseMatrix[T]] = {
      require(n >= 0)
      require(n < v.cols)
      require(v.cols % n == 0)

      val result = new collection.mutable.ListBuffer[DenseMatrix[T]]()
      val newRows = v.rows / n

      cfor(0)(k => k < n, k => k+1)(k => {
        val offsetInOriginalMatrix = k*newRows
        val chunk = DenseMatrix.create(newRows, v.cols, new Array[T](v.cols * newRows))
        cfor(0)(i => i < newRows, i => i+1)(i => {
          cfor(0)(j => j < v.cols, j => j+1)(j => {
            chunk(i,j) = v(i+offsetInOriginalMatrix,j)
          })
        })
        result += chunk
      })
      result.toSeq
    }
  }
}
