package com.example.learnlist

data class Song(val id: Long, val title: String, val tag: String, val desc: String = "description of $title") {
    fun toMap(): Map<String, String> {
        return mapOf("title" to title, "tag" to tag, "desc" to desc)
    }
}