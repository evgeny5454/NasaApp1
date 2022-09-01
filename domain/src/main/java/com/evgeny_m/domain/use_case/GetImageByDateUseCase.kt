package com.evgeny_m.domain.use_case

import com.evgeny_m.domain.model.Item
import com.evgeny_m.domain.repository.Repository
import java.time.LocalDate

class GetImageByDateUseCase(private val repository: Repository) {

    suspend fun execute(): Item? {
        return repository.getData()
    }
}