package id.rafli.mychecklist.ui.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import id.rafli.mychecklist.R
import id.rafli.mychecklist.databinding.ActivityLoginBinding
import id.rafli.mychecklist.databinding.ActivityMainBinding
import id.rafli.mychecklist.helper.BaseActivity
import id.rafli.mychecklist.helper.Go
import id.rafli.mychecklist.helper.SweetAlert
import id.rafli.mychecklist.helper.viewBinding
import id.rafli.mychecklist.ui.login.LoginViewModel

class MainActivity : BaseActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        statusPutih()

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]

        observeData()

        viewModel.getAllChecklist()
    }

    private fun observeData(){
        viewModel.isLoading.observe(this) {
            if (it) {
                SweetAlert.onLoading(this)
            }else{
                SweetAlert.dismis()
            }
        }

        viewModel.errorMsg.observe(this){
            SweetAlert.onFailure(this, it)
        }


//        viewModel.responseData.observe(this){
//            Go(this).move(MainActivity::class.java, clearPrevious = true)
//        }
    }
}