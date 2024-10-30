package com.example.volumebalok

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edtwidth : EditText
    private lateinit var edtheight : EditText
    private lateinit var edtlength : EditText
    private lateinit var btncalculate : Button
    private lateinit var tvResult : TextView;

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        edtwidth = findViewById(R.id.edt_width)
        edtheight = findViewById(R.id.edt_height)
        edtlength = findViewById(R.id.edt_length)
        btncalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btncalculate.setOnClickListener(this)

        if  (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT,tvResult.text.toString())
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_calculate) {

            val inputLength = edtlength.text.toString().trim()
            val inputWidth = edtwidth.text.toString().trim()
            val inputHeight = edtheight.text.toString().trim()

            var isEmpty =  false

            if (inputLength.isEmpty()) {
                isEmpty = true
                edtlength.error = "Field ini tidak boleh kosong"
            }

            if (inputWidth.isEmpty()) {
                isEmpty = true
                edtwidth.error = "Field ini tidak boleh kosong"
            }

            if (inputHeight.isEmpty()) {
                isEmpty = true
                edtheight.error = "Field ini tidak boleh kosong"
            }

            if (!isEmpty) {
                val volume = inputHeight.toDouble() * inputLength.toDouble() * inputWidth.toDouble()
                tvResult.text = volume.toString();
            }

        }
    }
}