package shapely

trait Poly {

  final def at[A] = new Caser[A]

  def apply[A, B](a: A)(implicit C: this.Case[A, B]): B = C(a)

  final class Caser[A] {
    def apply[B](f: A => B): Case[A, B] = new Case[A, B] {
      def apply(a: A) = f(a)
    }
  }

  sealed trait Case[A, B] {
    def apply(a: A): B
  }
}