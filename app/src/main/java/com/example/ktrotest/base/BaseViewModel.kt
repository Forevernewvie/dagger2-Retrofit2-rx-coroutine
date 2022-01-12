package com.example.ktrotest.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktrotest.R
import com.example.ktrotest.model.ToastMessage
import com.example.ktrotest.util.Event
import com.example.ktrotest.util.Response
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


//wemeet BaseComponent 참조

open class BaseViewModel : ViewModel() {

    val restartEvent = eventLiveData<Unit>()
    val toastMessage = eventLiveData<ToastMessage>()
    val errorPopup = eventLiveData<Response<*>?>()
    val noDataEvent = eventLiveData<Unit>()

    suspend fun <T> asyncIO(block: suspend CoroutineScope.() -> T) =
        withContext(
            viewModelScope.coroutineContext + Dispatchers.IO,
            block = block
        )

    fun launchIO(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(Dispatchers.IO, block = block)

    fun launch(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(Dispatchers.Main, block = block)

    fun toastEvent(message: String) {
        toastMessage.postEvent(ToastMessage(msg = message))
    }


    protected fun <T> LiveData<T>.value(v: T) {
        if (this is MutableLiveData<T>) {
            value = v
        }
    }

    fun restart() {
        restartEvent.event()
    }

    protected fun <T> LiveData<T>.post(v: T) {
        if (this is MutableLiveData<T>) {
            postValue(v)
        }
    }
    /*
    fun handleHttpError(response: Response<*>?, showErrorPopup: Boolean = true, showToastMessage: Boolean = false) {
        when {
            response?.httpResponse?.status?.value == HttpStatusCode.Unauthorized.value ||
                    response?.httpResponse?.status?.value == ServerErrorCode.OTHER_DEVICE_LOGIN -> {
                noDataEvent.postEvent(Unit)
            }
            showErrorPopup -> errorPopup.postEvent(response)
            showToastMessage -> {
                toastMessage.postEvent(
                    response?.error?.error?.message?.let { ToastMessage(msg = it) }
                        ?: ToastMessage(msgResId = R.string.)
                )
            }
        }
    }*/

    fun <T> Response<T>.handleError(
        showErrorPopUp: Boolean = true,
        toastMessage: Boolean = false
    ): Response<T> {
        if(httpResponse?.status != HttpStatusCode.OK || data ==null) {

        }
        return this
    }

    protected fun <T> liveData(): LiveData<T> = MutableLiveData()
    protected fun <T> liveData(v: T): LiveData<T> = MutableLiveData(v)

    protected fun <T> eventLiveData(): LiveData<Event<T>> = MutableLiveData()

    protected fun <T> LiveData<Event<T>>.event(v: T) = value(Event(v))
    protected fun <T> LiveData<Event<T>>.postEvent(v: T) = post(Event(v))

    protected fun LiveData<Event<Unit>>.event() = value(Event(Unit))
    protected fun LiveData<Event<Unit>>.postEvent() = post(Event(Unit))
}