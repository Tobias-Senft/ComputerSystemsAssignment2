import chisel3._
import chisel3.util._

class ALU extends Module {
  val io = IO(new Bundle {
    val a = Input(UInt(32.W))
    val b = Input(UInt(32.W))
    val sel = Input(UInt(4.W))
    val result = Output(UInt(32.W))
    val cond = Output(Bool())
    //Define the module interface here (inputs/outputs)
  })


  //Implement this module here
  io.result := 0.U
  io.cond := false.B

  switch(io.sel){
    //vores operationer: ADD, ADDI, SUB osv.
    is(0.U) {io.result:= io.a + io.b} //ADD, ADDI
    is(1.U) {io.result:= io.a - io.b} //SUB
    is(2.U) {io.result := io.a * io.b} //MULT
    is(3.U) {io.cond := io.a === io.b}
    is(4.U) {io.cond := io.a.asSInt < io.b.asSInt}
    is(5.U) {io.cond := true.B}
    is(6.U) {io.result :=  io.a}
  }
  



}