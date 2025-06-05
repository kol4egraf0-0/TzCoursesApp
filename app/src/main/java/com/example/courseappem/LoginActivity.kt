package com.example.courseappem

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.courseappem.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textCheck()
        setupTextWatchers()
    }

    private fun textCheck(){
        val emailInput = binding.etEmail

        val filter = InputFilter { source, start, end, dest, dstart, dend ->
            for(i in start until end){
                val c = source[i]
                if (c < ' ' || c > '~') {
                    return@InputFilter ""
                }
            }
            null
        }

        emailInput.filters = arrayOf(filter)
    }

    private fun setupTextWatchers() {
        val watcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                checkLoginConditions(buttonBlock = false)
            }

        }

        binding.etEmail.addTextChangedListener(watcher)
        binding.etPassword.addTextChangedListener(watcher)

        binding.btnVhod.setOnClickListener {
            checkLoginConditions(buttonBlock = true)
        }
    }

    private fun checkLoginConditions(buttonBlock: Boolean = false) {
        val isEmailAndPassValid = binding.etEmail.text.toString().isValidEmail() && binding.etPassword.text.toString().isNotEmpty()

        binding.btnVhod.isEnabled = isEmailAndPassValid

        if (buttonBlock && isEmailAndPassValid) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches() //email check
}