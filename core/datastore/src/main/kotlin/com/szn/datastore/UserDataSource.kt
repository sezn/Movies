package com.szn.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import com.szn.common.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserDataSource @Inject constructor(val userData: DataStore<User>) {

    val TAG = "UserDataSource"

    val userStream = userData.data.map {
        User.newBuilder()
            .setFirstName(it.firstName)
            .setLastName(it.lastName)
            .setPicture(it.picture)
            .setAddress(it.address)
            .setBirthDay(it.birthDay)
            .build()
    }

    init {
        Log.w(TAG, "init")
        CoroutineScope(Dispatchers.Default).launch {
            userStream.collect {
                if(it == null || it.birthDay == 0L)
                    createUser()
            }
        }
    }

    private suspend fun createUser() {
        Log.w(TAG, "createUser")
        userData.updateData {
            User.newBuilder()
                .setFirstName("SZN")
                .setLastName("Juju")
                .setPicture("https://cv.sezn.fr/static/media/ju.71ce4df19b6f1399e40b.png")
                .setBirthDay(401032800000)
                .build()
        }

    }


}