package com.evgeny_m.data.repository

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.evgeny_m.domain.repository.DownloadRepositoryImpl
import java.io.File

class DownloadRepository(private val context: Context) : DownloadRepositoryImpl {

    @SuppressLint("WrongConstant")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun downloadImage(uri: String, title: String) {
        try {
            val downloadManager =
                context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val imageLink = Uri.parse(uri)
            val request = DownloadManager.Request(imageLink)
                .setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_MOBILE
                            or DownloadManager.Request.NETWORK_WIFI
                )
                .setMimeType("image/jpeg")
                .setAllowedOverRoaming(false)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setTitle(title)
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_PICTURES,
                    File.separator + title + ".jpg"
                )

            downloadManager.enqueue(request)
            Toast.makeText(context, "Download Start", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Toast.makeText(context, "Loading Failed", Toast.LENGTH_SHORT).show()
        }
    }
}