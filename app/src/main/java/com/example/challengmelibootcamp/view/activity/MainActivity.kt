package com.example.challengmelibootcamp.view.activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengmelibootcamp.databinding.ActivityMainBinding
import com.example.challengmelibootcamp.view.adapter.ProductsAdapter
import com.example.challengmelibootcamp.view.listener.implementation.OnClickItemListener
import com.example.challengmelibootcamp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel = MainViewModel(this)
    private var adapter: ProductsAdapter = ProductsAdapter(listOf(), this, OnClickItemListener(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityMainBinding.inflate(this.layoutInflater)

        setContentView(this.binding.root)

        setListeners()

        setObservers()

        hideRecyclerView()

        this.binding.recyclerViewProducts.layoutManager = LinearLayoutManager(this)
        this.binding.recyclerViewProducts.adapter = this.adapter

    }


    private fun setListeners(){
        this.binding.editSearch.setOnEditorActionListener { textView, i, keyEvent ->
            if(binding.editSearch.text.length > 0){
                hideRecyclerView()
                hideErrorMessage()
                hideProgressBar()
            }
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
        viewModel.productSearch().observe(this, Observer {
            hideProgressBar()
            if(it.success()){
                showRecyclerView()
                adapter.updateProducts(viewModel.productsCollection())
            }else{
                setErrorMessage(it.message()!!)
                showErrorMessage()
            }
        })
    }

    private fun showRecyclerView(){
        this.binding.recyclerViewProducts.visibility = View.VISIBLE
    }

    private fun hideRecyclerView(){
        this.binding.recyclerViewProducts.visibility = View.GONE
    }

    private fun setErrorMessage(message: String){
        this.binding.textError.text = message
    }

    private fun showErrorMessage(){
        this.binding.textError.visibility = View.VISIBLE
    }

    private fun hideErrorMessage(){
        this.binding.textError.visibility = View.INVISIBLE
    }
}