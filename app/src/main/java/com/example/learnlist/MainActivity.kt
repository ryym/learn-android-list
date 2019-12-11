package com.example.learnlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spices = mutableListOf(
            "胡椒",
            "ターメリック",
            "コリアンダー",
            "生姜",
            "ニンニク",
            "サフラン"
        )

        val list = findViewById<ListView>(R.id.spices)
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            spices
        )
        list.adapter = adapter


        list.setOnItemLongClickListener { parent, view, position, id ->
            val clicked = (view as TextView).text.toString()

            // 上から見て最初にマッチしたテキストの要素を消すっぽい。
            adapter.remove(clicked)

            // イベントを消費しない。消費するとこの次の click listener のイベントは呼ばれなくなる。
            false
        }

        list.setOnItemClickListener { parent, view, position, id ->
            val clicked = (view as TextView).text.toString()
            Toast.makeText(this, clicked, Toast.LENGTH_SHORT).show()
        }
    }
}
