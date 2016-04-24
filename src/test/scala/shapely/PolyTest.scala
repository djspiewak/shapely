package shapely

object PolyTest {

  {
    object add extends Poly {
      implicit val i = at[Int] { _ + 1 }
      implicit val d = at[Double] { _ + 1 }
    }

    add(42)
    add(3.14)
  }
}