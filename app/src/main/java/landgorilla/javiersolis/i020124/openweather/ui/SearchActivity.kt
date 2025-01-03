package landgorilla.javiersolis.i020124.openweather.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import landgorilla.javiersolis.i020124.openweather.BuildConfig
import landgorilla.javiersolis.i020124.openweather.databinding.SearchBinding

import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Javier J. Solis Flores on 2/01/25.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */
class SearchActivity : AppCompatActivity(){

    private lateinit var binding: SearchBinding
    private val vm: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.versionName.text = BuildConfig.VERSION_NAME
    }
}