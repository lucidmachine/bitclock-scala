package com.lucidmachinery.bitclock

object Digit extends Enumeration {
  type Digit = Value
  val Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine = Value
}

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

  def isActive(placeValue: Int, digit: Digit): Boolean =
    (placeValue & digit.id) > 0

  def bit(placeValue: Int, digit: Digit): Bit =
    if (isActive(placeValue, digit)) Bit.One
    else Bit.Zero

  def bitDigit(digit: Digit): BitDigit = (
    bit(8, digit),
    bit(4, digit),
    bit(2, digit),
    bit(1, digit),
  )

  def bitTime(time: LocalTime): BitTime = (
    bitDigit(Digit(time.hour.get / 10)),
    bitDigit(Digit(time.hour.get % 10)),
    bitDigit(Digit(time.minute.get / 10)),
    bitDigit(Digit(time.minute.get % 10)),
    bitDigit(Digit(time.second.get / 10)),
    bitDigit(Digit(time.second.get % 10)),
  )

  def bitTime(): BitTime =
    bitTime(LocalTime.now())

  def main(args: Array[String]): Unit = {
    println(bitTime())
  }
}
