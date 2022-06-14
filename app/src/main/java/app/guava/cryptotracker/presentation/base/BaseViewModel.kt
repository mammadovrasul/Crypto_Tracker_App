package app.guava.cryptotracker.presentation.base

import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by rasulmammadov on 14,Jun,2022
 */
abstract class BaseViewModel : ViewModel(), LifecycleObserver, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    private val mLaunchManager: MutableList<Job> = mutableListOf()

    /**
     * add launch task to [mLaunchManager]
     */

    override fun onCleared() {
        clearLaunchTask()
        super.onCleared()
    }

    private fun clearLaunchTask() {
        for (job in mLaunchManager) {
            job.cancel()
        }
        mLaunchManager.clear()
    }

    private val loading = MutableLiveData<Boolean>()
    private val message = MutableLiveData<Int>()
    val errorLiveData = MutableLiveData<Throwable>()
    val loadingState: LiveData<Boolean>
        get() = loading

    fun getErrorLiveData(): LiveData<Throwable> {
        return errorLiveData
    }

    fun loading(isLoading: Boolean) {
        loading.value = isLoading
    }
}