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
}