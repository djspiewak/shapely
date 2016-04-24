package shapely

trait Nat

object Nat {

  def toInt[N <: Nat](implicit N: ToInt[N]): Int = N.value
}

trait Zero extends Nat

trait Succ[N <: Nat] extends Nat