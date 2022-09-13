package com.evgeny_m.domain.use_case

import com.evgeny_m.domain.repository.DownloadRepositoryImpl

class DownloadImageByUrlUseCase(private val repository: DownloadRepositoryImpl) {
    fun execute(uri: String, title: String){
        repository.downloadImage(uri = uri, title = title)
    }
}