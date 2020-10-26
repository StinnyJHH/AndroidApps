package course.labs.lab5_lifecycle_aware
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//ToDo: define your own view model that would be used to store the data when activity is destroyed during rotation

class CounterViewModel : ViewModel() {
    companion object{
        private var portraitCtr = 0;
        private var landscapeCtr = 0;
        private const val TAG = "CounterViewModel"
    }
    private val mPortraitCounter = MutableLiveData<Int>()
    private val mLandscapeCounter = MutableLiveData<Int>()

    init{
        mPortraitCounter.value = 0
        mLandscapeCounter.value = 0
    }

    fun increasePortrait(){
        portraitCtr++
        mPortraitCounter.value = portraitCtr
    }

    fun increaseLandscape(){
        landscapeCtr++
        mPortraitCounter.value = landscapeCtr
    }

    fun resetPortrait(){
        portraitCtr = 0
    }

    fun resetLandscape(){
        landscapeCtr = 0
    }
}