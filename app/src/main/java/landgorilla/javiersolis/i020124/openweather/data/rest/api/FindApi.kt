package landgorilla.javiersolis.i020124.openweather.data.rest.api

import landgorilla.javiersolis.i020124.openweather.data.repository.OWFindRepository
import landgorilla.javiersolis.i020124.openweather.data.rest.dto.find.FindResponseDto
import landgorilla.javiersolis.i020124.openweather.data.rest.service.ApiOpenWeather

/**
 * Created by Javier J. Solis Flores on 3/01/25.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */
class FindApi(
    private val api: ApiOpenWeather,
    private val apiKey: String
) : OWFindRepository {


    override suspend fun findCity(q: String): FindResponseDto {
        return api.find(
            query = q, appId = apiKey
        ).execute().body()!!
    }


}