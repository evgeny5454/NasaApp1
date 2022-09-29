package com.evgeny_m.nasaapp.presenter.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.evgeny_m.data.repository.ApodRepository
import com.evgeny_m.data.repository.DownloadRepository
import com.evgeny_m.domain.use_case.DownloadImageByUrlUseCase
import com.evgeny_m.domain.use_case.GetImageByDateUseCase
import com.evgeny_m.domain.use_case.GetListOfImagesUseCase

@Suppress("UNCHECKED_CAST")
class ApodViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val apodRepository by lazy(LazyThreadSafetyMode.NONE) {
        ApodRepository(context)
    }

    private val downloadImageDataUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetImageByDateUseCase(apodRepository)
    }

    private val getListOfImagesUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetListOfImagesUseCase(apodRepository)
    }


    private val downloadRepository by lazy(LazyThreadSafetyMode.NONE) {
        DownloadRepository(context)
    }

    private val downloadImageByUrlUseCase by lazy(LazyThreadSafetyMode.NONE) {
        DownloadImageByUrlUseCase(downloadRepository)
    }


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ApodViewModel(
            downloadImageDataUseCase = downloadImageDataUseCase,
            getListOfImagesUseCase = getListOfImagesUseCase,
            downloadImageByUrlUseCase = downloadImageByUrlUseCase
        ) as T
    }
}