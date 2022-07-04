package com.example.challengmelibootcamp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import com.example.challengmelibootcamp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityMainBinding.inflate(this.layoutInflater)

        setContentView(this.binding.root)

        setListeners()
    }


    private fun setListeners(){
        this.binding.editSearch.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_SEARCH || i == EditorInfo.IME_ACTION_DONE || keyEvent == null || keyEvent.keyCode == KeyEvent.KEYCODE_ENTER){
                showProgressBar()
            }
            false
        }
    }

    private fun showProgressBar(){
        this.binding.progressBar.visibility = View.VISIBLE
    }

}