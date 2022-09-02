package com.szn.core

sealed class State {
    object START : State()
    object LOADING : State()
    object SUCCESS : State()
    data class FAILURE(val message: String) : State()
}

val stateListLoading by lazy { mutableListOf(State.LOADING, State.SUCCESS) }
