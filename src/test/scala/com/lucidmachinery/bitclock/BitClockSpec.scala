import org.scalatest._

class BitClockSpec extends WordSpec {
  "Bit" when {
    "the given place value is active in the given digit" should {
      "return 1" in {
        fail // TODO
      }
    }
    "the given place value is not active in the given digit" should {
      "return 0" in {
        fail // TODO
      }
    }
  }

  "BitDigit" when {
    "given a positive integer 1 <= n <= 9" should {
      "return an array of 4 Bits representing that number" in {
        fail // TODO
      }
    }
  }

  "BitTime" when {
    "given a DateTime" should {
      "return 6 BitDigits representing that DateTime's time in HHmmss format" in {
        fail // TODO
      }
    }
    "given no parameters" should {
      "return 6 BitDigits representing the current time in HHmmss format" in {
        fail // TODO
      }
    }
  }
}
