package com.example.learnlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
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
            android.R.layout.simple_list_item_checked,
            spices
        )
        list.adapter = adapter


        // ListView の choiceMode を multipleChoiceModal にする場合このリスナーの登録が必須。
        // いずれかの項目を長押しすると複数選択モードになるが、リスナーが登録されていないとなんと
        // NullPointerException が発生する。
        list.setMultiChoiceModeListener(object: AbsListView.MultiChoiceModeListener {
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return true
            }

            override fun onItemCheckedStateChanged(
                mode: ActionMode?,
                position: Int,
                id: Long,
                checked: Boolean
            ) { /* Do nothing */ }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return true
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
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
        })
    }
}
