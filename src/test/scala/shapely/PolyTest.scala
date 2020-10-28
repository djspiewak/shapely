package shapely

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PolyTest extends AnyWordSpec with Matchers {

  "Poly" should {

    "accept different types" in {
      object add extends Poly {
        implicit val i = at[Int] { _ + 1 }
        implicit val d = at[Double] { _ + 1 }
      }

      val result1: Int = add(42)
      val result2: Double = add(3.14)

      result1 mustBe 43
      result2 mustBe (4.14 +- 1E-10)
    }
  }
}