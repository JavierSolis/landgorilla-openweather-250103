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
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "country") val country: String,
    val temperature: Double,
    val description: String,
    val icon: String

)
