package landgorilla.javiersolis.i020124.openweather

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
import androidx.test.ext.junit.rules.ActivityScenarioRule
import landgorilla.javiersolis.i020124.openweather.ui.SearchActivity
import landgorilla.javiersolis.i020124.openweather.ui.SearchViewModel

@RunWith(AndroidJUnit4::class)
class SearchActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(SearchActivity::class.java)

    private lateinit var mockViewModel: SearchViewModel

    @Before
    fun setUp() {
        // Inicializamos el ViewModel mockeado
        mockViewModel = mockk()

        // Creamos un LiveData simulado para los resultados de búsqueda
        val mockLiveData = MutableLiveData<List<String>>()
        mockLiveData.value = listOf("Resultado 1", "Resultado 2")

        // Simulamos que el ViewModel devuelve los resultados de la búsqueda
        //every { mockViewModel.searchResults } returns mockLiveData
        //every { mockViewModel.loadingState } returns MutableLiveData(false)

        // Configuramos el ViewModel en el Activity
        activityRule.scenario.onActivity { activity ->
            activity.vm = mockViewModel
        }
    }

    @Test
    fun testSearchFunctionality() {
        // Simulamos que el usuario escribe una consulta
        onView(withId(R.id.search_edit_text))
            .perform(typeText("query"), closeSoftKeyboard())  // Escribir "query"

        // Simulamos que el usuario presiona el botón de buscar
        onView(withId(R.id.search_input_layout))
            .perform(pressImeActionButton())  // Enviar la búsqueda

        // Verificamos que el ViewModel se llama con la consulta
        //verify { mockViewModel.search("query") }

        // Verificamos que los resultados son visibles en la UI
       // onView(withId(R.id.search_results_rv))
        //   .check(matches(isDisplayed()))  // Verificar que el RecyclerView es visible

        // Verificamos que los resultados fueron correctamente mostrados
        //onView(withText("Resultado 1")).check(matches(isDisplayed()))
        //onView(withText("Resultado 2")).check(matches(isDisplayed()))

        // Verificamos que el ProgressBar no es visible (no está en estado de carga)
            //onView(withId(R.id.progress_bar))
        //.check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @After
    fun tearDown() {
        // Limpiar los mocks
        clearMocks(mockViewModel)
    }
}
