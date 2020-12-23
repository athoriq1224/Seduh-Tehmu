package com.google.developers.teacup.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.developers.teacup.data.DataRepository

class TeaViewModel(repository: DataRepository) : ViewModel() {

    val originCountries: LiveData<List<String>> = repository.getTeaOriginCountries()

    val teaTypes: LiveData<List<String>> = repository.getTeaTypes()
}
