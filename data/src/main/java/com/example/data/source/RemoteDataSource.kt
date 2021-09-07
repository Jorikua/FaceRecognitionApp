package com.example.data.source

import android.content.Context
import com.example.data.R
import com.example.data.model.PersonResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okio.buffer
import okio.source

class RemoteDataSource(
    private val context: Context,
    private val moshi: Moshi
) {

    private val persons = mutableListOf<PersonResponse>()

    fun getPersons(): List<PersonResponse> {
        if (persons.isEmpty()) {
            context.resources.openRawResource(R.raw.android_guys).source().buffer().use {
                val type = Types.newParameterizedType(List::class.java, PersonResponse::class.java)
                val list = moshi.adapter<List<PersonResponse>>(type).fromJson(it) ?: emptyList()
                persons.addAll(list)
            }
        }

        return persons
    }
}