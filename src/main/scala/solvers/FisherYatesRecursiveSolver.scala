package solvers

import scala.annotation.tailrec
import scala.util.Random

// Generates random 10k number list using fisher-yates algorithm recursively
// https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#Pencil-and-paper_method
object FisherYatesRecursiveSolver extends SolverInterface {
  override def generateList(): List[Int] = makeList().toList

  def makeList(): Vector[Int] = {

    @tailrec
    def iterateAlg(scratch: Vector[Int], results: Vector[Int]): Vector[Int] = {

      if (scratch.isEmpty) { return results }

      //split at random place in remaining scratch list
      val (a, b) = scratch splitAt Random.nextInt(scratch.length)

      //iterate with the randomly selected index removed from scratch
      // and placed at the end of the result list
      iterateAlg(a ++ b.tail, results ++ Vector(b.head))
    }

    iterateAlg( 1 to 10000 toVector, Vector() )
  }

  override def runCalc(): Unit = makeList()

}
