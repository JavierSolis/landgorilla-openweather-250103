package landgorilla.javiersolis.i020124.openweather.data.repository

import landgorilla.javiersolis.i020124.openweather.data.sqlite.CityEntity

/**
 * Created by Javier J. Solis Flores on 3/01/25.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */
interface CityRepository {
    fun insertCities(cities: List<CityEntity>)
    fun searchCity(cityName: String): List<CityEntity>
}