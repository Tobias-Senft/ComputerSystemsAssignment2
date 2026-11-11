import chisel3._
import chisel3.util._

class CPUTop extends Module {
  val io = IO(new Bundle {
    val done = Output(Bool ())
    val run = Input(Bool ())
    //This signals are used by the tester for loading and dumping the memory content, do not touch
    val testerDataMemEnable = Input(Bool ())
    val testerDataMemAddress = Input(UInt (16.W))
    val testerDataMemDataRead = Output(UInt (32.W))
    val testerDataMemWriteEnable = Input(Bool ())
    val testerDataMemDataWrite = Input(UInt (32.W))
    //This signals are used by the tester for loading and dumping the memory content, do not touch
    val testerProgMemEnable = Input(Bool ())
    val testerProgMemAddress = Input(UInt (16.W))
    val testerProgMemDataRead = Output(UInt (32.W))
    val testerProgMemWriteEnable = Input(Bool ())
    val testerProgMemDataWrite = Input(UInt (32.W))
  })

  //Creating components
  val programCounter = Module(new ProgramCounter())
  val dataMemory = Module(new DataMemory())
  val programMemory = Module(new ProgramMemory())
  val registerFile = Module(new RegisterFile())
  val controlUnit = Module(new ControlUnit())
  val alu = Module(new ALU())

  //Connecting the modules
  programCounter.io.run := io.run
  programMemory.io.address := programCounter.io.programCounter
  val instruction = programMemory.io.instructionRead
  controlUnit.io.controlIn := instruction(31,28)

  registerFile.io.R1 := instruction(27,23)
  registerFile.io.R2 := instruction(22,18)
  registerFile.io.R3 := instruction(17,13)
  registerFile.io.RegWrite := controlUnit.io.RegWrite

  val imm = Cat(Fill(14, instruction(17)), instruction(17, 0))
  val aluB = Mux(controlUnit.io.ALUsrc, imm, registerFile.io.outputB)

  alu.io.a :=  registerFile.io.outputA
  alu.io.b := aluB
  alu.io.sel := controlUnit.io.ALUop

  dataMemory.io.address := alu.io.result(15, 0)
  dataMemory.io.dataWrite := registerFile.io.outputB
  dataMemory.io.writeEnable := controlUnit.io.memWrite

  registerFile.io.Data := Mux(controlUnit.io.memRead, dataMemory.io.dataRead, alu.io.result)

  val jumpOffset = Cat(Fill(3, instruction(12)), instruction(12, 0))
  programCounter.io.jump := controlUnit.io.Jump && alu.io.cond
  programCounter.io.programCounterJump := programCounter.io.programCounter + jumpOffset
  programCounter.io.stop := controlUnit.io.End

  io.done := controlUnit.io.End



  



  ////////////////////////////////////////////
  //Continue here with your connections
  ////////////////////////////////////////////

  //This signals are used by the tester for loading the program to the program memory, do not touch
  programMemory.io.testerAddress := io.testerProgMemAddress
  io.testerProgMemDataRead := programMemory.io.testerDataRead
  programMemory.io.testerDataWrite := io.testerProgMemDataWrite
  programMemory.io.testerEnable := io.testerProgMemEnable
  programMemory.io.testerWriteEnable := io.testerProgMemWriteEnable
  //This signals are used by the tester for loading and dumping the data memory content, do not touch
  dataMemory.io.testerAddress := io.testerDataMemAddress
  io.testerDataMemDataRead := dataMemory.io.testerDataRead
  dataMemory.io.testerDataWrite := io.testerDataMemDataWrite
  dataMemory.io.testerEnable := io.testerDataMemEnable
  dataMemory.io.testerWriteEnable := io.testerDataMemWriteEnable
}