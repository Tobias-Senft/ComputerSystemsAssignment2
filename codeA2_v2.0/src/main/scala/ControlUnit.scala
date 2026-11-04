import chisel3._
import chisel3.util._

class ControlUnit extends Module {
  val io = IO(new Bundle {
    val controlIn = Input(UInt(4.W))
    val RegWrite = Output(Bool())
    val ALUsrc = Output(Bool())
    val ALUop = Output(UInt(4.W))
    val memRead = Output(Bool())
    val memWrite = Output(Bool())
    val Jump = Output(Bool())
    val End = Output(Bool())
    

  })

  val opcode = io.controlIn

  io.RegWrite := false.B
  io.ALUsrc := false.B
  io.ALUop := 0.U
  io.memRead := false.B
  io.memWrite := false.B
  io.Jump := false.B
  io.End := false.B


  switch(opcode){
    is("b0000".U) { } //START: ingenting

    is("b0001".U) { //ADD
      io.RegWrite := true.B
      io.ALUop := 0.U
    }
    is("b0010".U) { //MULT
      io.RegWrite := true.B
      io.ALUop := 2.U
    }
    is("b0011".U) { //ADDI
      io.RegWrite := true.B
      io.ALUsrc := true.B
      io.ALUop := 0.U
    }
    is("b0100".U) { //LD: adresse = R2 + immediate via ALU
      io.RegWrite := true.B
      io.ALUsrc := true.B
      io.ALUop := 0.U
      io.memRead := true.B
    }
    is("b0101".U) { //SD: adresse = R2 + immediate via ALU
      io.ALUsrc := true.B
      io.ALUop := 0.U
      io.memWrite := true.B
    }
    is("b0110".U) { //JR
      io.Jump := true.B
      io.ALUop := 6.U
    }
    is("b0111".U) { //JEQ
      io.Jump := true.B
      io.ALUop := 3.U
    }
    is("b1000".U) { //JLT
      io.Jump := true.B
      io.ALUop := 4.U
    }
    is("b1010".U) { //END
      io.End := true.B
    }
    is("b1011".U) { //SUB
      io.RegWrite := true.B
      io.ALUop := 1.U
    }
  

  }


  //Implement this module here

}