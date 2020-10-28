package shapely

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class NatTest extends AnyWordSpec with Matchers {

  "Nat" should {

    "convert from integers" in {
      val result = Nat.fromInt(2)

      result mustBe Succ(Succ(Zero))
    }

    "convert to integers" in {
      val nat = Nat.fromInt(5)

      val result = nat.toInt

      result mustBe 5
    }
  }
}