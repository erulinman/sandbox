package info.erulinman.animations.animationutils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnimationUtilsViewModel: ViewModel() {

    enum class STATE { STOPPED, STARTED, COUNTING }

    val state = MutableLiveData(STATE.STOPPED)

    private val _counter = MutableLiveData(0)
    val counter: LiveData<Int> get() = _counter

    fun updateCounter(clear: Boolean = false) {
        if (!clear) {
            val counterValue = counter.value!!
            _counter.value = counterValue + 1
        } else {
            _counter.value = 0
        }
    }
}