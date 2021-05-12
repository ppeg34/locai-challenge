package solvers

import scala.annotation.tailrec
import scala.util.Random

object OverhandSolver extends SolverInterface {
  override def generateList(): List[Int] = makeList().toList

  def makeList(): Seq[Int] = {

    @tailrec
    def shuffleOnce(left: Seq[Int], right: Seq[Int]): Seq[Int] = {
      if(right.isEmpty) {
        return left
      }

      val (top, bottom) = right.splitAt(Random.nextInt(100))
      shuffleOnce(top ++ left, bottom)
    }

    @tailrec
    def shuffle(cards: Seq[Int], iterations: Int): Seq[Int] = {
      if (iterations == 0) {
        return cards
      }

      shuffle(shuffleOnce(Seq(), cards), iterations - 1)
    }

    shuffle(1 to 10000, 5)
  }
}
