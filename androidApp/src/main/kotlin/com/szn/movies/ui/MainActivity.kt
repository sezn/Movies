package com.szn.movies.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.szn.movies.ui.compose.AppSkeleton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    val TAG = "Main"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{ AppSkeleton() }
        Log.w(TAG, "onCreate")
    }


    override fun onResume() {
        super.onResume()
        Log.w(TAG, "onResume")
    }
}