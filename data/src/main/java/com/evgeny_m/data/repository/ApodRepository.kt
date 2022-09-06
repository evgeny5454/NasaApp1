package com.evgeny_m.data.repository

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.evgeny_m.data.R
import com.evgeny_m.data.apod_api.ApodApi
import com.evgeny_m.data.model.ApodData
import com.evgeny_m.data.utils.apodToItem
import com.evgeny_m.domain.model.Item
import com.evgeny_m.domain.repository.Repository
import java.time.LocalDate
import kotlin.coroutines.coroutineContext

private const val DAYS_T0_SUBTRACT_59: Long = 59
private const val DAYS_T0_SUBTRACT_19: Long = 19
private const val ONE_DAY: Long = 1

class ApodRepository(context: Context) : Repository {

    @RequiresApi(Build.VERSION_CODES.O)
    private var localDate = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)
    private var endDate = localDate


    private val apiKey: String = context.getString(R.string.nasa_api_key)

    private lateinit var startDate: LocalDate


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getData(): Item? {
        val response = ApodApi.api.getImageData(apiKey, localDate.toString())
        return if (response.code() != 400) {
            val item = response.body()
            item?.let {
                apodToItem(it)
            }
        } else {
            localDate = localDate.minusDays(ONE_DAY)
            getData()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getArrayImages(date: LocalDate?): List<Item> {
        val result = mutableListOf<Item>()

        if (date == null) {
            endDate = localDate
            startDate = endDate.minusDays(DAYS_T0_SUBTRACT_59)
        } else {
            endDate = date.minusDays(ONE_DAY)
            startDate = endDate.minusDays(DAYS_T0_SUBTRACT_19)
        }

        try {
            val response = ApodApi.api
                .getArrayImagesData(
                    api_key = apiKey,
                    start_date = startDate.toString(),
                    end_date = endDate.toString()
                )
            if (response.code() != 400) {
                val list: List<ApodData>? = response.body()
                val sortedList = list?.sortedBy { it.date }
                val reversedList = sortedList?.reversed()
                Log.d("RESPONSE_LIST", reversedList.toString())
                Log.d("getArrayImagesFUN", "response = $response")
                reversedList?.forEach {
                    Log.d("getArrayImagesFUN", "item = $it")
                    result.add(apodToItem(it))
                }

            } else if (response.code() != 429) {
                //not implemented
            } else {
                endDate = endDate.minusDays(ONE_DAY)
                return getArrayImages(endDate)
            }

        } catch (e: Exception) {
            endDate = endDate.minusDays(ONE_DAY)
            return getArrayImages(endDate)
        }
        return result
    }
}