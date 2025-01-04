package landgorilla.javiersolis.i020124.openweather.data.sqlite

/**
 * Created by Javier J. Solis Flores on 3/01/25.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_table")
data class CityEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val country: String,
    val temperature: Double,
    val description: String,
    val icon: String

    //val country: String,
    //val lat: Double,
    //val lon: Double,
    //val temperature: Double,
    //val feelsLike: Double,
    //val minTemperature: Double,
    //val maxTemperature: Double,
    //val pressure: Int,
    //val humidity: Int,
    //val weatherMain: String,
    //val weatherDescription: String,
    //val icon: String
)
