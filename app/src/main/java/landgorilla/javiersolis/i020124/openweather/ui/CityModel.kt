package landgorilla.javiersolis.i020124.openweather.ui

/**
 * Created by Javier J. Solis Flores on 3/01/25.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */
data class CityModel(
    val id: Int,
    val name: String,
    val country: String,
    val temperature: Double,
    val description: String,
    val icon: String,
    val countryFlag: String
)