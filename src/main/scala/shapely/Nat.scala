package shapely

import scala.language.experimental.macros

import scala.reflect.macros.whitebox

trait Nat

object Nat {

  implicit def materialize(i: Int): Nat = macro NatMacros.materialize

  def toInt[N <: Nat](implicit N: ToInt[N]): Int = N.value
}

@macrocompat.bundle
class NatMacros(val c: whitebox.Context) {
  import c.universe._

  def materialize(i: Tree): Tree = {
    def loop(n: Int, acc: Tree): Tree = if (n <= 0) acc else loop(n - 1, q"new shapely.Succ($acc)")

    i match {
      case Literal(Constant(n: Int)) => loop(n, q"shapely.Zero")

      case _ => c.abort(c.enclosingPosition, s"expression $i is not a non-negative integer literal")
    }
  }
}

object Zero0 extends Nat

class Succ[N <: Nat](n: N) extends Nat