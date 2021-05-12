package solvers

import org.scalatest.funsuite._

trait SolverSpec extends AnyFunSuite {
  def solver: SolverInterface
  def iterations: Int = 10000

  private val className = this.getClass.getSimpleName
  private val resultList = solver.generateList()

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

  time { (1 to iterations).foreach( _ => solver.runCalc() ) }

  // times the duration of executing different solvers.
  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block    // call-by-name
    val t1 = System.nanoTime()

    val nanosPerIter = (t1 - t0) / iterations
    info(s"${solver.getClass.getSimpleName } time: ${ nanosPerIter }ns per iteration")
    result
  }
}
