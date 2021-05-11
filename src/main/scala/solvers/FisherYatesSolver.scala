package solvers

import scala.collection.immutable.NumericRange
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object FisherYatesSolver extends SolverInterface {

  def generateList(): List[Int] = {
    makeList().map(_.toInt).toList
  }

  def makeList(): ArrayBuffer[Short] = {
    val buf: ArrayBuffer[Short] = NumericRange(1: Short, 10001: Short, 1: Short).to[ArrayBuffer]

    def swap(i1: Int, i2: Int): Unit = {
      val tmp = buf(i1)
      buf(i1) = buf(i2)
      buf(i2) = tmp
    }

    for (n <- buf.length to 2 by -1) {
      val k = Random.nextInt(n)
      swap(n - 1, k)
    }

    buf
  }

  override def runCalc(): Unit = makeList()
}
