package com.szn.movies.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.szn.movie.auth.viewmodel.UserViewModel
import com.szn.movies.ui.compose.AppSkeleton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{ AppSkeleton() }
        CoroutineScope(lifecycleScope.coroutineContext).launch {
            userViewModel.newToken()
        }
    }
}