package com.aio.kotlin.utils

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject

class JsonConverterUtils {

    fun stringToJson(jsonString: String?): JsonObject? {
        val gson = Gson()
        var jsonObject:JsonObject ?= null
        if (jsonString != null) {
            try {
                jsonObject = gson.fromJson(jsonString, JsonObject::class.java)
            } catch (e: Exception) {
                Log.e("JSON Parse Error", e.message ?: "Unknown JSON Parsing Error")
            }
        }
        return jsonObject
    }
}