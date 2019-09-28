package com.example.phonepequiz.utils

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

fun jumbleSequence(name: String?): List<Char> {
    val chars = mutableListOf<Char>()

    for (ch in name.toCharArray()) {
        chars.add(ch)
    }
    chars.shuffle()
    return chars
}

fun <T> parseListResponse(response: String?, classOfT: Class<T>): Array<T>? {
    val gson = Gson()
    try {
        val listType = TypeToken.getArray(classOfT)
        return gson.fromJson(response, listType.type)
    } catch (e: JsonSyntaxException) {
        throw e
    } catch (e: Exception) {
        Log.e("json exception", e.message)
    }
    return null
}