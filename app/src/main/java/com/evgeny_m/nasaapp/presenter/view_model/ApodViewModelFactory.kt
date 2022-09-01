package com.evgeny_m.nasaapp.presenter.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.evgeny_m.data.repository.ApodRepository
import com.evgeny_m.domain.use_case.GetImageByDateUseCase
import com.evgeny_m.domain.use_case.GetListOfImagesUseCase

@Suppress("UNCHECKED_CAST")
class ApodViewModelFactory(context: Context): ViewModelProvider.Factory {

    private val repository by lazy(LazyThreadSafetyMode.NONE) {
        ApodRepository(context)
    }

    private val downloadImageDataUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetImageByDateUseCase(repository)
    }

    private val getListOfImagesUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetListOfImagesUseCase(repository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ApodViewModel(
            downloadImageDataUseCase = downloadImageDataUseCase,
            getListOfImagesUseCase = getListOfImagesUseCase
        ) as T
    }
}