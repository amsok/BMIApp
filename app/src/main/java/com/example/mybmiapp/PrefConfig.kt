package com.example.mybmiapp

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PrefConfig {

    companion object Friend{

        val LIST_KEY: String? = "list_key"
    fun writeListInPref(context: Context, list: ArrayList<Item>) {
        var gson = Gson()
        var jsonString = gson.toJson(list)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var editor = pref.edit()
        editor.putString(LIST_KEY,jsonString)
    }

    fun readListFromPref(context: Context): ArrayList<Item>? {
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var jsonString = pref.getString(LIST_KEY, "")
        val type = object : TypeToken<List<Item>>() {}.type
        return Gson().fromJson(jsonString, type)
    }
    }
}