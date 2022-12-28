package id.rafli.mychecklist.ui.login


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.rafli.mychecklist.app.App.Companion.pref
import id.rafli.mychecklist.app.App.Companion.token
import id.rafli.mychecklist.model.User
import id.rafli.mychecklist.repositories.AuthRepo
import kotlinx.coroutines.*

class LoginViewModel : ViewModel() {
    var job: Job? = null
    private var repo = AuthRepo()
    val responseData = MutableLiveData<User>()
    var isLoading = MutableLiveData<Boolean>()
    var errorMsg = MutableLiveData<String>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        setError("Exception handled: ${throwable.localizedMessage}")
    }

    private fun setError(message: String) {
        errorMsg.postValue(message)
        isLoading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()

        try{
            job?.cancel()
        }catch (_: UninitializedPropertyAccessException){

        }

    }

    fun login(user: User) {
        isLoading.postValue(true)


        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val result = repo.login(user)
            withContext(Dispatchers.Main) {
                if (result.isSuccessful){
                    val responseBody = result.body()
                    if (responseBody != null) {
                        isLoading.postValue(false)
                        responseData.postValue(responseBody.data)
                        pref.setIsLogin(true)
                        pref.setUser(responseBody.data)
                        token = "Bearer "+responseBody.data.token
                    }else {
                        setError("Tidak ada data tersedia")
                    }
                }else{
                    val responseBody = result.body()
                    if (responseBody != null) {
                        setError(responseBody.errorMessage)
                    }else{
                        setError(result.message())
                    }

                }
            }
        }
    }


}