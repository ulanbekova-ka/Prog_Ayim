package com.kay.prog.ayim

data class RepoResult(
    val items: List<Item>
)

data class Item(
    val id: Long?,
    val name: String?,
    val full_name: String?,
    val private: Boolean,
    val owner: Owner?,
    val html_url: String?,
    val description: String?
)

data class Owner(val login: String?, val id: Long?, val avatar_url: String?)