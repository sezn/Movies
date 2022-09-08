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

    init {
        Log.w(TAG, "init")
        viewModelScope.launch {
            val auth = userRepository.newToken()
            Log.w(TAG, "init $auth  ${auth.request_token}")
        }
    }



}