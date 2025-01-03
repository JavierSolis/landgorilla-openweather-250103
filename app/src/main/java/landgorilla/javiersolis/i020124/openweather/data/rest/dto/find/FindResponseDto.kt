package landgorilla.javiersolis.i020124.openweather.data.rest.dto.find


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FindResponseDto(
    /**
    @Json(name = "cod")
    val cod: String,*/
    @Json(name = "count")
    val count: Int,
    @Json(name = "list")
    val list: List<FindResponseItemDto>,
    @Json(name = "message")
    val message: String
)