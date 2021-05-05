package com.example.test.ui.main.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.test.api.ResponseData
import com.example.test.repository.MainRepository
import kotlinx.coroutines.Dispatchers


class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getUsers() = liveData(Dispatchers.IO) {


        emit(ResponseData.Loading<Nothing>(null))
        try {
            emit(ResponseData.Success(mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(ResponseData.Error<Nothing>(exception))
        }

    }
}