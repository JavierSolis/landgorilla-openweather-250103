package landgorilla.javiersolis.i020124.openweather.data.sqlite

import landgorilla.javiersolis.i020124.openweather.data.repository.CityRepository

/**
 * Created by Javier J. Solis Flores on 3/01/25.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */

class CityDBA(private val appDataBase: AppDatabase):CityRepository {
    override fun insertCities(cities: List<CityEntity>) {
        appDataBase.cityDao().insertCities(cities)
    }

    override fun searchCity(cityName: String): List<CityEntity> {
        return appDataBase.cityDao().searchCities(cityName)
    }
}