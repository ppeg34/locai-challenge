package solvers

class FisherYatesRecursiveSolverSpec extends SolverSpec {
  override def iterations: Int = 10
  def solver: SolverInterface = FisherYatesRecursiveSolver
}

