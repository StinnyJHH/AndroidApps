package course.labs.lab5_lifecycle_aware
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

//ToDo:
class ActivityObserver(private val model: CounterViewModel) : LifecycleObserver{
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreateEvent() {
        Log.i(TAG, "Entered onCreate")

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStartEvent()
    {
        Log.i(TAG, "Entered onStart")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResumeEvent() {
        Log.i(TAG, "ON_RESUME event")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPauseEvent() {
        Log.i(TAG, "ON_PAUSE event")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopEvent() {
        Log.i(TAG, "ON_STOP event")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroyEvent() {
        Log.i(TAG, "ON_DESTROY event")
    }

    companion object
    {
        private const val TAG = "Lab-Lifecycle"
    }

}