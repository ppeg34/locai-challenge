package solvers

import scala.util.Random

object NaiveSolver extends SolverInterface {
  def generateList(): List[Int] = {
    //shuffle a range of numbers, 1 to 10000
    Random.shuffle(1 to 10000 toList)
  }
}
