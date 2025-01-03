package landgorilla.javiersolis.i020124.openweather.data.repository

import landgorilla.javiersolis.i020124.openweather.data.rest.dto.find.FindResponseDto

/**
 * Created by Javier J. Solis Flores on 3/01/25.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */
interface OWFindRepository {
    suspend fun findCity(q:String): FindResponseDto
}