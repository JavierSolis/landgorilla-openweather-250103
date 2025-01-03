package landgorilla.javiersolis.i020124.openweather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import landgorilla.javiersolis.i020124.openweather.domain.SearchWeatherDomain

fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { postValue (initialValue) }

/**
 * Created by Javier J. Solis Flores on 2/01/25.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */
class SearchViewModel(private val searchWeatherUseCase: SearchWeatherDomain):ViewModel() {

    private val _searchState = MutableLiveData<SearchState>().default(SearchState.Initial)
    fun getState() = _searchState as LiveData<SearchState>

    fun searchWeather(country: String) {
        _searchState.value = SearchState.Loading   // Cambiar el estado a Loading

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                searchWeatherUseCase.execute(country)
            }
            //val result = searchWeatherUseCase.execute(country)

            if (result.isSuccess) {
                _searchState.value = SearchState.Success(result.getOrNull()!!)
            } else {
                _searchState.value = SearchState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }
}