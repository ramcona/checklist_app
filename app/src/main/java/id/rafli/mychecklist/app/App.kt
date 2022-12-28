package id.rafli.mychecklist.app

import android.app.Application
import android.os.StrictMode
import id.rafli.mychecklist.helper.Helper
import id.rafli.mychecklist.helper.HelperToast
import id.rafli.mychecklist.helper.SharedPref

class App : Application() {

    companion object {
        var helper = Helper
        var toast = HelperToast()
        lateinit var pref: SharedPref
        var token = ""

    }

    override fun onCreate() {
        super.onCreate()
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        pref = SharedPref(this)

        token = "Bearer "+pref.getUser().token
    }

}