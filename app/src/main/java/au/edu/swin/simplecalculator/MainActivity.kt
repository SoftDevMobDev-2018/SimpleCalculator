package au.edu.swin.simplecalculator

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

  private val stack by lazy { findViewById<TextView>(R.id.stack) }
  private val display by lazy { findViewById<TextView>(R.id.display) }

  private val numBtns by lazy {
    listOf<Button>(findViewById(R.id.num0),
                   findViewById(R.id.num1),
                   findViewById(R.id.num2),
                   findViewById(R.id.num3),
                   findViewById(R.id.num4),
                   findViewById(R.id.num5),
                   findViewById(R.id.num6),
                   findViewById(R.id.num7),
                   findViewById(R.id.num8),
                   findViewById(R.id.num9),
                   findViewById(R.id.numDecimal))
  }

  private val opBtns by lazy {
    listOf<Button>(findViewById(R.id.opAdd),
                   findViewById(R.id.opSubstract),
                   findViewById(R.id.opMultiply),
                   findViewById(R.id.opDivide),
                   findViewById(R.id.opAdd))
  }

  private val sumButton by lazy { findViewById<Button>(R.id.opEqual) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  @SuppressLint("SetTextI18n", "LogNotTimber")
  override fun onStart() {
    super.onStart()
    numBtns.forEach { numBtn ->
      numBtn.setOnClickListener {
        display.text = "${display.text}${numBtn.text}"
        if(display.text.length != 1 && display.text[0] == '0')
          display.text = display.text.removeRange(0,1)
      }
    }
    opBtns.forEach { opBtn ->
      opBtn.setOnClickListener {
        if (display.text.isNotBlank()) {
          SimpleCalculator.inputs.add(display.text.toString().toFloat())
          SimpleCalculator.operations.add(
              Operation.values().find { it.stringValue == opBtn.text })
          stack.text = SimpleCalculator.stack()
          display.text = ""
        }
        Log.d("zip", (SimpleCalculator.operations zip  SimpleCalculator.inputs).toString())
      }
    }

    sumButton.setOnClickListener{
      if(display.text.isNotBlank()) {
        SimpleCalculator.inputs.add(display.text.toString().toFloat())
        display.text = SimpleCalculator.computeResult().toString()
        SimpleCalculator.inputs.clear()
        SimpleCalculator.operations.clear()
        stack.text = ""
      }
    }
  }
}
