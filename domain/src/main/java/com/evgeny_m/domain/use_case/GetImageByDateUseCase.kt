package com.evgeny_m.domain.use_case

import com.evgeny_m.domain.model.Item
import com.evgeny_m.domain.repository.ApodRepositoryImpl

class GetImageByDateUseCase(private val repository: ApodRepositoryImpl) {

    suspend fun execute(): Item? {
        println(repository.getData())
        return repository.getData()
    }
}