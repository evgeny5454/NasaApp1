package com.evgeny_m.data.utils

import android.os.Build
import android.util.Log
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
            url = apodData.url,
            media_type = MediaType.Video.type,
            service_version = apodData.service_version ?: "",
            title = apodData.title ?: "",
            urlImagePreview = "https://img.youtube.com/vi/$id/mqdefault.jpg"
        )

    } else if (!urlList.contains("www.youtube.com") &&
        apodData.media_type == MediaType.Video.type
    ) {
        Log.d("FAVICON", urlList.toString())
        return Item(
            copyright = apodData.copyright ?: "",
            date = stringToLocalDate(apodData.date!!),
            explanation = apodData.explanation ?: "",
            url = apodData.url,
            media_type = MediaType.Web.type,
            service_version = apodData.service_version ?: "",
            title = apodData.title ?: "",
            urlImagePreview = "http://favicon.yandex.net/favicon/${urlList[2]}"
        )
    } else {
        return Item(
            copyright = apodData.copyright ?: "",
            date = stringToLocalDate(apodData.date!!),
            explanation = apodData.explanation ?: "",
            url = apodData.hdurl ?: "",
            media_type = MediaType.Image.type,
            service_version = apodData.service_version ?: "",
            title = apodData.title ?: "",
            urlImagePreview = apodData.url
        )
    }
}