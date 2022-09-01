package com.evgeny_m.nasaapp.utilits

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
fun currentDate(): LocalDate {
    return LocalDate.now()
}

@RequiresApi(Build.VERSION_CODES.O)

fun getStartDate(endDate: LocalDate, datesCount: Long): LocalDate {
    return endDate.minusDays(datesCount)
}

fun getYoutubeImage(url: String): String {
    val string = url.split('/').last()
    val id = string.split('?').first()
    return "https://img.youtube.com/vi/$id/mqdefault.jpg"
}
