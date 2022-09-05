package com.evgeny_m.nasaapp.presenter

import com.evgeny_m.domain.model.Item

object CashList{
    private var cashList = mutableListOf<Item>()

    fun putList(list: List<Item>) {
        cashList.addAll(cashList.size, list)
    }
    fun getList() : List<Item>{
        return cashList.ifEmpty {
            emptyList()
        }
    }
}