package com.example.learnlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// MyListAdapter と同じようなカスタムアダプターを RecylcerView で実装してる。
// 最初に RecyclerView を見た時はとっつきづらそうに見えたけど、こうして見てみると
// BaseAdapter を継承するよりもスッキリ書ける。 getItem(): Any とか convertView とかがなく、
// ViewHolder の生成とバインドを宣言的に書けば済むだけになってる。

class SongCardListAdapter(private val songs: ArrayList<Song>) : RecyclerView.Adapter<SongCardListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.song_card_title)
        val tag: TextView = view.findViewById(R.id.song_card_tag)
        val desc: TextView = view.findViewById(R.id.song_card_desc)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.song_card, parent, false)
        return SongCardListAdapter.ViewHolder(view)
    }

    // RecyclerView は同じ ViewHolder を使い回して効率化してるらしい。
    // こちらは ViewHolder のバインド方法さえ定義すれば良い。
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = songs.get(position)
        holder.title.text = song.title
        holder.tag.text = song.tag
        holder.desc.text = song.desc
    }
}