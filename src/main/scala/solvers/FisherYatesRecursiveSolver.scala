package solvers

import scala.annotation.tailrec
import scala.util.Random

object FisherYatesRecursiveSolver extends SolverInterface {

  def generateList(): List[Int] = {
    makeList().map(_.toInt).toList
  }

  def makeList(): Seq[Short] = {
    @tailrec
    def doit(items: Seq[Short], iteration: Short): Seq[Short] = {
      if (iteration > 0) {
        val k = Random.nextInt(iteration)
        val (a, b) = items.splitAt(k)
        doit(a ++ b.tail ++ Seq(b.head), (iteration - 1).toShort)
      } else {
        items
      }
    }

    doit( 1 to 10000 map(a => a.toShort), 10000 )
  }

  override def runCalc(): Unit = makeList()
}
