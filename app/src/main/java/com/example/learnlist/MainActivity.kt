package com.example.learnlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spices = arrayOf(
            "胡椒",
            "ターメリック",
            "コリアンダー",
            "生姜",
            "ニンニク",
            "サフラン"
        )

        val list = findViewById<ListView>(R.id.spices)
        list.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            spices
        )
    }
}
