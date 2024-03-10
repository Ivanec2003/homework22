package com.example.homework22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.homework22.fragment.NotifiFragment
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment()

        FirebaseMessaging.getInstance().token.addOnCompleteListener{task->
            val token = task.result
            Log.d("TOKEN", token)
        }
    }

    private fun loadFragment(){
        val fragment = NotifiFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit()
    }
}