package solvers

import scala.util.Random

object NaiveSolver extends SolverInterface {
  def generateList(): List[Int] = {
    Random.shuffle(1 to 10000 toList)
  }
}
