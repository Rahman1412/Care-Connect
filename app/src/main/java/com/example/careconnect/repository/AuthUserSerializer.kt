package com.example.careconnect.repository

import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream

object AuthUserSerializer : Serializer<User>{
    override val defaultValue: User = User.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): User {
        return User.parseFrom(input)
    }

    override suspend fun writeTo(t: User, output: OutputStream) {
        t.writeTo(output)
    }

}