import com.github.nscala_time.time.Imports._
import com.lucidmachinery.bitclock._
import com.lucidmachinery.bitclock.BitClock._
import org.scalatest._

class BitClockSpec extends WordSpec with Matchers {
  "bit" when {
    "the given place value is active in the given digit" should {
      "return One" in {
        bit(8, Digit.Nine)  should be (Bit.One)
        bit(4, Digit.Six)   should be (Bit.One)
        bit(2, Digit.Six)   should be (Bit.One)
        bit(1, Digit.Seven) should be (Bit.One)
      }
    }

    "the given place value is not active in the given digit" should {
      "return Zero" in {
        bit(8, Digit.Seven) should be (Bit.Zero)
        bit(4, Digit.Eight) should be (Bit.Zero)
        bit(2, Digit.Nine)  should be (Bit.Zero)
        bit(1, Digit.Four)  should be (Bit.Zero)
      }
    }
  }

  "bitDigit" when {
    "given a Digit" should {
      "return a Tuple of 4 Bits representing that number" in {
        bitDigit(Digit.Zero)  should be ((Bit.Zero, Bit.Zero, Bit.Zero, Bit.Zero))
        bitDigit(Digit.One)   should be ((Bit.Zero, Bit.Zero, Bit.Zero, Bit.One))
        bitDigit(Digit.Two)   should be ((Bit.Zero, Bit.Zero, Bit.One, Bit.Zero))
        bitDigit(Digit.Three) should be ((Bit.Zero, Bit.Zero, Bit.One, Bit.One))
        bitDigit(Digit.Four)  should be ((Bit.Zero, Bit.One, Bit.Zero, Bit.Zero))
        bitDigit(Digit.Five)  should be ((Bit.Zero, Bit.One, Bit.Zero, Bit.One))
        bitDigit(Digit.Six)   should be ((Bit.Zero, Bit.One, Bit.One, Bit.Zero))
        bitDigit(Digit.Seven) should be ((Bit.Zero, Bit.One, Bit.One, Bit.One))
        bitDigit(Digit.Eight) should be ((Bit.One, Bit.Zero, Bit.Zero, Bit.Zero))
        bitDigit(Digit.Nine)  should be ((Bit.One, Bit.Zero, Bit.Zero, Bit.One))
      }
    }
  }

  "bitTime" when {
    "given a LocalTime" should {
      "return 6 BitDigits representing that LocalTime's time in HHmmss format" in {
        bitTime(LocalTime.parse("12:34:56")) should be ((
          (Bit.Zero, Bit.Zero, Bit.Zero, Bit.One),  // 1
          (Bit.Zero, Bit.Zero, Bit.One , Bit.Zero), // 2
          (Bit.Zero, Bit.Zero, Bit.One, Bit.One),   // 3
          (Bit.Zero, Bit.One, Bit.Zero, Bit.Zero),  // 4
          (Bit.Zero, Bit.One, Bit.Zero, Bit.One),   // 5
          (Bit.Zero, Bit.One, Bit.One, Bit.Zero),   // 6
        ))
      }
    }
  }

  "prettyPrint" when {
    "given a BitTime" should {
      "return the BitTime as columns of BitDigits in the order HHmmss." in {
        val prettyStr = """
                            |0 0 0 0 0 0
                            |0 0 0 1 1 1
                            |0 1 1 0 0 1
                            |1 0 1 0 1 0
                            """.stripMargin('|')
        prettyPrint(bitTime(LocalTime.parse("12:34:56"))) should be (prettyStr)
      }
    }
  }
}
