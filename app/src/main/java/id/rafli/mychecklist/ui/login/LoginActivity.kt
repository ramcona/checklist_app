package id.rafli.mychecklist.ui.login

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import id.rafli.mychecklist.ui.home.MainActivity
import id.rafli.mychecklist.R
import id.rafli.mychecklist.app.App.Companion.pref
import id.rafli.mychecklist.databinding.ActivityLoginBinding
import id.rafli.mychecklist.helper.BaseActivity
import id.rafli.mychecklist.helper.Go
import id.rafli.mychecklist.helper.SweetAlert
import id.rafli.mychecklist.helper.viewBinding
import id.rafli.mychecklist.model.User
import id.rafli.mychecklist.ui.register.RegisterActivity

class LoginActivity : BaseActivity() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        statusPutih()

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[LoginViewModel::class.java]

        if (pref.getIsLogin()){
            Go(this).move(MainActivity::class.java, clearPrevious = true)
        }

        observeData()
        action()
    }

    private fun action(){

        binding.tvDaftar.setOnClickListener {
            Go(this).move(RegisterActivity::class.java)
        }

        binding.btnMasuk.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()

            if (username.isEmpty() && password.isEmpty()){
                SweetAlert.warning(this, getString(R.string.teks_peringatan), getString(R.string.teks_lengkapi_data_anda_untuk_masuk))
            }else{
                val user = User()
                user.username = username
                user.password = password
                viewModel.login(user)
            }
        }
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


        viewModel.responseData.observe(this){
            Go(this).move(MainActivity::class.java, clearPrevious = true)
        }
    }
}