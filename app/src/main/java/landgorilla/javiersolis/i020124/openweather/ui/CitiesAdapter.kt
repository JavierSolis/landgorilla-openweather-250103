package landgorilla.javiersolis.i020124.openweather.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import landgorilla.javiersolis.i020124.openweather.R

/**
 * Created by Javier J. Solis Flores on 3/01/25.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */


class CitiesAdapter(private val cities: List<CityModel>) : RecyclerView.Adapter<CitiesAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.bind(city)
    }

    override fun getItemCount(): Int = cities.size

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cityName: TextView = itemView.findViewById(R.id.city_name)
        private val country: TextView = itemView.findViewById(R.id.country)
        private val temperature: TextView = itemView.findViewById(R.id.temperature)
        private val weatherDescription: TextView = itemView.findViewById(R.id.weather_description)
        private val weatherIcon: ImageView = itemView.findViewById(R.id.weather_icon)
        private val flagIcon: ImageView = itemView.findViewById(R.id.country_flag)


        fun bind(city: CityModel) {
            cityName.text = city.name
            country.text = city.country
            temperature.text =  String.format("%.1f°C", city.temperature-273.15)
            weatherDescription.text = city.description

            Glide.with(itemView.context)
                .load("https://openweathermap.org/img/wn/${city.icon}@2x.png")
                .into(weatherIcon)
            Glide.with(itemView.context)
                .load("https://openweathermap.org/images/flags/${city.countryFlag}.png")
                .into(flagIcon)
        }
    }
}
