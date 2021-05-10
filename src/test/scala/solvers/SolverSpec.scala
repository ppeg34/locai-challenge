package solvers

import org.scalatest.funsuite._

trait SolverSpec extends AnyFunSuite {
  def solver: SolverInterface

  val className = this.getClass.getSimpleName
  val resultList = solver.generateList()

  test(className + " length is 10000") {
    assert(resultList.length == 10000)
  }

  test(className + " has no repeats") {
    resultList.toSet.toList.length == 10000
  }

  test(className + " has all 10000 numbers") {
    (1 to 10000)
      .flatMap(n => resultList.find(b => b == n) )
      .length
      .equals(10000)
  }

  time { (1 to 10000).foreach( _ => solver.runCalc() ) }

  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block    // call-by-name
    val t1 = System.nanoTime()
    info(s"${solver.getClass.getSimpleName} time: ${t1 - t0}ns")
    result
  }
}
