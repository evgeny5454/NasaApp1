package com.evgeny_m.data.utils

sealed class Video(val type: String) {
    object Youtube : Video("www.youtube.com")
}
