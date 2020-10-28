package shapely

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class HListTest extends AnyWordSpec with Matchers {

  "HList" should {

    "have proper type" in {
      val list = 1 :: false :: "hi" :: HNil

      val result: Int :: Boolean :: String :: HNil = list

      result mustBe 1 :: false :: "hi" :: HNil
    }

    "have head with proper type" in {
      val list = 1 :: false :: "hi" :: HNil

      val result: Int = list.head

      result mustBe 1
    }

    "have tail with proper type" in {
      val list = 1 :: false :: HNil

      val result: Boolean :: HNil = list.tail

      result mustBe false :: HNil
    }

    "concatenate with another list" in {
      val list = (1 :: HNil) ++ (false :: HNil)

      val result: Int :: Boolean :: HNil = list

      result mustBe 1 :: false :: HNil
    }

    "remove a specific type" in {
      val list = 1 :: false :: HNil

      val result1: Boolean:: HNil = list.remove[Int]
      val result2: Int :: HNil = list.remove[Boolean]

      result1 mustBe false :: HNil
      result2 mustBe 1 :: HNil
    }

    "not remove a type that is not present" in {
      val list = 1 :: false :: HNil

      val result: Int :: Boolean :: HNil = list.remove[String]

      result mustBe list
    }

    "remove all instances of a specific type (1)" in {
      val list = 1 :: 1 :: false :: HNil

      val result1: Boolean :: HNil = list.remove[Int]
      val result2: Int :: Int :: HNil = list.remove[Boolean]

      result1 mustBe false :: HNil
      result2 mustBe 1 :: 1 :: HNil
    }

    "remove all instances of a specific type (2)" in {
      val list = 1 :: false :: 1 :: HNil

      val result1: Boolean :: HNil = list.remove[Int]
      val result2: Int :: Int :: HNil = list.remove[Boolean]

      result1 mustBe false :: HNil
      result2 mustBe 1 :: 1 :: HNil
    }

    /* TODO: fix
    "not remove anything from an empty list" in {
      val list = HNil

      val result: HNil = list.remove[Int]

      result mustBe HNil
    }
    */

    "map each list element" in {
      val list = 1 :: false :: HNil

      object doubleFlip extends Poly {
        implicit val i = at[Int] { _ * 2 }
        implicit val b = at[Boolean] { !_ }
      }

      val result: Int :: Boolean :: HNil = list.map(doubleFlip)

      result mustBe 2 :: true :: HNil
    }

    "map to the proper type" in {
      val list = 1 :: false :: HNil

      object toString extends Poly {
        implicit def default[A] = at[A] { _.toString }
      }

      val result: String :: String :: HNil = list.map(toString)

      result mustBe "1" :: "false" :: HNil
    }

    /* TODO: fix
    "return nth list element with proper type" in {
      val list = 1 :: false :: HNil

      val result1: Int = list.nth(0)
      val result2: Boolean = list.nth(1)

      result1 mustBe 1
      result2 mustBe false
    }
    */
  }
}