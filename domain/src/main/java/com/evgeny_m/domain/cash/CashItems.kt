package com.evgeny_m.domain.cash

import com.evgeny_m.domain.model.Item

object CashItems{
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