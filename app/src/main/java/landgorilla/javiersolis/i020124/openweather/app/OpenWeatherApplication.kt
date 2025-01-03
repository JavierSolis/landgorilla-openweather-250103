package landgorilla.javiersolis.i020124.openweather.app

import android.app.Application
import org.koin.core.context.startKoin
import org.koin.dsl.module
import landgorilla.javiersolis.i020124.openweather.ui.SearchViewModel

val appModule = module {
    factory { SearchViewModel() }
}

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
            //androidContext(this@OpenWeatherApplication)
            modules(appModule)
        }
    }
}