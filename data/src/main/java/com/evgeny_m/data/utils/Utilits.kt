package com.evgeny_m.data.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.evgeny_m.data.model.ApodData
import com.evgeny_m.data.stringToLocalDate
import com.evgeny_m.domain.model.Item

@RequiresApi(Build.VERSION_CODES.O)
fun apodToItem(apodData: ApodData): Item {

    val urlList = apodData.url?.split('/')

    if (urlList!!.contains("www.youtube.com") &&
        apodData.media_type == MediaType.Video.type
    ) {
        val string = urlList.last()
        val id = string.split('?').first()
        return Item(
            copyright = apodData.copyright ?: "",
            date = stringToLocalDate(apodData.date!!),
            explanation = apodData.explanation ?: "",
            hdurl = apodData.hdurl ?: "",
            media_type = MediaType.Video.type,
            service_version = apodData.service_version ?: "",
            title = apodData.title ?: "",
            url = "https://img.youtube.com/vi/$id/mqdefault.jpg"
        )

    } else if (!urlList.contains("www.youtube.com") &&
        apodData.media_type == MediaType.Video.type
    ) {
        return Item(
            copyright = apodData.copyright ?: "",
            date = stringToLocalDate(apodData.date!!),
            explanation = apodData.explanation ?: "",
            hdurl = apodData.hdurl ?: "",
            media_type = MediaType.Web.type,
            service_version = apodData.service_version ?: "",
            title = apodData.title ?: "",
            url = "http://favicon.yandex.net/favicon/${urlList[2]}"
        )
    } else {
        return Item(
            copyright = apodData.copyright ?: "",
            date = stringToLocalDate(apodData.date!!),
            explanation = apodData.explanation ?: "",
            hdurl = apodData.hdurl ?: "",
            media_type = MediaType.Image.type,
            service_version = apodData.service_version ?: "",
            title = apodData.title ?: "",
            url = apodData.url
        )
    }
}