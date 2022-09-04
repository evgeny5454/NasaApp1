package com.evgeny_m.domain.repository

import com.evgeny_m.domain.model.Item
import java.time.LocalDate

interface Repository {
    suspend fun getData(): Item?
    suspend fun getArrayImages(date: LocalDate?): List<Item>?
}