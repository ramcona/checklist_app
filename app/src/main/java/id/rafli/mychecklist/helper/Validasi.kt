package id.rafli.mychecklist.helper

import androidx.databinding.library.BuildConfig

class   Validasi {

    fun isRelease(): Boolean {
        return false
    }

    fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }


}