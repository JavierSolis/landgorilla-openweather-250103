package landgorilla.javiersolis.i020124.openweather.app

import android.app.Application
import landgorilla.javiersolis.i020124.openweather.BuildConfig
import landgorilla.javiersolis.i020124.openweather.data.repository.OWFindRepository
import landgorilla.javiersolis.i020124.openweather.data.rest.api.FindApi
import landgorilla.javiersolis.i020124.openweather.data.rest.service.ApiOpenWeather
import landgorilla.javiersolis.i020124.openweather.data.rest.service.RetrofitInstance
import landgorilla.javiersolis.i020124.openweather.domain.SearchWeatherDomain
import org.koin.core.context.startKoin
import org.koin.dsl.module
import landgorilla.javiersolis.i020124.openweather.ui.SearchViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import retrofit2.Retrofit

val appModule = module {
    //api
    //single { OkHttpClient.Builder().build() }
    single<ApiOpenWeather>{RetrofitInstance.getApi(get())}
    //single { get<Retrofit>().create(ApiOpenWeather::class.java) }
    //data
    factory<OWFindRepository> { FindApi(get(),BuildConfig.API_KEY) }

    factory { SearchWeatherDomain(get()) }
    factory { SearchViewModel(get()) }
}


/**
 * val repositoryModule = module {
 *     single<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }
 * }
 *
 * val databaseModule = module {
 *     single { Room.databaseBuilder(get(), WeatherDatabase::class.java, "weather.db").build() }
 *     single { get<WeatherDatabase>().weatherDao() }
 * }
 *
 * val viewModelModule = module {
 *     viewModel { MainViewModel(get()) }
 * }
 */


/**
 * Created by Javier J. Solis Flores on 2/01/25.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */
class OpenWeatherApplication:Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            //androidLogger()
            androidContext(this@OpenWeatherApplication)
            modules(appModule)
        }
    }
}