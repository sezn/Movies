package com.szn.network

sealed class State {
    object START : State()
    object LOADING : State()
    object SUCCESS : State()
    data class FAILURE(val message: String) : State()
}