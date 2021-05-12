package solvers

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

// Modern implementation fo the fisher-yates algorithm
// https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#Modern_method

// This is a simplified version of scala.util.Random.shuffle
// https://github.com/scala/scala/blob/v2.13.5/src/library/scala/util/Random.scala#L260
object FisherYatesSolver extends SolverInterface {

  def generateList(): List[Int] = {
    makeList().toList
  }

  def makeList(): ArrayBuffer[Int] = {
    // Instantiate 1 to 10000 ArrayBuffer.
    // ArrayBuffers are mutable constant-time append/update data structures
    val buf: ArrayBuffer[Int] = (1 to 10000).to[ArrayBuffer]

    // exchanges two indexes in the array buffer above
    def swap(i1: Int, i2: Int): Unit = {
      val tmp = buf(i1)
      buf(i1) = buf(i2)
      buf(i2) = tmp
    }

    // iterate on succeedingly smaller chunks of the array buffer,
    // swapping the last item of the list with a random item from the chunk
    for (n <- buf.length to 2 by -1) {
      val k = Random.nextInt(n)
      swap(n - 1, k)
    }

    buf
  }

  override def runCalc(): Unit = makeList()
}
