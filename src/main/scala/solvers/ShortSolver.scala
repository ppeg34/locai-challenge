package solvers

import scala.collection.immutable.NumericRange
import scala.util.Random

//This is the same as NaiveSolver, but with Shorts instead of type Ints
object ShortSolver extends SolverInterface {
  def generateList(): List[Int] = {
    shortList().map(_.toInt)
  }
  private def shortList(): List[Short] = {
    Random.shuffle(NumericRange(1.toShort, 10001.toShort, 1.toShort).toList)
  }
  override def runCalc(): Unit = {
    shortList()
  }
}
