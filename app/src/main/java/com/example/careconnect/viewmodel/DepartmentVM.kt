package com.example.careconnect.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careconnect.models.Doctors
import com.example.careconnect.repository.ApiService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DepartmentVM(private val id :String) : ViewModel() {
    private val _doctors = MutableStateFlow<List<Doctors>>(emptyList())
    val doctors : StateFlow<List<Doctors>> = _doctors
    val gson = Gson()

    private val _loading = mutableStateOf(true)
    val loading = _loading

    init {
        getDoctors(id.toInt())
    }

    private fun getDoctors(id:Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try{
                    val resp = ApiService.api.GetDoctors(id)
                    resp.body()?.let{ result ->
                        if(result.status == true){
                            val doctors : List<Doctors> = gson.fromJson(result.data,object : TypeToken<List<Doctors>>() {}.type)
                            _doctors.value = doctors
                            _loading.value = false
                        }else{
                            throw Exception()
                        }
                    }
                }catch(e : Exception){
                    _doctors.value = emptyList()
                    _loading.value = false
                }
            }
        }
    }
}