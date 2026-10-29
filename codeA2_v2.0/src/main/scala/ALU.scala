import chisel3._
import chisel3.util._

class ALU extends Module {
  val io = IO(new Bundle {
    val a = Input(UInt(32.W))
    val b = Input(UInt(32.W))
    val sel = Input(UInt(4.W))
    val result = Output(UInt(32.W))
    val eq = Output(Bool())
    val lt = Output(Bool())
    //Define the module interface here (inputs/outputs)
  })


  //Implement this module here
  io.result := 0.U

  switch(io.opcode){
    //vores operationer: ADD, ADDI, SUB osv.
    is(0.U) {io.result:= io.a + io.b} //ADD
    is(1.U) {io.result:= io.a - io.b} //SUB
    is(2.U) {io.result := io.a * io.b} //MULT
  }
  io.eq := io.a === io.b
  io.lt := io.a.asSInt < io.b.asSInt
  



}