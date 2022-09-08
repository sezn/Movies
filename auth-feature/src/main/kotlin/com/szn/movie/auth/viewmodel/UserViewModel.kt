package com.szn.movie.auth.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.szn.core.repos.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepo): ViewModel() {

    val TAG = UserViewModel::class.java.simpleName
    val isLogged = mutableStateOf(false)
    val errorMessage = mutableStateOf("")

    init {
        Log.w(TAG, "init")
        viewModelScope.launch {
            val auth = userRepository.newToken()
            Log.w(TAG, "init $auth  ${auth.request_token}")
        }
    }


    suspend fun login(login: String, pass: String, pseudo: String) {
        userRepository.login(pseudo, pass).collect { auth ->
            Log.w(TAG, "Login:: $auth")
            if(!auth.success){
                val er = auth.error
                errorMessage.value = er?.status_message.toString()
            } else {
                isLogged.value = true
            }
        }
    }

    fun favorite(id: Int) {
        Log.w(TAG, "favorite $id sessId: ${userRepository.sessionId}  token: ${userRepository.token}")
    }


}