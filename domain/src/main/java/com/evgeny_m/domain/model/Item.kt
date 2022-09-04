package com.evgeny_m.domain.model

import java.time.LocalDate

data class Item(
    val copyright: String,
    val date: LocalDate,
    val explanation: String,
    val url: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val urlImage: String
)
