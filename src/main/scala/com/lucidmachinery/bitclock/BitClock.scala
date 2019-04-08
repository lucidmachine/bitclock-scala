package com.lucidmachinery.bitclock

/** All digits 0-9. */
object Digit extends Enumeration {
  type Digit = Value
  val Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine = Value
}

/** All bits 0-1. */
object Bit extends Enumeration {
  type Bit = Value
  val Zero, One = Value
}

object BitClock {
  import com.github.nscala_time.time.Imports._

  import Digit._
  import Bit._
  type BitDigit = Tuple4[Bit, Bit, Bit, Bit]
  type BitTime = Tuple6[BitDigit, BitDigit, BitDigit, BitDigit, BitDigit, BitDigit]

  /** Returns whether the given place value is active in the given digit. */
  def isActive(placeValue: Int, digit: Digit): Boolean =
    (placeValue & digit.id) > 0

  /** Returns a Bit representing the given place value in the given digit. */
  def bit(placeValue: Int, digit: Digit): Bit =
    if (isActive(placeValue, digit)) Bit.One
    else Bit.Zero

  /** Returns a BitDigit representing the given digit. */
  def bitDigit(digit: Digit): BitDigit = (
    bit(8, digit),
    bit(4, digit),
    bit(2, digit),
    bit(1, digit),
  )

  /** Returns a BitTime representing the given time. */
  def bitTime(time: LocalTime): BitTime = (
    bitDigit(Digit(time.hour.get / 10)),
    bitDigit(Digit(time.hour.get % 10)),
    bitDigit(Digit(time.minute.get / 10)),
    bitDigit(Digit(time.minute.get % 10)),
    bitDigit(Digit(time.second.get / 10)),
    bitDigit(Digit(time.second.get % 10)),
  )

  /** Returns the BitTime as columns of BitDigits in the order HHmmss. */
  def prettyPrint(bitTime: BitTime): String =
    ???

  /** Prints the current BitTime to stdout. */
  def main(args: Array[String]): Unit = {
    println(prettyPrint(bitTime(LocalTime.now())))
  }
}
