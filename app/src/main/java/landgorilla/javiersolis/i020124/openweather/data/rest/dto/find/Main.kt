package landgorilla.javiersolis.i020124.openweather.data.rest.dto.find


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Main(
    @Json(name = "temp")
    val temp: Double,

    /**
    @Json(name = "feels_like")
    val feelsLike: Double,
    @Json(name = "grnd_level")
    val grndLevel: Int,
    @Json(name = "humidity")
    val humidity: Int,
    @Json(name = "pressure")
    val pressure: Int,
    @Json(name = "sea_level")
    val seaLevel: Int,
    @Json(name = "temp_max")
    val tempMax: Double,
    @Json(name = "temp_min")
    val tempMin: Double
    */
)