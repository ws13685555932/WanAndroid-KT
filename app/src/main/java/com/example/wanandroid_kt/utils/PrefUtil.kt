package com.example.wanandroid_kt.utils

import android.content.Context
import android.text.TextUtils
import android.util.Base64
import com.example.wanandroid_kt.MyApplication
import com.example.wanandroid_kt.ext.log
import java.io.*
import java.lang.IllegalArgumentException

object PrefUtil {
    private const val TAG : String = "sharedPreference"
    private const val DATA_FILE = "data"

    fun saveValue(key : String, value : Any){
        val editor = MyApplication.context.getSharedPreferences(DATA_FILE, Context.MODE_PRIVATE).edit()
        when (value){
            is Int -> editor.putInt(key, value)
            is String -> editor.putString(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Float -> editor.putFloat(key, value)
            is Long -> editor.putLong(key, value)
            else -> editor.putString(key, value.toString())
        }
        editor.apply()
    }

    fun getValue(key: String, default : Any?) : Any?{
        val shared = MyApplication.context.getSharedPreferences(DATA_FILE, Context.MODE_PRIVATE)
        return when(default){
            is Int -> shared.getInt(key, default)
            is String -> shared.getString(key ,default)
            is Long -> shared.getLong(key, default)
            is Float -> shared.getFloat(key, default)
            is Boolean -> shared.getBoolean(key, default)
            else -> null
        }
    }

    fun saveObject(key: String, value: Any?) {
        if (value == null) {
            return
        }
        if (value !is Serializable) {
            return
        }
        var baos: ByteArrayOutputStream? = null
        var oos: ObjectOutputStream? = null
        try {
            val sp = MyApplication.context.getSharedPreferences(DATA_FILE, Context.MODE_PRIVATE)
            baos = ByteArrayOutputStream()
            oos = ObjectOutputStream(baos)
            oos.writeObject(value)
            val temp = String(Base64.encode(baos.toByteArray(), Base64.DEFAULT))
            sp.edit().putString(key, temp).apply()
            baos.close()
            oos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getObject(key: String): Any? {
        var `object`: Any? = null
        var bais: ByteArrayInputStream? = null
        var ois: ObjectInputStream? = null
        val sp = MyApplication.context.getSharedPreferences(DATA_FILE, Context.MODE_PRIVATE)
        val temp = sp.getString(key, "")
        if (!TextUtils.isEmpty(temp)) {
            try {
                bais = ByteArrayInputStream(Base64.decode(temp!!.toByteArray(), Base64.DEFAULT))
                ois = ObjectInputStream(bais)
                `object` = ois.readObject()
                bais.close()
                ois.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return `object`
    }
}