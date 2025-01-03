package landgorilla.javiersolis.i020124.openweather.data.sqlite


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Javier J. Solis Flores on 3/01/25.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */

@Dao
interface CityDao {
    @Query("SELECT * FROM CITY_TABLE WHERE name LIKE '%' || :query || '%'")
    suspend fun searchCities(query: String): List<CityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(cities: List<CityEntity>)
}