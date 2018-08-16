package au.edu.swin.simplecalculator

import au.edu.swin.simplecalculator.Operation.*
import java.util.Stack

enum class Operation(val stringValue: String) {
  ADDITION("+"),
  SUBTRACTION("-"),
  DIVISION("/"),
  MULTIPLICATION("*")
}

object SimpleCalculator {

  val inputs = Stack<Float>()
  val operations = Stack<Operation?>()

  fun computeResult(): Float = inputs.reduce {
    sum, element -> performOperation(sum, element)
  }

  fun stack()  =
      inputs.mapIndexed { i, input ->
        "$input ${operations[i]?.stringValue}"
      }.joinToString(" ")

  private fun performOperation(lhs:Float, rhs:Float): Float =
      when(operations.peek()){
        ADDITION -> lhs + rhs
        SUBTRACTION -> lhs - rhs
        DIVISION -> lhs / rhs
        MULTIPLICATION -> lhs * rhs
        else -> lhs
      }
}
