package com.tastyday.domain.replies

class Children(
    private val children: MutableList<Reply>?,
) {

    var to: List<Reply>? = children
        get() = this.children?.toList() ?: emptyList()

    fun remove(reply: Reply) {
        this.children?.remove(reply)
    }

    fun add(reply: Reply) {
        this.children?.add(reply)
    }

}