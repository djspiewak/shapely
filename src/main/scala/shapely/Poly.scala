package shapely

trait Poly {
  sealed trait Case[A, B] {
    def apply(a: A): B
  }

  final def at[A]: Caser[A] =
    new Caser[A]

  final def apply[A, B](a: A)(implicit C: Case[A, B]): B =
    C(a)

  final class Caser[A] {
    def apply[B](f: A => B): Case[A, B] =
      new Case[A, B] {
        override def apply(a: A): B =
          f(a)
      }
  }
}