package landgorilla.javiersolis.i020124.openweather.ui

/**
 * Created by Javier J. Solis Flores on 3/01/25.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */
sealed class SearchState {
    object Initial : SearchState()           // Estado cuando entro por primera vez a la pantalla
    object Loading : SearchState()           // Estado cuando está cargando
    data class Success(val data: List<CityModel>) : SearchState()   // Estado de éxito con datos
    data class Error(val message: String) : SearchState()       // Estado de error con mensaje
    object Empty : SearchState()            // Estado cuando no hay datos
}