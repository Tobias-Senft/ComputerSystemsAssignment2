import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class ALUTester extends AnyFlatSpec with ChiselScalatestTester {

  "ALUTester" should "pass" in {
    test(new ALU())
      .withAnnotations(Seq(WriteVcdAnnotation)) { dut =>

        val minusOne = 0xFFFFFFFFL

        //ADD
        System.out.println("\nTest ADD")
        dut.io.sel.poke(0)
        dut.io.a.poke(5)
        dut.io.b.poke(3)
        dut.clock.step(1)
        System.out.println("5 + 3 = " + dut.io.result.peekInt())
        dut.io.result.expect(8)

        dut.io.a.poke(minusOne)
        dut.io.b.poke(1)
        dut.clock.step(1)
        System.out.println("-1 + 1 = " + dut.io.result.peekInt())
        dut.io.result.expect(0)

        //SUB
        System.out.println("\nTest SUB")
        dut.io.sel.poke(1)
        dut.io.a.poke(10)
        dut.io.b.poke(4)
        dut.clock.step(1)
        System.out.println("10 - 4 = " + dut.io.result.peekInt())
        dut.io.result.expect(6)

        dut.io.a.poke(0)
        dut.io.b.poke(1)
        dut.clock.step(1)
        System.out.println("0 - 1 = " + dut.io.result.peekInt())
        dut.io.result.expect(minusOne)

        //MULT
        System.out.println("\nTest MULT")
        dut.io.sel.poke(2)
        dut.io.a.poke(6)
        dut.io.b.poke(7)
        dut.clock.step(1)
        System.out.println("6 * 7 = " + dut.io.result.peekInt())
        dut.io.result.expect(42)

        //EQ
        System.out.println("\nTest EQ")
        dut.io.sel.poke(3)
        dut.io.a.poke(42)
        dut.io.b.poke(42)
        dut.clock.step(1)
        System.out.println("42 == 42: " + dut.io.eq.peekBoolean())
        dut.io.eq.expect(true.B)

        dut.io.b.poke(41)
        dut.clock.step(1)
        System.out.println("42 == 41: " + dut.io.eq.peekBoolean())
        dut.io.eq.expect(false.B)

        //LT
        System.out.println("\nTest LT")
        dut.io.sel.poke(4)
        dut.io.a.poke(minusOne)
        dut.io.b.poke(1)
        dut.clock.step(1)
        System.out.println("-1 < 1: " + dut.io.lt.peekBoolean())
        dut.io.lt.expect(true.B)

        dut.io.a.poke(5)
        dut.io.b.poke(3)
        dut.clock.step(1)
        System.out.println("5 < 3: " + dut.io.lt.peekBoolean())
        dut.io.lt.expect(false.B)

        System.out.println("\nEnd of simulation")
    }
  }
}