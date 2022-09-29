package com.evgeny_m.domain.use_case

import com.evgeny_m.domain.model.Item
import com.evgeny_m.domain.repository.ApodRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate

class TestRepository : ApodRepositoryImpl {
    override suspend fun getData(): Item? {
        TODO("Not yet implemented")
    }

    override suspend fun getArrayImages(date: LocalDate?): List<Item> {
        val list = mutableListOf<Item>()
        for (i in 1..10) {
            list.add(
                Item(
                    copyright = "$i",
                    date = LocalDate.now(),
                    explanation = "$i",
                    url = "$i",
                    media_type = "$i",
                    service_version = "$i",
                    title = "$i",
                    urlImagePreview = "$i"
                )
            )
        }
        return list
    }
}


class GetListOfImagesUseCaseTest {

    private val testRepository = TestRepository()
    private val getListOfImagesUseCase = GetListOfImagesUseCase(repository = testRepository)


    @Test
    fun `should return list of items`() = runBlocking {
        val actual = mutableListOf<Item>()
        for (i in 1..10) {
            actual.add(
                Item(
                    copyright = "$i",
                    date = LocalDate.now(),
                    explanation = "$i",
                    url = "$i",
                    media_type = "$i",
                    service_version = "$i",
                    title = "$i",
                    urlImagePreview = "$i"
                )
            )
        }
        val expected = getListOfImagesUseCase.execute(null)
        Assertions.assertEquals(expected, actual)
    }
}
