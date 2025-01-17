package landgorilla.javiersolis.i020124.openweather

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import landgorilla.javiersolis.i020124.openweather.ui.SearchActivity

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by Javier J. Solis Flores on 3/01/25.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */
@RunWith(AndroidJUnit4::class)
//@LargeTest
class HelloWorldEspressoTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(SearchActivity::class.java)

    @Test fun searchInputPresent() {
        onView(withHint("Escriba aquí el lugar a buscar")).check(matches(isDisplayed()))
    }
}
