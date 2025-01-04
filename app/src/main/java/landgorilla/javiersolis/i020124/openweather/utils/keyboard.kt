package landgorilla.javiersolis.i020124.openweather.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by Javier J. Solis Flores on 4/01/25.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */

object UtilKeyboard{
    fun hideKeyboard(view: View) {
        val inputMethodManager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
