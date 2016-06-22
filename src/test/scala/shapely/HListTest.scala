package shapely

object HListTest {

  // result should have proper type
  {
    val xs = 1 :: false :: "hi" :: HNil

    xs: Int :: Boolean :: String :: HNil
  }

  // head should return proper type
  {
    val xs = 1 :: false :: "hi" :: HNil

    xs.head: Int
  }

  // head should return proper type
  {
    val xs = 1 :: false :: HNil

    xs.tail: Boolean :: HNil
  }

  // ++ should concatenate properly
  {
    val xs = (1 :: HNil) ++ (false :: HNil)

    xs: Int :: Boolean :: HNil
  }

  // remove should have proper type
  {
    val xs = 1 :: false :: HNil

    xs.remove[Int].head: Boolean // TODO: xs.remove[Int]: Boolean :: HNil
    xs.remove[Boolean].head: Int // TODO: xs.remove[Boolean]: Int :: HNil
  }

  // remove should work for type that is not contained
  {
    val xs = 1 :: false :: HNil

    xs.remove[String].head: Int // TODO: xs.remove[String]: Boolean :: Int :: HNil
  }

  // remove should work for multiple instance of the same type (1)
  {
    val xs = 1 :: 1 :: false :: HNil

    xs.remove[Int].head: Boolean // TODO: xs.remove[Int]: Boolean :: HNil
    xs.remove[Boolean].head: Int // TODO: xs.remove[Boolean]: Int :: Int :: HNil
  }

  // remove should work for multiple instance of the same type (2)
  {
    val xs = 1 :: false :: 1 :: HNil

    xs.remove[Int].head: Boolean // TODO: xs.remove[Int]: Boolean :: HNil
    xs.remove[Boolean].head: Int // TODO: xs.remove[Boolean]: Int :: Int :: HNil
  }

  // remove should work for empty HList
  {
    val xs = HNil

    xs.remove[Int] // TODO: xs.remove[Int]: HNil
  }

  // map should process each element
  {
    val xs = 1 :: false :: HNil

    object doubleFlip extends Poly {
      implicit val i = at[Int] { _ * 2 }
      implicit val b = at[Boolean] { !_ }
    }

    xs.map(doubleFlip): Int :: Boolean :: HNil
  }

  // map should return proper types
  {
    val xs = 1 :: false :: HNil

    object toString extends Poly {
      implicit def default[A] = at[A] { _.toString }
    }

    xs.map(toString): String :: String :: HNil
  }

  // nth should return proper type
  {
    val xs = 1 :: false :: HNil

    xs.nth(0): Int
    xs.nth(1): Boolean
  }
}