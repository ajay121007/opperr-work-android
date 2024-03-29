package com.expert.operrwork.util

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
    var sharedPref: SharedPreferences
    init {
        sharedPref = context.getSharedPreferences(Constant.SharedPrefName, 0)
    }

    fun setString(key: String, value: String) {
        sharedPref.edit().putString(key, value).commit()
    }

    fun getString(key: String): String {
        return sharedPref.getString(key, "")
    }

    fun setBoolean(key:String,value:Boolean){
        sharedPref.edit().putBoolean(key, value).commit()
    }

    fun getBoolean(key:String):Boolean{
        return sharedPref.getBoolean(key, false)
    }
}