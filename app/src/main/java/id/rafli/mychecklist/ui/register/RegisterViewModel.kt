package id.rafli.mychecklist.ui.register


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.rafli.mychecklist.model.User
import id.rafli.mychecklist.repositories.AuthRepo
import kotlinx.coroutines.*

class RegisterViewModel : ViewModel() {
    var job: Job? = null
    private var repo = AuthRepo()
    val isSuccess = MutableLiveData<Boolean>()
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

    fun register(user: User) {
        isLoading.postValue(true)


        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val result = repo.register(user)
            withContext(Dispatchers.Main) {
                if (result.isSuccessful){
                    val responseBody = result.body()
                    if (responseBody != null) {
                        isLoading.postValue(false)
                        isSuccess.postValue(true)
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