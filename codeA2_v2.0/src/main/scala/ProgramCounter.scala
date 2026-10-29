import chisel3._
import chisel3.util._

class ProgramCounter extends Module {
  val io = IO(new Bundle {
    val stop = Input(Bool())
    val jump = Input(Bool())
    val run = Input(Bool())
    val programCounterJump = Input(UInt(16.W))
    val programCounter = Output(UInt(16.W))
  }) 
  val programCounter = RegInit(0.U(16.W))
  val programCounterNext = Wire(UInt(16.W))

  programCounterNext := programCounter
  when (io.run && !io.stop && !io.jump ){
    programCounterNext := programCounter + 1.U;
  } .elsewhen (io.jump){
    programCounterNext := io.programCounterJump
  }.otherwise{
    programCounterNext := programCounter
  }
  programCounter := programCounterNext
  io.programCounter := programCounter

}