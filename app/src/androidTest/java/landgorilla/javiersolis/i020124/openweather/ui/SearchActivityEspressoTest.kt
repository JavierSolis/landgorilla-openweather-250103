package landgorilla.javiersolis.i020124.openweather.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import landgorilla.javiersolis.i020124.openweather.R
import org.hamcrest.Matchers.not


@RunWith(AndroidJUnit4::class)
class SearchActivityEspressoTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(SearchActivity::class.java)
    private lateinit var mockViewModel: SearchViewModel
    val liveData = MutableLiveData<SearchState>()

    @Before
    fun setUp() {
        // Inicializamos el ViewModel mockeado
        mockViewModel = mockk()

        every { mockViewModel.getState() } returns liveData
        activityRule.scenario.onActivity { activity ->
            activity.vm = mockViewModel
            activity.suscribe()
        }
    }

    @Test
    fun `search whit result success`() {
        every { mockViewModel.searchWeather("Lima") } answers {
            Log.e("TESTT","mockkoo")
            val listCities = listOf(
                CityModel(1,"Lima1","",12.0,"","",""),
                CityModel(2,"Lima2","",12.0,"","",""),
                CityModel(3,"Lima3","",12.0,"","",""),
            )
            liveData.postValue(SearchState.Success(data=listCities))
        }


        onView(withId(R.id.search_edit_text))
            .perform(typeText("Lima"))  // Escribir "query"

        // Simulamos que el usuario presiona el botón de buscar
        onView(withId(R.id.search_edit_text))
            .perform(pressImeActionButton(), closeSoftKeyboard())  // Enviar la búsqueda

        // Verificamos que el ViewModel se llama con la consulta
        verify { mockViewModel.searchWeather("Lima") }

        runBlocking { delay(3000) }

        // Verificamos que los resultados son visibles en la UI
        onView(withId(R.id.search_results_rv))
           .check(matches(isDisplayed()))  // Verificar que el RecyclerView es visible

        // Verificamos que los resultados fueron correctamente mostrados
        onView(withText("Lima1")).check(matches(isDisplayed()))
        onView(withText("Lima2")).check(matches(isDisplayed()))
        onView(withText("Lima3")).check(matches(isDisplayed()))

        // Verificamos que el ProgressBar no es visible (no está en estado de carga)
        onView(withId(R.id.progress_bar))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.empty_text))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.search_edit_text))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }


    @Test
    fun `search whit result empty`() {
        every { mockViewModel.searchWeather("Lima Peru") } answers {
            liveData.postValue(SearchState.Empty)
        }


        onView(withId(R.id.search_edit_text))
            .perform(typeText("Lima Peru"))  // Escribir "query"

        // Simulamos que el usuario presiona el botón de buscar
        onView(withId(R.id.search_edit_text))
            .perform(pressImeActionButton(), closeSoftKeyboard())  // Enviar la búsqueda

        // Verificamos que el ViewModel se llama con la consulta
        verify { mockViewModel.searchWeather("Lima Peru") }

        runBlocking { delay(3000) }

        // Verificamos que los resultados son visibles en la UI
        onView(withId(R.id.search_results_rv))
            .check(matches(not(isDisplayed())))


        var textEmpty = ""
        activityRule.scenario.onActivity { activity ->
            textEmpty = activity.getString(R.string.search_dont_found)
        }

        // Verificamos que los resultados fueron correctamente mostrados
        onView(withText(textEmpty)).check(matches( isDisplayed() ))

        // Verificamos que el ProgressBar no es visible (no está en estado de carga)
        onView(withId(R.id.progress_bar))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.empty_text))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.search_edit_text))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }



    @After
    fun tearDown() {
        // Limpiar los mocks
        clearMocks(mockViewModel)
    }
}
