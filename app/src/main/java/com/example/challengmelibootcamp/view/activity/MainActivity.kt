package com.example.challengmelibootcamp.view.activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.example.challengmelibootcamp.databinding.ActivityMainBinding
import com.example.challengmelibootcamp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel = MainViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityMainBinding.inflate(this.layoutInflater)

        setContentView(this.binding.root)

        setListeners()

        setObservers()
    }


    private fun setListeners(){
        this.binding.editSearch.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_SEARCH || i == EditorInfo.IME_ACTION_DONE || keyEvent == null || keyEvent.keyCode == KeyEvent.KEYCODE_ENTER){
                if(binding.editSearch.text.toString().length == 0){
                    showAlertDialog("Erro", "Voce deve digitar um termo para a pesquisa.")
                }else{
                   searchProduct()
                }
            }
            false
        }
    }

    private fun searchProduct(){
        val productName: String = this.binding.editSearch.text.toString()
        showProgressBar()
        viewModel.searchCategory(productName)
    }

    private fun showProgressBar(){
        this.binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        this.binding.progressBar.visibility = View.GONE
    }

    private fun showAlertDialog(title: String, message: String){
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setNeutralButton("Ok", object : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                }
            })
            .create()
            .show()
    }

    private fun setObservers(){
//        viewModel.categories().observe(this, Observer {
//            hideProgressBar()
//            if(it.size == 0){
//                showAlertDialog("Info", "Nenhum produto encontrado")
//            }else{
//                val msg = it.first().categoryName
//                Toast.makeText(baseContext, msg, Toast.LENGTH_LONG).show()
//            }
//        })

        viewModel.productSearch().observe(this, Observer {
            hideProgressBar()
            if(it.success()){
                Toast.makeText(this, "Sucesso!!!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Erro!!", Toast.LENGTH_SHORT).show()
            }
        })
    }

}