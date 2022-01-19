package com.kay.prog.ayim

interface Navigation {
    fun initMainFrg()
    fun initAddFrg()
    fun initEmployeeFrg(id : Long)
    fun initEditFrg(id : Long)
}