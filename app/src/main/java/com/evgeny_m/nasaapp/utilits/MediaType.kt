package com.evgeny_m.nasaapp.utilits

sealed class MediaType(val type: String){
    object Video : MediaType("video")
    object Image : MediaType("image")
}
