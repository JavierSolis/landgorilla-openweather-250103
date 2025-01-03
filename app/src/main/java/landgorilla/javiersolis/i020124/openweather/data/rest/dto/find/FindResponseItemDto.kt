package landgorilla.javiersolis.i020124.openweather.data.rest.dto.find


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FindResponseItemDto(
    @Json(name = "clouds")
    val clouds: Clouds,
    @Json(name = "coord")
    val coord: landgorilla.javiersolis.i020124.openweather.data.rest.dto.find.Coord,
    @Json(name = "dt")
    val dt: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "main")
    val main: landgorilla.javiersolis.i020124.openweather.data.rest.dto.find.Main,
    @Json(name = "name")
    val name: String,
    @Json(name = "rain")
    val rain: Rain,
    @Json(name = "snow")
    val snow: Snow,
    @Json(name = "sys")
    val sys: Sys,
    @Json(name = "weather")
    val weather: List<landgorilla.javiersolis.i020124.openweather.data.rest.dto.find.Weather>,
    @Json(name = "wind")
    val wind: Wind
)