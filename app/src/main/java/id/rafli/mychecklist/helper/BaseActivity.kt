package id.rafli.mychecklist.helper

import android.content.res.Configuration
import android.os.Build
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import id.rafli.mychecklist.R
import id.rafli.mychecklist.databinding.LayoutToolbarBinding
import java.util.*


open class BaseActivity : AppCompatActivity() {

    fun statusPutih() {
        supportActionBar?.hide()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val window: Window = window
            val decorView: View = window.decorView
            val wic = WindowInsetsControllerCompat(window, decorView)


            wic.isAppearanceLightStatusBars = false // true or false as desired.
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        }


    }


    fun setToolbar(title: String, toolbarTitle: LayoutToolbarBinding) {
        statusPutih()

        when (resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                toolbarTitle.toolbarBack.setImageResource(R.drawable.ic_back_white)
            }
            Configuration.UI_MODE_NIGHT_NO -> {
//                toolbarTitle.toolbarBack.setImageResource(R.drawable.ic_back_black)
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                toolbarTitle.toolbarBack.setImageResource(R.drawable.ic_back_black)
            }
        }

        toolbarTitle.toolbarTitle.text = title
        toolbarTitle.toolbarBack.setOnClickListener {
            onBackPressed()
        }

        //mengubah warna status bar

    }


}