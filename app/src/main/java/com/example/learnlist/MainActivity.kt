package com.example.learnlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.core.view.children
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupSpiceList()
        setupSongList()
        setupSongListByCustomAdapter()
        setupSongCardList()


        val sections = mapOf(
            R.id.show_spices to R.id.spices,
            R.id.show_songs to R.id.songs_container,
            R.id.show_song_cards to R.id.song_cards
        )
        val radios = findViewById<RadioGroup>(R.id.shownSections)
        radios.setOnCheckedChangeListener { _, checkedId ->
            for ((radioId, sectionId) in sections) {
                val view = findViewById<View>(sectionId)
                view.visibility = if (radioId == checkedId) View.VISIBLE else View.GONE
            }
        }
        radios.check(R.id.show_spices)
    }

    private fun setupSpiceList() {
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

    private fun setupSongList() {
        val songs = makeSongs().map { it.toMap() }

        // https://developer.android.com/reference/android/widget/SimpleAdapter
        // データを List<Map<String, String>> として渡し、マップのキーと TextView の ID を
        // 対応づけてデータを表示する事ができる。 TextView のみのシンプルなリストを作るのには使える。
        val adapter = SimpleAdapter(
            this,
            songs,
            R.layout.list_item,
            arrayOf("title", "tag", "desc"),
            intArrayOf(R.id.title, R.id.tag, R.id.desc)
        )

        val list = findViewById<ListView>(R.id.songs)
        list.adapter = adapter
    }

    private fun setupSongListByCustomAdapter() {
        val adapter = MyListAdapter(this, makeSongs(), R.layout.list_item)
        val list = findViewById<ListView>(R.id.songs2)
        list.adapter = adapter
    }

    private fun setupSongCardList() {
        val songCardsLayoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        // val songCardsLayoutManager = GridLayoutManager(this, 2)

        findViewById<RecyclerView>(R.id.song_cards).apply {
            setHasFixedSize(false)
            adapter = SongCardListAdapter(makeSongs())
            layoutManager = songCardsLayoutManager
        }
    }

    private fun makeSongs(): ArrayList<Song> {
        return arrayListOf(
            Song(id = 0, title = "革命のエチュード", tag = "ピアノ"),
            Song(id = 1, title = "G線上のアリア", tag = "バイオリン"),
            Song(id = 2, title = "シャコンヌ", tag = "チェロ"),
            Song(id = 3, title = "夜の女王のアリア", tag = "声楽"),
            Song(id = 4, title = "春の海", tag = "?")
        )
    }
}
