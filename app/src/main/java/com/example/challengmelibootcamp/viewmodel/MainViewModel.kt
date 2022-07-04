package com.example.challengmelibootcamp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengmelibootcamp.data.model.CategoryModel
import com.example.challengmelibootcamp.data.repository.CategoryRepository
import com.example.challengmelibootcamp.utils.CategorySearch
import com.example.challengmelibootcamp.utils.Constants
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException

class MainViewModel: ViewModel() {
    private val categoryRepository: CategoryRepository = CategoryRepository()

    private var categorySearch: CategorySearch = CategorySearch("", false)
    public fun categorySearch() : CategorySearch {
        return this.categorySearch
    }


    private var categories: MutableLiveData<List<CategoryModel>> = MutableLiveData()
    public fun categories() : LiveData<List<CategoryModel>>{
        return this.categories
    }

    public fun searchCategory(categoryName: String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val res: Response<List<CategoryModel>> =
                    categoryRepository.searchCategory(categoryName)
                when (res.code()) {
                    Constants.HTTP.SUCCESS -> {
                        categories.postValue(res.body())
                        categorySearch = CategorySearch(message = "Sucesso", success = true)
                    }
                    else -> {
                        categories.postValue(listOf())
                        categorySearch = CategorySearch(message = "Erro", success = false)
                    }
                }
            }catch (e: ConnectException){
                categorySearch = CategorySearch(message = Constants.MESSAGE.INTERNET_CONNECTION, success = false)
            }catch(e: IOException){
                categorySearch = CategorySearch(message = Constants.MESSAGE.UKNOWN_ERROR, success = false)
            }
        }
    }


}


//viewModelScope.launch(Dispatchers.IO){
//    try {
//        val res: Response<List<TaskModel>> = taskRepository.all()
//
//        when(res.code()){
//            Constants.HTTP.SUCCESS -> {
//                allTasks.postValue(res.body())
//                taskResult = TaskResult("Sucesso", true)
//            }
//            else -> {
//                val message: String = Gson().fromJson(res.errorBody()!!.string(), String::class.java)
//                taskResult = TaskResult(message, false)
//            }
//        }
//    }catch (e: ConnectException){
//        taskResult = TaskResult("Erro ao conectar à internet.", false)
//    }catch (e: IOException){
//        taskResult = TaskResult("Desculpe-nos, falhamos ao buscar suas tarefas.", false)
//    }
//}