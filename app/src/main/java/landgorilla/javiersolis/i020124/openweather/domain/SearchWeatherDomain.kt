package landgorilla.javiersolis.i020124.openweather.domain

import android.util.Log
import landgorilla.javiersolis.i020124.openweather.data.repository.CityRepository
import landgorilla.javiersolis.i020124.openweather.data.repository.OWFindRepository
import landgorilla.javiersolis.i020124.openweather.data.sqlite.CityDBA
import landgorilla.javiersolis.i020124.openweather.data.sqlite.CityEntity
import landgorilla.javiersolis.i020124.openweather.ui.CityModel

/**
 * Created by Javier J. Solis Flores on 3/01/25.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */
class SearchWeatherDomain(
    private val owFindRepository: OWFindRepository,
    private val cityRepository: CityRepository
) {

    suspend fun execute(countryName: String): Result<List<CityModel>> {
        Log.i("MOCKKK", "Domain: $countryName")

        return try {
            val weatherData = owFindRepository.findCity(countryName)
            if (weatherData.count>0) {
                Log.i("SearchWeatherDomain", "execute: ${weatherData.list}")
                cityRepository.insertCities(weatherData.list.map {
                    CityEntity(
                        id = it.id,
                        name = it.name,
                        country = it.sys.country,
                        temperature =  it.main.temp, // Kelvin to Celsius
                        description = it.weather[0].description,
                        icon = it.weather[0].icon,
                    )
                })
                Result.success(weatherData.list.map {
                    CityModel(
                        id = it.id,
                        name = it.name,
                        country = it.sys.country,
                        temperature =  it.main.temp, // Kelvin to Celsius
                        description = it.weather[0].description,
                        icon = it.weather[0].icon,
                        countryFlag = it.sys.country.lowercase()
                    )
                })
            } else {
                Result.failure(Exception("Data no encontrada"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}