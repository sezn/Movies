package com.szn.core.repos

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.dataStore
import com.google.gson.Gson

import com.szn.core.db.AppDatabase
import com.szn.core.extensions.toRequestBody
import com.szn.core.network.API
import com.szn.core.network.ApiResult
import com.szn.core.network.model.ErrorResponse
import com.szn.core.network.model.MEDIA_TYPE
import com.szn.core.network.model.session.AuthResult
import com.szn.core.network.model.session.UserSession
import com.szn.core.network.model.user.Account
import com.szn.core.network.model.user.AccountSerializer
import com.szn.core.network.model.user.FavRequestBody
import com.szn.datastore.DataStoreManager
import com.szn.datastore.DataStoreManager.Companion.ACCOUNT_ID
import com.szn.datastore.DataStoreManager.Companion.ACCOUNT
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepo @Inject constructor(private val api: API,
                                  private val database: AppDatabase,
                                  private val datastore: DataStoreManager
) {

    val TAG = UserRepo::class.java.simpleName
    var sessionId: String? = null
    var token: String? = null
    var accountId = 0
    var isLogged = mutableStateOf(false)

    init {
        Log.w(TAG, "init")
        CoroutineScope(Dispatchers.Main).launch {
           checkDataStore()
        }
    }

    private suspend fun checkDataStore() {
        datastore.getSessionId().let {
            if (it != null)
                sessionId = it
        }
        datastore.getToken().let {
            if (it != null)
                token = it
            else
                newToken()
        }
        datastore.getValue(ACCOUNT_ID).let {
            if(it != null)
                accountId = Integer.parseInt(it.toString())
        }
        Log.w(TAG, "checkDataStore token: $token sess: $sessionId acc: $accountId")
        if(token?.isNotEmpty() == true && accountId > 0){
            isLogged.value = true

            checkAccount()
        }
    }

    private suspend fun checkAccount() {
        val account = datastore.getValue(ACCOUNT)
        if(account != null && account is Account){
            Log.w(TAG, "Account: $account")
        } else
            Log.w(TAG, "Account?: ${account.toString()} ${account?.javaClass?.simpleName}")
    }

    suspend fun getUser() = flow {
        emit(datastore.getValue(ACCOUNT))
    }

    private suspend fun newToken(): AuthResult {
        val auth = api.authenticate()
        if(auth != null && auth.success && auth.request_token?.isNotEmpty() == true) {
            token = auth.request_token
            datastore.setToken(auth.request_token)
        } else {
            Log.e(TAG, "Error while authenticate")
        }
        return auth
    }

    suspend fun createSession(sess: AuthResult): AuthResult? {
        val js = Gson().toJson(sess)
        val json = js.toRequestBody()
        return try {
            var sess = api.getSessionId(json)
            Log.w(TAG, "createSessionId $sess")
            sess.session_id?.let {
                datastore.setSessionId(it)
                sessionId = it
                isLogged.value = true
                getAccount(it)
            }
            sess
        } catch (e: Exception){
            Log.e(TAG,"Exception while create SessionId $e")
            null
        }
    }

    suspend fun login(mail: String, pass: String) = flow {
        emit(ApiResult.Loading(true))
        if(token.isNullOrEmpty()){
            emit(ApiResult.Error(ErrorResponse(-1, "No token provided", false)))
        }
        val json = Gson().toJson(UserSession(mail, pass, token!!)).toRequestBody()

        val logResponse = api.login(json)
        if(logResponse.isSuccessful){
            logResponse.body()?.let { createSession(it) }
            emit(ApiResult.Success(logResponse))
        } else{
            emit(ApiResult.Error(fromJson(logResponse.errorBody()?.string())))
        }

    }

    suspend fun getAccount(sessId: String){
        val account = api.getAccount(sessId)
        Log.w(TAG, "getAccount $account")
        accountId = account.id
        datastore.add(ACCOUNT_ID, account.id)
        datastore.add(ACCOUNT, account)

        datastore.context.userDataStore.updateData {
            account
        }
    }

    suspend fun favorite(fav: Boolean, accountId: String, movieId: Int) = flow {
        val json = Gson().toJson(FavRequestBody(fav, movieId, MEDIA_TYPE.movie.name)).toRequestBody()
        val mvs = api.favorite(accountId, sessionId!!, json)
        if(mvs.isSuccessful)
            emit(ApiResult.Success(mvs))
        else
            emit(ApiResult.Error(fromJson(mvs.errorBody()?.string())))
    }

    suspend fun logout() = flow {
        if(sessionId.isNullOrEmpty())
            emit(ApiResult.Error("you dont have a valid Session"))

        val lout = api.logout(sessionId!!)
        if(lout.isSuccessful){
            datastore.clear()
            sessionId = null
            isLogged.value = false
            token = null
            accountId = 0
            newToken()
            emit(ApiResult.Success(lout))
        } else
            emit(ApiResult.Error(fromJson(lout.errorBody()?.string())))
    }

    companion object {
        const val USER_PREFS = "UserPrefs"
        val Context.userDataStore by dataStore(USER_PREFS, AccountSerializer)
    }

}

private fun fromJson(string: String?): ErrorResponse {
    return Gson().fromJson(string, ErrorResponse::class.java)
}

