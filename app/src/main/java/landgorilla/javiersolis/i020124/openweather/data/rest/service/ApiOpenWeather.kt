package landgorilla.javiersolis.i020124.openweather.data.rest.service

import landgorilla.javiersolis.i020124.openweather.data.rest.dto.find.FindResponseDto
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Javier J. Solis Flores on 3/01/25.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */
interface ApiOpenWeather {
    companion object {
        const val uriFind = "find"
    }

    //https://api.openweathermap.org/data/2.5/find?q=lima&type=like&sort=population&cnt=30&appid=API_KEY
    @GET(uriFind)
    fun find(
        @Query("q") query: String,
        @Query("type") type: String = "like",
        @Query("sort") sort: String = "population",
        @Query("cnt") cnt: Int = 30,
        @Query("appid") appId: String
    ): Call<FindResponseDto>

}