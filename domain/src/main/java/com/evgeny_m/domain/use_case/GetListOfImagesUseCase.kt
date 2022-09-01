package com.evgeny_m.domain.use_case

import com.evgeny_m.domain.model.Item
import com.evgeny_m.domain.repository.Repository
import java.time.LocalDate

class GetListOfImagesUseCase(private val repository: Repository) {
    suspend fun execute(): List<Item>? {
        return repository.getArrayImages()
    }

    suspend fun execute(date: LocalDate?): List<Item>? {
        return repository.getArrayImages(date = date)
    }
}