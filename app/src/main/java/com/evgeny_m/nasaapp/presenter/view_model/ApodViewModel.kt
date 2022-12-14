package com.evgeny_m.nasaapp.presenter.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evgeny_m.domain.cash.CashItems
import com.evgeny_m.domain.model.Item
import com.evgeny_m.domain.use_case.DownloadImageByUrlUseCase
import com.evgeny_m.domain.use_case.GetImageByDateUseCase
import com.evgeny_m.domain.use_case.GetListOfImagesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class ApodViewModel(
    private val downloadImageDataUseCase: GetImageByDateUseCase,
    private val getListOfImagesUseCase: GetListOfImagesUseCase,
    private val downloadImageByUrlUseCase: DownloadImageByUrlUseCase
) : ViewModel() {
    private val _cashList = MutableLiveData<List<Item>>()
    val cashList: LiveData<List<Item>> = _cashList

    private val _item = MutableLiveData<Item>(null)
    val item: LiveData<Item> = _item

    private val _archiveList = MutableLiveData<List<Item>>()
    val archiveList: LiveData<List<Item>> = _archiveList

    private val _stateAdapter = MutableLiveData<Boolean>()
    val stateAdapter: LiveData<Boolean> = _stateAdapter


    fun downloadItem() {
        viewModelScope.launch(Dispatchers.IO) {
            _item.postValue(downloadImageDataUseCase.execute())
        }
    }


    fun downloadArchiveList(date: LocalDate?) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = getListOfImagesUseCase.execute(date = date)
            _archiveList.postValue(list)
        }
    }

    fun getCashList() {
        _cashList.postValue(CashItems.getList())
    }

    fun setState(boolean: Boolean) {
        _stateAdapter.postValue(boolean)
    }

    fun downloadImage(url: String, title: String) {
        downloadImageByUrlUseCase.execute(url, title)
    }
}