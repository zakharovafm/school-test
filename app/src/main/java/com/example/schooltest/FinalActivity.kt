package com.example.schooltest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_final.*

class FinalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        val result = intent.getDoubleExtra("result", 0.0)
        resultText.text = getString(R.string.result, result.toString())
    }

    fun onReplayButton(view: View) {
        val intent = Intent(this, TestActivity::class.java)
        startActivity(intent)
        finish()
    }
}