package landgorilla.javiersolis.i020124.openweather.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import landgorilla.javiersolis.i020124.openweather.BuildConfig
import landgorilla.javiersolis.i020124.openweather.R
import landgorilla.javiersolis.i020124.openweather.databinding.SearchBinding

import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Javier J. Solis Flores on 2/01/25.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */
class SearchActivity : AppCompatActivity(){
    private lateinit var binding: SearchBinding

    // Propiedad para pruebas
    var vm: SearchViewModel? = null
    // Usado en producción, se obtiene a través de Hilt
    val viewModel: SearchViewModel by viewModel()

    //val vm: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = vm ?: viewModel

        setVersionName()
        suscribe()
        events()

    }

    fun setVersionName(){
        binding.versionName.text = BuildConfig.VERSION_NAME
    }

    fun suscribe(){
        vm?.getState()?.observe(this) {
            when (it) {
                is SearchState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.searchResultsRv.visibility = View.GONE
                    binding.emptyText.visibility = View.GONE
                }
                is SearchState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.searchResultsRv.visibility = View.VISIBLE
                    binding.emptyText.visibility = View.GONE
                    displayWeatherData(it.data)
                }
                is SearchState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.searchResultsRv.visibility = View.GONE
                    binding.emptyText.text = it.message
                    binding.emptyText.visibility = View.VISIBLE
                }

                is SearchState.Empty -> {
                    binding.progressBar.visibility = View.GONE
                    binding.searchResultsRv.visibility = View.GONE
                    binding.emptyText.setText(R.string.search_dont_found)
                    binding.emptyText.visibility = View.VISIBLE
                }
                SearchState.Initial -> {

                }
            }
        }        // Observando el estado de la búsqueda
    }

    fun events(){
        binding.searchEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // Aquí se captura cuando se presiona la tecla "Buscar" (actionSearch)
                // Puedes realizar la búsqueda o llamar a cualquier método necesario
                performSearch(v.text.toString())
                true // Retorna true para indicar que has manejado el evento
            } else {
                false // Retorna false si el evento no es para la acción de búsqueda
            }
        }
    }

    private fun performSearch(query: String) {
        // Aquí puedes manejar la lógica de búsqueda
        // Por ejemplo, llamar al ViewModel para hacer la búsqueda
        vm?.searchWeather(query)
    }

    private fun displayWeatherData(dataCities: List<CityModel>) {
        val adapter = CitiesAdapter(dataCities)
        binding.searchResultsRv.layoutManager = LinearLayoutManager(this)
        binding.searchResultsRv.adapter = adapter
    }
}