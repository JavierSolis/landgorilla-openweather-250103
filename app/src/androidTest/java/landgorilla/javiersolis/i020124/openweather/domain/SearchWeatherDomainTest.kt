package landgorilla.javiersolis.i020124.openweather.domain

import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import landgorilla.javiersolis.i020124.openweather.data.repository.OWFindRepository
import landgorilla.javiersolis.i020124.openweather.data.rest.dto.find.FindResponseDto
import landgorilla.javiersolis.i020124.openweather.data.rest.dto.find.FindResponseItemDto
import landgorilla.javiersolis.i020124.openweather.data.rest.dto.find.Main
import landgorilla.javiersolis.i020124.openweather.data.rest.dto.find.Sys
import landgorilla.javiersolis.i020124.openweather.data.rest.dto.find.Weather
import landgorilla.javiersolis.i020124.openweather.data.sqlite.AppDatabase
import landgorilla.javiersolis.i020124.openweather.data.sqlite.CityDBA
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Javier J. Solis Flores on 3/01/25.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */
@RunWith(AndroidJUnit4::class)
class SearchWeatherDomainTest {

    @Test
    fun `save Cities when Cities returned Successfully`() = runTest {
        val owFindRepository:OWFindRepository = mockk()

        coEvery { owFindRepository.findCity("Lima") } answers {
            Log.i("MOCKKK", "findCity: Lima")
            FindResponseDto(
                count = 1,
                list = listOf(
                    FindResponseItemDto(
                        id = 1,
                        name = "Lima",
                        sys = Sys("PE"),
                        main = Main(300.0),
                        weather = listOf(Weather("Sunny", "01d"))
                    )
                ),
                message = "Success"
            )
        }

        val database = Room.databaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java,
            "weather_test.db"
        ).build()
        val cityDBA = CityDBA(database)
        val searchWeatherDomain = SearchWeatherDomain(owFindRepository, cityDBA)

        //
        searchWeatherDomain.execute("Lima")

        val result = cityDBA.searchCity("Lima")
        //assert
        assertEquals(1, result.size)
        assertEquals("Lima", result.first().name)
        assertEquals(1, result.first().id)
        assertEquals("PE", result.first().country)
        assertEquals("Sunny", result.first().description)
        assertEquals("01d", result.first().icon)
        assertEquals(300.toDouble(), result.first().temperature,0.0)
    }
}