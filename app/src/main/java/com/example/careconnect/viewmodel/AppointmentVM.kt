package com.example.careconnect.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careconnect.models.Departments
import com.example.careconnect.repository.ApiService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppointmentVM : ViewModel() {
    private val _department = MutableStateFlow<List<Departments>>(emptyList())
    val department : StateFlow<List<Departments>> = _department

    private val _loading = mutableStateOf(true)
    val loading = _loading
    val gson = Gson()
    init{
        getDepartment()
    }

    private fun getDepartment(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try{
                    val resp = ApiService.api.GetDepartment();
                    resp.body()?.let { result ->
                        if(result.status == true){
                            val departmentList: List<Departments> = gson.fromJson(result.data, object : TypeToken<List<Departments>>() {}.type)
                            _department.value = departmentList
                            _loading.value = false
                        }else{
                            throw Exception()
                        }
                    }
                }catch(e : Exception){
                    _department.value = emptyList()
                    _loading.value = false
                }
            }
        }
    }
}