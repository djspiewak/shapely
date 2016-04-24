package shapely

trait Poly {

  def at[A] = new Caser[A]

  final class Caser[A] {
    def apply[B](f: A => B): Case[A, B] = new Case[A, B] {
      def apply(a: A) = f(a)
    }
  }

  trait Case[A, B] {
    def apply(a: A): B
  }
}