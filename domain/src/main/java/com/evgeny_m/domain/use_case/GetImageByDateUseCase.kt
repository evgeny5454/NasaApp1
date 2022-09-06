package com.evgeny_m.domain.use_case

import com.evgeny_m.domain.model.Item
import com.evgeny_m.domain.repository.Repository

class GetImageByDateUseCase(private val repository: Repository) {

    suspend fun execute(): Item? {
        println(repository.getData())
        return repository.getData()
    }
}