package landgorilla.javiersolis.i020124.openweather.data.rest.dto.find


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FindResponseItemDto(

    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,

    @Json(name = "weather")
    val weather: List<Weather>,


    /**
    @Json(name = "main")
    val main: Main,
    @Json(name = "clouds")
    val clouds: Clouds,
    @Json(name = "coord")
    val coord: Coord,
    @Json(name = "dt")
    val dt: Int,
    @Json(name = "rain")
    val rain: Rain,
    @Json(name = "snow")
    val snow: Snow,
    @Json(name = "sys")
    val sys: Sys,
    @Json(name = "wind")
    val wind: Wind
     */
)