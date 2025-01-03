package landgorilla.javiersolis.i020124.openweather.data.rest.service

import android.content.Context
import android.util.Log

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import landgorilla.javiersolis.i020124.openweather.BuildConfig
import landgorilla.javiersolis.i020124.openweather.utils.NetworkUtils
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance{

    companion object{

        private fun getClient(context: Context) = OkHttpClient.Builder()
            .callTimeout(60,TimeUnit.SECONDS)
            .connectTimeout(60,TimeUnit.SECONDS)
            .writeTimeout(60,TimeUnit.SECONDS)
            .readTimeout(60,TimeUnit.SECONDS)
            .cache(Cache(context.cacheDir, 10 * 1024 * 1024)) // 10 MB
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (NetworkUtils.isConnectedToNetwork(context)) {
                    // Si hay conexión a Internet, establecer la cabecera Cache-Control para solicitar datos frescos al servidor
                    request.newBuilder().build()
                } else {
                    // Si no hay conexión a Internet, establecer la cabecera Cache-Control para usar datos en cache
                    request.newBuilder().header(
                        "Cache-Control",
                        "max-stale=14400"
                    ) // Usar datos en cache por un máximo de 4 horas (14400 segundos)
                        .build()
                }
                chain.proceed(request)
            }
            .apply {
                val interceptorLOG = run {
                    val httpLoggingInterceptor = HttpLoggingInterceptor()
                    httpLoggingInterceptor.apply {
                        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    }
                }
                addInterceptor(interceptorLOG)

            }.build()

        private fun getRetrofit(context: Context):Retrofit{
            //System.out.println(project.ext.myprop + " " + project.ext.myversion)
            //println("Api que se consulta : $"+project.ext.myprop );
            val BASE_URL = BuildConfig.BASE_URL //""// ConstantSystemProperty.getBaseUrl()!!
            Log.i("BASE_URL", BASE_URL)
            val moshi = Moshi
                .Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(
                    getClient(
                        context
                    )
                )
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        private var API_OPEN_WEATHER: ApiOpenWeather?=null

        fun getApiOld(context: Context): ApiOpenWeather {
            if(API_OPEN_WEATHER ==null){
                API_OPEN_WEATHER = getRetrofit(
                    context
                )
                    .create(ApiOpenWeather::class.java)
            }
            return API_OPEN_WEATHER!!
        }
        fun getApi(context: Context): ApiOpenWeather {
            return getRetrofit(context).create(ApiOpenWeather::class.java)
        }

        //public val API_LIMON : ApiLimon by lazy { retrofit.create(ApiLimon::class.java)}
    }
}