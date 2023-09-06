package com.example.comparetext.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import com.example.comparetext.databinding.ActivityMainBinding
import android.content.Context
import com.example.comparetext.R

class MainActivity

    : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.stringComparison.observe(this) {
            if (it.compareResult) {
                binding.resultTextView.text = getString(R.string.result_ok)
                binding.resultTextView.setTextColor(getColor(R.color.result_ok))
            }
            else {
                binding.resultTextView.text = getString(R.string.result_fail)
                binding.resultTextView.setTextColor(getColor(R.color.result_fail))
            }

        }

        mainViewModel.errorMsg.observe(this) {
            if (!it.isNullOrEmpty()) showError(it)
        }

        binding.compareButton.setOnClickListener {
            // Esconder el teclado antes de ejecutar la comparación
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)

            // Obtener valores desde la UI y ejecutar comparación
            val str1: String = binding.Text1.text.toString()
            val str2: String = binding.Text2.text.toString()
            mainViewModel.compareStrings(str1, str2)
        }
    }

    private fun showError(msg: String) {
        // Setear texto y color en caso de error

        binding.resultTextView.text = msg
        binding.resultTextView.setTextColor(getColor(R.color.error))
    }

}