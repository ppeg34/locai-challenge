package solvers

import scala.collection.mutable.ArrayBuffer

trait SolverInterface {
  def generateList(): List[Int]
  // Runs the array generation without returning any input
  // Useful for profiling
  def runCalc(): Unit = {
    generateList()
  }
}
