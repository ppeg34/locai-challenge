package solvers

class OverhandSolverSpec extends SolverSpec {
  def solver: SolverInterface = OverhandSolver

  override def iterations: Int = 100
}

