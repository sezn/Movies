package com.szn.core.repos

import android.util.Log
import com.google.gson.Gson
import com.szn.core.datastore.DataStoreManager
import com.szn.core.db.AppDatabase
import com.szn.core.extensions.toRequestBody
import com.szn.core.network.API
import com.szn.core.network.model.session.AuthResult
import com.szn.core.network.model.session.UserSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepo @Inject constructor(private val api: API,
                                  private val database: AppDatabase,
                                  private val datastore: DataStoreManager) {

    val TAG = UserRepo::class.java.simpleName
//    private var sessionId =

    init {
        CoroutineScope(Dispatchers.Main).launch {
            val sess = datastore.getSessionId()
            Log.w(TAG, "init $sess")
        }
    }

    suspend fun newToken(): AuthResult {
        val auth = api.authenticate()
        if(auth != null && auth.success && auth.request_token?.isNotEmpty() == true) {
            datastore.setToken(auth.request_token)
            login(auth.request_token)
        } else {
            Log.e(TAG, "Error while authenticate")
        }
        return auth
    }

    suspend fun login(token: String): AuthResult? {
        val session = UserSession("test", "test123", token)
        val js = Gson().toJson(session)
        val json = js.toRequestBody()
        return try {
            var sess = api.login(json)
            Log.w(TAG, "createSessionId $sess")
            createSession(sess)
            sess
        } catch (e: Exception){
            Log.e(TAG,"Exception while login $e")
            null
        }
    }

    suspend fun createSession(sess: AuthResult): AuthResult? {
        val js = Gson().toJson(sess)
        val json = js.toRequestBody()
        return try {
            var sess = api.getSessionId(json)
            Log.w(TAG, "createSessionId $sess")
            sess.session_id?.let {
                datastore.setSessionId(it)
                getAccount(it)
            }
            sess
        } catch (e: Exception){
            Log.e(TAG,"Exception while create SessionId $e")
            null
        }
    }

    suspend fun login(mail: String, pass: String): AuthResult? {
        val session = UserSession(mail, pass, mail)
        val js = Gson().toJson(session)
        val json = js.toRequestBody()
        return try {
            var sess = api.login(json)
            Log.w(TAG, "createSessionId $sess")
            createSession(sess)
            sess
        } catch (e: Exception){
            Log.e(TAG,"Exception while login $e")
            null
        }
    }

    suspend fun getAccount(sessId: String){
        val account = api.getAccount(sessId)
        Log.w(TAG, "getAccount $account")

    }
}