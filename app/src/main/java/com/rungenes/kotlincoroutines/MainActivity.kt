package com.rungenes.kotlincoroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val RESULT_1 ="Result_1"
    private val RESULT_2 ="Result_2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener {

            CoroutineScope(IO).launch {

                getResultApi()

            }
        }

    }

    //IO,Main and Default

    private suspend fun getResultApi(){
        val result1= getDataAPI1()

        println("Debug: $result1")


        updateUI(result1)
        val result2 = getDataAPI2()

        updateUI(result2)
    }

    private fun setNewText(input:String){

        val newText = text.text.toString() +"\n$input"
        text.text = newText

    }

    private suspend fun updateUI(input: String){

        withContext(Main){
           setNewText(input)
        }
    }

    private suspend fun getDataAPI1():String {

        logThread("getDataAPI1")
        delay(1000)
        return RESULT_1

    }

    private suspend fun getDataAPI2():String {

        logThread("getDataAPI2")
        delay(1000)
        return RESULT_2

    }

    private fun logThread(methodName:String ){

        println("Debug ${methodName} : ${Thread.currentThread().name}")
    }
}
