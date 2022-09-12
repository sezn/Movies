package com.szn.movie.auth.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.szn.core.network.ApiStatus
import com.szn.core.network.model.ErrorResponse
import com.szn.core.repos.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepo): ViewModel() {

    val TAG = UserViewModel::class.java.simpleName
    val isLogged = mutableStateOf(false)
    val showError = mutableStateOf(false)
    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf("")

    init {
        Log.w(TAG, "init")
        viewModelScope.launch {
            val auth = userRepository.newToken()
            Log.w(TAG, "init $auth  ${auth.request_token}")
        }
    }

    suspend fun newToken(){
        val auth = userRepository.newToken()
        Log.w(TAG, "init $auth  ${auth.request_token}")
    }

    suspend fun login(/*login: String, */pass: String, pseudo: String) {
        errorMessage.value = ""
        userRepository.login(pseudo, pass)
            .collect { result ->
                when(result.status) {
                    ApiStatus.SUCCESS -> {
                        isLoading.value = false
                        isLogged.value = true
                        val auth = result.data
                        Log.w(TAG, "Login sucesss $auth")
                    }
                    ApiStatus.ERROR -> {
                        isLoading.value = false
                        showError.value = true
                        val error = result.data as ErrorResponse
                        Log.e(TAG, "Login Error ${error.status_code} ${error.status_message}")
                        errorMessage.value = error.status_message
                    }
                    ApiStatus.LOADING -> {
                        isLoading.value = true
                        Log.w(TAG, "Loading...")
                    }
                }

        }
    }

    suspend fun favorite(id: Int) {
        Log.w(TAG, "favorite $id sessId: ${userRepository.sessionId}  token: ${userRepository.token}  ${userRepository.accountId}")
        userRepository.favorite(userRepository.accountId.toString(), userRepository.sessionId, id).collect{ result ->
            Log.w(TAG, "favorite.. $result")
            when(result.status) {
                ApiStatus.SUCCESS -> {
                    Log.w(TAG, "favorite sucesss $result")
                }
                ApiStatus.ERROR -> {
                    showError.value = true
                    val error = result.data as ErrorResponse
                    Log.e(TAG, "favorite Error ${error.status_code} ${error.status_message}")
                    errorMessage.value = error.status_message
                }
                else -> {}
            }
    }
    }


}