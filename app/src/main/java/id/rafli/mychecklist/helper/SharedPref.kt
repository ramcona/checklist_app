package id.rafli.mychecklist.helper

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import id.rafli.mychecklist.model.User

class SharedPref(context : Context) {

    private val mypref = "MAIN_PREF"
    private val sp: SharedPreferences = context.getSharedPreferences(mypref, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sp.edit()
    private val isLogin = "isLogin"
    private val users = "users"

    fun setUser(data:
                User): User {
        val json = Gson().toJson(data, User::class.java)
        sp.edit().putString(users, json).apply()
        return getUser()
    }

    fun getUser(): User {
        val data = sp.getString(users, null) ?: return User()
        return Gson().fromJson(data, User::class.java)
    }

    fun setIsLogin(value : Boolean){
        sp.edit().putBoolean(isLogin, value).apply()
    }

    fun getIsLogin(): Boolean {
        return sp.getBoolean(isLogin, false)
    }


    fun clearAll() {
        editor.clear()
        editor.commit()
    }

}