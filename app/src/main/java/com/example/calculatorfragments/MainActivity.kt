package com.example.calculatorfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.calculatorfragments.databinding.ActivityMainBinding
const val IS_RESULT_PAGE = "Is_Result_Page"
const val RESULT = "result"
const val REQUEST_KEY = "requestKey"
const val OPERAND1 = "operand1"
const val OPERAND2 = "operand2"
const val OPERATION = "operation"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.fragment_container, MainScreen())
            fragmentTransaction.commit()
        }
    }
}