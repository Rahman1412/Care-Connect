package com.example.careconnect.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.careconnect.models.ApiState
import com.example.careconnect.models.AuthUser
import com.example.careconnect.models.Login
import com.example.careconnect.models.LoginData
import com.example.careconnect.models.LoginFieldState
import com.example.careconnect.repository.ApiService
import com.example.careconnect.repository.StorageManager
import com.example.careconnect.repository.StorageManager.Companion.userDataStore
import com.example.careconnect.repository.User
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthVM(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext
    val storage = StorageManager(context)

    private val _login = mutableStateOf(LoginData())
    val login = _login

    private val _state = MutableStateFlow<ApiState?>(null)
    val state: StateFlow<ApiState?> = _state

    private val _authUser = MutableStateFlow<AuthUser?>(null)
    val authUser : StateFlow<AuthUser?> = _authUser

    private val _error = MutableStateFlow<String?>(null)
    val error : StateFlow<String?> = _error

    val gson = Gson()



    fun setData(field:String,value:String){
        _login.value = when(field){
            "email" -> _login.value.copy(email = _login.value.email.copy(value = value.trim()))
            "password" -> _login.value.copy(password = _login.value.password.copy(value = value.trim()))
            else -> _login.value
        }
        validateField(field)
    }

    fun setTouchedGesture(field:String,value:Boolean){
        Log.d("Gesture","Touched");
        _login.value = when(field){
            "email" -> _login.value.copy(email = _login.value.email.copy(touched = value)
            )
            "password" -> _login.value.copy(password = _login.value.password.copy(touched = value)
            )
            else -> _login.value
        }
        validateField(field)
    }

    fun setUnTouchedGesture(field:String,value:Boolean){
        Log.d("Gesture","Untouched");
        _login.value = when(field){
            "email" -> _login.value.copy(email = _login.value.email.copy(untouched = value)
            )
            "password" -> _login.value.copy(password = _login.value.password.copy(untouched = value)
            )
            else -> _login.value
        }
        validateField(field)
    }

    private fun validateField(field:String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                _login.value = when(field){
                    "email" -> _login.value.copy(email =
                        _login.value.email.copy(
                            error = if(_login.value.email.value == ""){
                                "Email is required"
                            }else if (!Patterns.EMAIL_ADDRESS.matcher(_login.value.email.value).matches()){
                                "Please enter valid email address"
                            } else {
                                ""
                            }
                        )
                    )
                    "password" -> _login.value.copy(password =
                        _login.value.password.copy(
                            error = if(_login.value.password.value == ""){
                                "Password is required"
                            }else{
                                ""
                            }
                        )
                    )
                    else -> _login.value
                }
            }
        }
    }

    fun doLogin(email:LoginFieldState,password: LoginFieldState){
        _state.value = ApiState.Loading
        val data = Login(email = email.value,password=password.value)
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try{
                    val resp = ApiService.api.Login(data)
                    resp.body()?.let{ result ->
                        if(result.status == true){
                            _authUser.value = gson.fromJson(result.data,AuthUser::class.java)
                            val save = async { authUser.value?.let { saveDataToStorage(it) } }.await()
                            _state.value = ApiState.Success

                        }else{
                            throw Exception(result.message)
                        }
                    }
                }catch(e:Exception){
                    _state.value = ApiState.Error
                    _error.value = e.message.toString()
                }
            }
        }
    }


    fun clear(){
        _error.value = null
        _state.value = null
    }

    private fun saveDataToStorage(authuser:AuthUser){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                storage.setData(authuser)
            }
        }
    }
}