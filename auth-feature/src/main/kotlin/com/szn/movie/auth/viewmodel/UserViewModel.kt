package com.szn.movie.auth.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.szn.core.network.ApiStatus
import com.szn.core.network.model.ErrorResponse
import com.szn.core.repos.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepo): ViewModel() {

    val TAG = UserViewModel::class.java.simpleName
    val isLogged = userRepository.isLogged
    val showError = mutableStateOf(false)
    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf("")

    init {
        Log.w(TAG, "init ${isLogged.value}")
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
                        val error = result.data as ErrorResponse
                        Log.e(TAG, "Login Error ${error.status_code} ${error.status_message}")
                        errorMessage.value = error.status_message
                        showError.value = true
                    }
                    ApiStatus.LOADING -> {
                        isLoading.value = true
                        Log.w(TAG, "Loading...")
                    }
                }

        }
    }

    suspend fun favorite(fav: Boolean, id: Int) = flow {
        Log.w(TAG, "favorite $id sessId: ${userRepository.sessionId}  token: ${userRepository.token}  ${userRepository.accountId}")
        userRepository.favorite(fav, userRepository.accountId.toString(), id).collect{ result ->
            Log.w(TAG, "favorite.. $result")
            when(result.status) {
                ApiStatus.SUCCESS -> {
                    Log.w(TAG, "favorite sucesss $result")
                    emit(true)
                }
                ApiStatus.ERROR -> {
                    showError.value = true
                    val error = result.data as ErrorResponse
                    Log.e(TAG, "favorite Error ${error.status_code} ${error.status_message}")
                    errorMessage.value = error.status_message
                    emit(false)
                }
                else -> {}
            }
        }
    }

    suspend fun logout() = userRepository.logout()
    suspend fun getUser() = flow {
//        userRepository.getAccount()
        emit(null)
    }

}