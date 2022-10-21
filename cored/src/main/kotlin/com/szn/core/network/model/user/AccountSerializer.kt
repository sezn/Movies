package com.szn.core.network.model.user

import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object AccountSerializer: Serializer<Account> {

    override val defaultValue: Account
        get() = Account()

    override suspend fun readFrom(input: InputStream): Account {
        return try {
            Json.decodeFromString(
                deserializer = Account.serializer(),
                string = input.readBytes().decodeToString()
            )
        }catch (e: SerializationException){
            defaultValue
        }
    }

    override suspend fun writeTo(t: Account, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = Account.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}