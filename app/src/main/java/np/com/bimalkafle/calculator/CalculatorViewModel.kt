package np.com.bimalkafle.calculator

import android.content.Context
import android.media.audiofx.Equalizer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Scriptable

class CalculatorViewModel : ViewModel(){

    private val _equationText = MutableLiveData("")
    val equationText : LiveData<String> = _equationText

    private val _resultText = MutableLiveData("0")
    val resultText : LiveData<String> =_resultText

    fun onButtonClick(btn : String){
        Log.i("Clicked Button", btn)

        _equationText.value?.let {
            if (btn == "AC"){
                _equationText.value =""
                _resultText.value ="0"
                return
            }

            if(btn == "C"){
                if(it.isNotEmpty()){
                    _equationText.value = it.substring(0,it.length-1)
                    return
                }
            }

            if(btn == "="){
                _equationText.value = _resultText.value
                return
            }

            _equationText.value = it+btn


        }

        fun calculateResult(equation : String) : String{
            val context : org.mozilla.javascript.Context = org.mozilla.javascript.Context.enter()
            context.optimizationLevel = -1
            val scriptable : Scriptable = context.initStandardObjects()
            context.evaluateString(scriptable,equation,"Javascript",1,null)

        }
    }
}