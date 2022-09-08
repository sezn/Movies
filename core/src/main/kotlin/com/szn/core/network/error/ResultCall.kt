package com.szn.core.network.error

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.szn.core.network.model.ErrorResponse
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultCall<T>(val delegate: Call<T>) :
    Call<Result<T>> {

    override fun enqueue(callback: Callback<Result<T>>) {
        delegate.enqueue(
            object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful) {
                        callback.onResponse(
                            this@ResultCall,
                            Response.success(
                                response.code(),
                                Result.success(response.body()!!)
                            )
                        )
                    } else {

                        //Please call errorBody().string() once only as the second try will return an empty string see: https://github.com/square/retrofit/issues/1321#issuecomment-251160231
                        val errorResponse = Gson().fromJson(response?.errorBody()?.string(), ErrorResponse::class.java)

                        callback.onResponse(
                            this@ResultCall,
                            Response.success(
                                Result.failure(
//                                    HttpException(response)
                                    errorResponse
                                )
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    callback.onFailure(
                        this@ResultCall,
                        t
                    )
                }
            }
        )
    }

    override fun isExecuted(): Boolean {
        return delegate.isExecuted
    }

    override fun execute(): Response<Result<T>> {
        return Response.success(Result.success(delegate.execute().body()!!))
    }

    override fun cancel() {
        delegate.cancel()
    }

    override fun isCanceled(): Boolean {
        return delegate.isCanceled
    }

    override fun clone(): Call<Result<T>> {
        return ResultCall(delegate.clone())
    }

    override fun request(): Request {
        return delegate.request()
    }

    override fun timeout(): Timeout {
        return delegate.timeout()
    }
}
