package com.evgeny_m.data.utils

sealed class MediaType(val type: String) {
    object Video : MediaType("video")
    object Image : MediaType("image")
    object Web : MediaType("web")
}
