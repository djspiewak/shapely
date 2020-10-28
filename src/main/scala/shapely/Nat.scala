package shapely

import scala.annotation.tailrec
import scala.language.experimental.macros
import scala.reflect.macros.whitebox

trait Nat {
  type N <: Nat
}

object Nat {
  implicit def fromInt(i: Int): Nat = macro NatMacros.materialize

  def toInt[N <: Nat](implicit N: ToInt[N]): Int =
    N.value
}

@macrocompat.bundle
class NatMacros(val c: whitebox.Context) {
  import c.universe._

  def materialize(i: Tree): Tree = {
    @tailrec
    def loop(n: Int, acc: Tree): Tree =
      if (n <= 0) acc
      else loop(n - 1, q"shapely.Succ($acc)")

    i match {
      case Literal(Constant(n: Int)) if n >= 0 =>
        loop(n, q"shapely.Zero")
      case _ =>
        c.abort(c.enclosingPosition, s"not a natural number")
    }
  }
}

object Zero extends Nat {
  override type N = Zero.type
}

final case class Succ[N0 <: Nat](n: N0) extends Nat {
  override type N = Succ[N0]
}