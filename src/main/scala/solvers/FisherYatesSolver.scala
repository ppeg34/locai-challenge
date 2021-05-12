package solvers

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object FisherYatesSolver extends SolverInterface {

  def generateList(): List[Int] = {
    makeList().toList
  }

  def makeList(): ArrayBuffer[Int] = {
    val buf: ArrayBuffer[Int] = (1 to 10000).to[ArrayBuffer]

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
