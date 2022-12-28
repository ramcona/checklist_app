package id.rafli.mychecklist.ui.home


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.rafli.mychecklist.app.App.Companion.pref
import id.rafli.mychecklist.app.App.Companion.token
import id.rafli.mychecklist.model.User
import id.rafli.mychecklist.repositories.AuthRepo
import id.rafli.mychecklist.repositories.ChecklistRepo
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {
    var job: Job? = null
    private var repo = ChecklistRepo()
    val responseData = MutableLiveData<User>()
    var isLoading = MutableLiveData<Boolean>()
    var errorMsg = MutableLiveData<String>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        setError("Exception handled: ${throwable.localizedMessage}")
    }

    private fun setError(message: String) {
        Log.e("ERROR", message)
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

    fun getAllChecklist() {
        isLoading.postValue(true)


        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val result = repo.getAllCheclist(token)
            withContext(Dispatchers.Main) {
                if (result.isSuccessful){
                    val responseBody = result.body()
                    if (responseBody != null) {
                        isLoading.postValue(false)
                        responseData.postValue(responseBody.data)

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