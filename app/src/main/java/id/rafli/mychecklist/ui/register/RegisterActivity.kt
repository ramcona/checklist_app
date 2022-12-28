package id.rafli.mychecklist.ui.register

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import id.rafli.mychecklist.R
import id.rafli.mychecklist.databinding.ActivityRegisterBinding
import id.rafli.mychecklist.helper.BaseActivity
import id.rafli.mychecklist.helper.Go
import id.rafli.mychecklist.helper.SweetAlert
import id.rafli.mychecklist.helper.viewBinding
import id.rafli.mychecklist.model.User
import id.rafli.mychecklist.ui.login.LoginActivity

class RegisterActivity : BaseActivity() {

    private val binding by viewBinding(ActivityRegisterBinding::inflate)
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar(getString(R.string.teks_daftar), binding.toolbar)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[RegisterViewModel::class.java]

        observeData()
        action()
    }

    private fun action(){

        binding.tvMasuk.setOnClickListener {
            Go(this).move(LoginActivity::class.java)
        }

        binding.btnDaftar.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val konf_password = binding.edtKonfirmasiPassword.text.toString()

            if (username.isEmpty() && password.isEmpty() && email.isEmpty() && konf_password.isEmpty()){
                SweetAlert.warning(this, getString(R.string.teks_peringatan), getString(R.string.teks_lengkapi_data_anda))
            }else{

                if (password != konf_password){
                    SweetAlert.warning(this, getString(R.string.teks_peringatan), getString(R.string.teks_konfirmasi_password_anda_tidak_valid))
                }else{
                    val user = User()
                    user.username = username
                    user.password = password
                    user.email = email
                    viewModel.register(user)
                }

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

        viewModel.isSuccess.observe(this){
            SweetAlert.success(this, getString(R.string.teks_daftar), getString(R.string.teks_anda_berhasil_mendaftar), finish = true)
        }

//        viewModel.detailGame.observe(this){
//            setDataDetail(it)
//        }


    }

}