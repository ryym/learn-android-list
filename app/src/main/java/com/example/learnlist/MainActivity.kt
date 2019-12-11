package com.example.learnlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.view.children

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
            android.R.layout.simple_list_item_multiple_choice,
            spices
        )
        list.adapter = adapter


        list.setOnItemLongClickListener { parent, view, positnion, id ->
            val clicked = (view as TextView).text.toString()
            Log.d("main", clicked)

            // イベントを消費しない。消費するとこの次の click listener のイベントは呼ばれなくなる。
            false
        }

        list.setOnItemClickListener { parent, view, position, id ->
            var msg = "選択したのは: "
            for (child in list.children) {
                val txt = child as CheckedTextView
                if (txt.isChecked) {
                    msg += txt.text.toString() + ","
                }
            }
            msg = msg.substring(0, msg.length - 1)
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
        }
    }
}
