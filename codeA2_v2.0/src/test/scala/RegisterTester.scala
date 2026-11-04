import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec


class RegisterTester extends AnyFlatSpec with ChiselScalatestTester{

    "RegisterTester" should "pass" in {
        test(new RegisterFile)
            .withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
            
            System.out.println("\nTest of 0 initializing")
            dut.io.R2.poke(5)
            dut.io.R3.poke(31)
            System.out.println("R5 = " + dut.io.outputA.peekInt() + ", R31 = " + dut.io.outputB.peekInt())
            dut.io.outputA.expect(0)
            dut.io.outputB.expect(0)

            System.out.println("\nTest of writing to register")
            dut.io.RegWrite.poke(true.B)
            dut.io.R1.poke(5)
            dut.io.Data.poke(123)
            dut.io.outputA.expect(0)
            dut.clock.step(1)
            dut.io.RegWrite.poke(false.B)
            System.out.println("R5 = " + dut.io.outputA.peekInt())
            dut.io.outputA.expect(123)

            System.out.println("\nTest of RegWrite = false")
            dut.io.RegWrite.poke(false.B)
            dut.io.R1.poke(5)
            dut.io.Data.poke(123)
            dut.clock.step(1)
            System.out.println("R5 = " + dut.io.outputA.peekInt())
            dut.io.outputA.expect(123)






            
            
            
            }
    }
}