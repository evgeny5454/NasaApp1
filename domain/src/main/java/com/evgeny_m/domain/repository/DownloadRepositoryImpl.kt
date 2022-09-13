package com.evgeny_m.domain.repository

interface DownloadRepositoryImpl {
    fun downloadImage(uri: String, title: String)
}