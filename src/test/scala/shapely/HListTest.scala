package shapely

object HListTest {

  {
    val xs = 1 :: false :: "hi" :: HNil

    xs.head: Int
  }

  {
    val xs: Int :: Boolean :: HNil = 1 :: false :: HNil

    xs.tail.head: Boolean
  }

  {
    val xs = (1 :: HNil) ++ (false :: HNil)

    xs.tail.head: Boolean
  }

  {
    val xs = 1 :: false :: HNil

    xs.remove[Int].head: Boolean
    xs.remove[Boolean].head: Int
  }

  {
    val xs = 1 :: false :: HNil

    object doubleFlip extends Poly {
      implicit val i = at[Int] { _ * 2 }
      implicit val b = at[Boolean] { !_ }
    }

    object toString extends Poly {
      implicit def default[A] = at[A] { _.toString }
    }

    xs map doubleFlip

    xs map toString head: String
  }
}