package com.evgeny_m.nasaapp.presenter.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evgeny_m.domain.model.Item
import com.evgeny_m.domain.use_case.GetImageByDateUseCase
import com.evgeny_m.domain.use_case.GetListOfImagesUseCase
import com.evgeny_m.nasaapp.presenter.CashList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class ApodViewModel(
    private val downloadImageDataUseCase: GetImageByDateUseCase,
    private val getListOfImagesUseCase: GetListOfImagesUseCase
) : ViewModel() {
    private var cash = CashList
    private val _cashList = MutableLiveData<List<Item>>()
    val cashList: LiveData<List<Item>> = _cashList

    private val _item = MutableLiveData<Item>(null)
    val item: LiveData<Item> = _item


    private val _archiveList = MutableLiveData<List<Item>>()
    val archiveList: LiveData<List<Item>> = _archiveList


    fun downloadItem() {
        viewModelScope.launch(Dispatchers.IO) {
            _item.postValue(downloadImageDataUseCase.execute())
        }
    }


    fun downloadArchiveList(date: LocalDate?) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = getListOfImagesUseCase.execute(date = date)
            _archiveList.postValue(list)
            cash.putList(list)
            _cashList.postValue(cash.getList())
            Log.d("CASHLIST", cash.getList().toString())
        }
    }

    fun getCashList()  {
        Log.d("CASHLIST", cash.getList().toString())
        _cashList.postValue(cash.getList())
    }

}