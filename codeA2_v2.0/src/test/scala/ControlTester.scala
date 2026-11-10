import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class ControlTester extends AnyFlatSpec with ChiselScalatestTester{

    "ControlTester" should "pass" in {
        test(new ControlUnit())
            .withAnnotations(Seq(WriteVcdAnnotation)) { dut => 

            System.out.println("\nTest START")
            dut.io.controlIn.poke("b0000".U)
            dut.clock.step(1)
            dut.io.RegWrite.expect(false.B)
            dut.io.ALUsrc.expect(false.B)
            dut.io.ALUop.expect(0.U)
            dut.io.memRead.expect(false.B)
            dut.io.memWrite.expect(false.B)
            dut.io.Jump.expect(false.B)
            dut.io.End.expect(false.B)

            System.out.println("\nTest ADD")
            dut.io.controlIn.poke("b0001".U)
            dut.clock.step(1)
            dut.io.RegWrite.expect(true.B)
            dut.io.ALUsrc.expect(false.B)
            dut.io.ALUop.expect(0.U)
            dut.io.memRead.expect(false.B)
            dut.io.memWrite.expect(false.B)
            dut.io.Jump.expect(false.B)
            dut.io.End.expect(false.B)

            System.out.println("\nTest MULT")
            dut.io.controlIn.poke("b0010".U)
            dut.clock.step(1)
            dut.io.RegWrite.expect(true.B)
            dut.io.ALUsrc.expect(false.B)
            dut.io.ALUop.expect(2.U)
            dut.io.memRead.expect(false.B)
            dut.io.memWrite.expect(false.B)
            dut.io.Jump.expect(false.B)
            dut.io.End.expect(false.B)

            System.out.println("Test ADDI")
            dut.io.controlIn.poke("b0011".U)
            dut.clock.step(1)
            dut.io.RegWrite.expect(true.B)
            dut.io.ALUsrc.expect(true.B)
            dut.io.ALUop.expect(0.U)
            dut.io.memRead.expect(false.B)
            dut.io.memWrite.expect(false.B)
            dut.io.Jump.expect(false.B)
            dut.io.End.expect(false.B)

            System.out.println("Test LD")
            dut.io.controlIn.poke("b0100".U)
            dut.clock.step(1)
            dut.io.RegWrite.expect(true.B)
            dut.io.ALUsrc.expect(true.B)
            dut.io.ALUop.expect(0.U)
            dut.io.memRead.expect(true.B)
            dut.io.memWrite.expect(false.B)
            dut.io.Jump.expect(false.B)
            dut.io.End.expect(false.B)

            System.out.println("Test SD")
            dut.io.controlIn.poke("b0101".U)
            dut.clock.step(1)
            dut.io.RegWrite.expect(false.B)
            dut.io.ALUsrc.expect(true.B)
            dut.io.ALUop.expect(0.U)
            dut.io.memRead.expect(false.B)
            dut.io.memWrite.expect(true.B)
            dut.io.Jump.expect(false.B)
            dut.io.End.expect(false.B)

            System.out.println("Test JR")
            dut.io.controlIn.poke("b0110".U)
            dut.clock.step(1)
            dut.io.RegWrite.expect(false.B)
            dut.io.ALUsrc.expect(false.B)
            dut.io.ALUop.expect(5.U)
            dut.io.memRead.expect(false.B)
            dut.io.memWrite.expect(false.B)
            dut.io.Jump.expect(true.B)
            dut.io.End.expect(false.B)

            System.out.println("Test JEQ")
            dut.io.controlIn.poke("b0111".U)
            dut.clock.step(1)
            dut.io.RegWrite.expect(false.B)
            dut.io.ALUsrc.expect(false.B)
            dut.io.ALUop.expect(3.U)
            dut.io.memRead.expect(false.B)
            dut.io.memWrite.expect(false.B)
            dut.io.Jump.expect(true.B)
            dut.io.End.expect(false.B)

            System.out.println("Test JLT")
            dut.io.controlIn.poke("b1000".U)
            dut.clock.step(1)
            dut.io.RegWrite.expect(false.B)
            dut.io.ALUsrc.expect(false.B)
            dut.io.ALUop.expect(4.U)
            dut.io.memRead.expect(false.B)
            dut.io.memWrite.expect(false.B)
            dut.io.Jump.expect(true.B)
            dut.io.End.expect(false.B)

            System.out.println("Test END")
            dut.io.controlIn.poke("b1010".U)
            dut.clock.step(1)
            dut.io.RegWrite.expect(false.B)
            dut.io.ALUsrc.expect(false.B)
            dut.io.ALUop.expect(0.U)
            dut.io.memRead.expect(false.B)
            dut.io.memWrite.expect(false.B)
            dut.io.Jump.expect(false.B)
            dut.io.End.expect(true.B)

            System.out.println("Test SUB")
            dut.io.controlIn.poke("b1011".U)
            dut.clock.step(1)
            dut.io.RegWrite.expect(true.B)
            dut.io.ALUsrc.expect(false.B)
            dut.io.ALUop.expect(1.U)
            dut.io.memRead.expect(false.B)
            dut.io.memWrite.expect(false.B)
            dut.io.Jump.expect(false.B)
            dut.io.End.expect(false.B)

            System.out.println("\nEnd of simulation")






            
            
        
            
        }
    }

}