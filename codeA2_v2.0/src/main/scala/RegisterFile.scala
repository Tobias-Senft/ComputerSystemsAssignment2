import chisel3._
import chisel3.util._

class RegisterFile extends Module {
  val io = IO(new Bundle {
    val R1 = Input(UInt(5.W))
    val R2 = Input(UInt(5.W))
    val R3 = Input(UInt(5.W))

    val Data = Input(UInt(32.W))
    val RegWrite = Input(Bool())

    val outputA = Output(UInt(32.W))
    val outputB = Output(UInt(32.W))

    //Define the module interface here (inputs/outputs)
  })

  val registers = RegInit(VecInit(Seq.fill(32)(0.U(32.W))))

  io.outputA := registers(io.R2)
  io.outputB := registers(io.R3)

  when(io.RegWrite){
    registers(io.R1) := io.Data
  }

}