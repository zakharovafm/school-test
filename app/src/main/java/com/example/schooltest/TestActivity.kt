package com.example.schooltest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_test.*
import kotlin.random.Random

class TestActivity : AppCompatActivity() {
    private var operator = Operator()
    private var helpButtons: Array<Button> = emptyArray()
    private var resultTextFields: Array<TextView> = emptyArray()
    var result = 0.0
    var lastHelpOperationResult: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        helpButtons = arrayOf(btnhelp1, btnhelp2, btnhelp3, btnhelp4, btnhelp6)
        resultTextFields = arrayOf(resultField1, resultField2, resulField3, resultField4, resultField5)
        generate()
    }

    private fun generate(){
        for ((i, v) in arrayOf(tv1, tv2, tv3, tv4, tv6).iterator().withIndex()){
            if(operator.operation[i] == "/") {
                operator.a[i] = operator.b[i]?.times(Random.nextInt(-10,10))
            }
            v.text = getString(R.string.answer_value, operator.a[i], operator.operation[i], operator.b[i])
        }
    }

    fun onHelpButton(pressedButton:View){
        val intentHelp = Intent(this, HelpActivity::class.java)
        val btnIndex = helpButtons.indexOf(pressedButton)
        startActivityForResult(intentHelp, btnIndex)
        pressedButton.visibility = View.INVISIBLE

        lastHelpOperationResult = operator.result(operator.a[btnIndex], operator.b[btnIndex], operator.operation[btnIndex])
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            operator.points[requestCode] = 0.5
            val textField = resultTextFields[requestCode]
            textField.text = ""
            textField.hint = generateHintString(lastHelpOperationResult)
        }
    }

    private fun generateHintString(number:Int):String {
        val hint:String = number.toString()
        return hint.replaceRange(0, hint.length - 1, "*")
    }

    fun onResult(view:View){
        result = 0.0
        for((i, v) in resultTextFields.iterator().withIndex()){
            if(v.text.toString() == operator.result(operator.a[i],operator.b[i],operator.operation[i]).toString())
                result += operator.points[i]
        }

        val intent = Intent(this, FinalActivity::class.java)
        intent.putExtra("result", result)
        startActivity(intent)
        finish()
    }
}