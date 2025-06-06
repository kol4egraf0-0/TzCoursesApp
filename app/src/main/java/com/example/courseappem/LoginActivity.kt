package com.example.courseappem

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import com.example.courseappem.databinding.ActivityLoginBinding
import com.example.presentation.login.LoginViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.presentation.login.LoginState
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textCheck()
        setupTextWatchers()

        binding.btnVK.setOnClickListener {
            openLink("https://vk.com/")
        }

        binding.btnOK.setOnClickListener {
            openLink("https://ok.ru/")
        }


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginState.collect { state ->
                    when (state) {
                        is LoginState.Idle -> {
                        }
                        is LoginState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is LoginState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        }
                        is LoginState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            //System.out.println("error")
                        }
                    }
                }
            }
        }
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
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            loginViewModel.login(email, password)
        }
    }

    private fun checkLoginConditions(buttonBlock: Boolean = false) {
        val isEmailAndPassValid = binding.etEmail.text.toString().isValidEmail() && binding.etPassword.text.toString().isNotEmpty()

        binding.btnVhod.isEnabled = isEmailAndPassValid
    }

    private fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches() //email check

    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(intent)
    }
}