package app.test.coroutines


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val TAG:String = "KOTLINFUN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, Thread.currentThread().name)
    }

    fun updateCounter(view: View) {
        Log.d(TAG, Thread.currentThread().name)
        val counterText = findViewById<TextView>(R.id.counter)
        counterText.text = "${counterText.text.toString().toInt() + 1}"
    }

    private fun executeLongRunningTask() {
        for (i in 1..1000000000L){

        }
    }

    fun doAction(view: View) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d(TAG, "1- ${Thread.currentThread().name}")
        }

        GlobalScope.launch(Dispatchers.Main) {
            Log.d(TAG, "2- ${Thread.currentThread().name}")
        }

        MainScope().launch(Dispatchers.Default) {
            Log.d(TAG, "3- ${Thread.currentThread().name}")
        }
    }
}