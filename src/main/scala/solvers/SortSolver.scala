package solvers

import scala.util.Random

object SortSolver extends SolverInterface {
  def generateList(): List[Int] = {
    (1 to 10000 toList) // generate range 1 to 10000
      .map((_, Random.nextInt())) // map to a list of tuples: (ranged number, random number)
      .sortBy(_._2) //sort by random number
      .map(_._1) //no need for the random number anymore
  }
}
