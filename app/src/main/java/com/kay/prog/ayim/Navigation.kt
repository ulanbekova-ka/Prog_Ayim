package com.kay.prog.ayim

interface Navigation {
    fun initMainFrg()
    fun initAddFrg()
    fun initShowFrg()
    fun initEditFrg(id : Long)
    fun getId() : Long
}