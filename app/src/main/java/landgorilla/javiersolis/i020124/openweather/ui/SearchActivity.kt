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
import landgorilla.javiersolis.i020124.openweather.utils.UtilKeyboard

import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Javier J. Solis Flores on 2/01/25.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */
class SearchActivity : AppCompatActivity(){
    private lateinit var binding: SearchBinding

    var vm: SearchViewModel? = null
    val viewModel: SearchViewModel by viewModel()

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
                    //nothing
                }
            }
        }
    }

    fun events(){
        binding.searchEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(v.text.toString())
                UtilKeyboard.hideKeyboard(v)
                true
            } else {
                false
            }
        }
    }

    private fun performSearch(query: String) {
        vm?.searchWeather(query)
    }

    private fun displayWeatherData(dataCities: List<CityModel>) {
        val adapter = CitiesAdapter(dataCities)
        binding.searchResultsRv.layoutManager = LinearLayoutManager(this)
        binding.searchResultsRv.adapter = adapter
    }
}