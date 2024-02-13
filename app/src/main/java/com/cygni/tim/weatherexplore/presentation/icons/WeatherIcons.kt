package com.cygni.tim.weatherexplore.presentation.icons

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.DrawableRes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class WeatherIcons {
    @Serializable
    data class WeatherIconEntity(
        @SerialName("Symbol ID")
        val symbolId: String,
        @SerialName("English")
        val english: String,
        @SerialName("Variants")
        val variants: Int
    )

    data class WeatherIconData(
        @DrawableRes
        val resId: Int,
        val english: String,
    )

    companion object {

        private val json = Json {
            ignoreUnknownKeys = true
        }

        private val jsonString = """
        [
          {
            "Symbol ID": "clearsky",
            "English": "Clear sky",
            "Bokmål": "Klarvær",
            "Nynorsk": "Klårvêr",
            "Old ID": 1,
            "Variants": 1
          },
          {
            "Symbol ID": "fair",
            "English": "Fair",
            "Bokmål": "Lettskyet",
            "Nynorsk": "Lettskya",
            "Old ID": 2,
            "Variants": 1
          },
          {
            "Symbol ID": "partlycloudy",
            "English": "Partly cloudy",
            "Bokmål": "Delvis skyet",
            "Nynorsk": "Delvis skya",
            "Old ID": 3,
            "Variants": 1
          },
          {
            "Symbol ID": "cloudy",
            "English": "Cloudy",
            "Bokmål": "Skyet",
            "Nynorsk": "Skya",
            "Old ID": 4,
            "Variants": 0
          },
          {
            "Symbol ID": "lightrainshowers",
            "English": "Light rain showers",
            "Bokmål": "Lette regnbyger",
            "Nynorsk": "Lette regnbyer",
            "Old ID": 40,
            "Variants": 1
          },
          {
            "Symbol ID": "rainshowers",
            "English": "Rain showers",
            "Bokmål": "Regnbyger",
            "Nynorsk": "Regnbyer",
            "Old ID": 5,
            "Variants": 1
          },
          {
            "Symbol ID": "heavyrainshowers",
            "English": "Heavy rain showers",
            "Bokmål": "Kraftige regnbyger",
            "Nynorsk": "Kraftige regnbyer",
            "Old ID": 41,
            "Variants": 1
          },
          {
            "Symbol ID": "lightrainshowersandthunder",
            "English": "Light rain showers and thunder",
            "Bokmål": "Lette regnbyger og torden",
            "Nynorsk": "Lette regnbyer og torevêr",
            "Old ID": 24,
            "Variants": 1
          },
          {
            "Symbol ID": "rainshowersandthunder",
            "English": "Rain showers and thunder",
            "Bokmål": "Regnbyger og torden",
            "Nynorsk": "Regnbyer og torevêr",
            "Old ID": 6,
            "Variants": 1
          },
          {
            "Symbol ID": "heavyrainshowersandthunder",
            "English": "Heavy rain showers and thunder",
            "Bokmål": "Kraftige regnbyger og torden",
            "Nynorsk": "Kraftige regnbyer og torevêr",
            "Old ID": 25,
            "Variants": 1
          },
          {
            "Symbol ID": "lightsleetshowers",
            "English": "Light sleet showers",
            "Bokmål": "Lette sluddbyger",
            "Nynorsk": "Lette sluddbyer",
            "Old ID": 42,
            "Variants": 1
          },
          {
            "Symbol ID": "sleetshowers",
            "English": "Sleet showers",
            "Bokmål": "Sluddbyger",
            "Nynorsk": "Sluddbyer",
            "Old ID": 7,
            "Variants": 1
          },
          {
            "Symbol ID": "heavysleetshowers",
            "English": "Heavy sleet showers",
            "Bokmål": "Kraftige sluddbyger",
            "Nynorsk": "Kraftige sluddbyer",
            "Old ID": 43,
            "Variants": 1
          },
          {
            "Symbol ID": "lightssleetshowersandthunder",
            "English": "Light sleet showers and thunder",
            "Bokmål": "Lette sluddbyger og torden",
            "Nynorsk": "Lette sluddbyer og torevêr",
            "Old ID": 26,
            "Variants": 1
          },
          {
            "Symbol ID": "sleetshowersandthunder",
            "English": "Sleet showers and thunder",
            "Bokmål": "Sluddbyger og torden",
            "Nynorsk": "Sluddbyer og torevêr",
            "Old ID": 20,
            "Variants": 1
          },
          {
            "Symbol ID": "heavysleetshowersandthunder",
            "English": "Heavy sleet showers and thunder",
            "Bokmål": "Kraftige sluddbyger og torden",
            "Nynorsk": "Kraftige sluddbyer og torevêr",
            "Old ID": 27,
            "Variants": 1
          },
          {
            "Symbol ID": "lightsnowshowers",
            "English": "Light snow showers",
            "Bokmål": "Lette snøbyger",
            "Nynorsk": "Lette snøbyer",
            "Old ID": 44,
            "Variants": 1
          },
          {
            "Symbol ID": "snowshowers",
            "English": "Snow showers",
            "Bokmål": "Snøbyger",
            "Nynorsk": "Snøbyer",
            "Old ID": 8,
            "Variants": 1
          },
          {
            "Symbol ID": "heavysnowshowers",
            "English": "Heavy snow showers",
            "Bokmål": "Kraftige snøbyger",
            "Nynorsk": "Kraftige snøbyer",
            "Old ID": 45,
            "Variants": 1
          },
          {
            "Symbol ID": "lightssnowshowersandthunder",
            "English": "Light snow showers and thunder",
            "Bokmål": "Lette snøbyger og torden",
            "Nynorsk": "Lette snøbyer og torevêr",
            "Old ID": 28,
            "Variants": 1
          },
          {
            "Symbol ID": "snowshowersandthunder",
            "English": "Snow showers and thunder",
            "Bokmål": "Snøbyger og torden",
            "Nynorsk": "Snøbyer og torevêr",
            "Old ID": 21,
            "Variants": 1
          },
          {
            "Symbol ID": "heavysnowshowersandthunder",
            "English": "Heavy snow showers and thunder",
            "Bokmål": "Kraftige snøbyger og torden",
            "Nynorsk": "Kraftige snøbyer og torevêr",
            "Old ID": 29,
            "Variants": 1
          },
          {
            "Symbol ID": "lightrain",
            "English": "Light rain",
            "Bokmål": "Lett regn",
            "Nynorsk": "Lett regn",
            "Old ID": 46,
            "Variants": 0
          },
          {
            "Symbol ID": "rain",
            "English": "Rain",
            "Bokmål": "Regn",
            "Nynorsk": "Regn",
            "Old ID": 9,
            "Variants": 0
          },
          {
            "Symbol ID": "heavyrain",
            "English": "Heavy rain",
            "Bokmål": "Kraftig regn",
            "Nynorsk": "Kraftig regn",
            "Old ID": 10,
            "Variants": 0
          },
          {
            "Symbol ID": "lightrainandthunder",
            "English": "Light rain and thunder",
            "Bokmål": "Lett regn og torden",
            "Nynorsk": "Lett regn og torevêr",
            "Old ID": 30,
            "Variants": 0
          },
          {
            "Symbol ID": "rainandthunder",
            "English": "Rain and thunder",
            "Bokmål": "Regn og torden",
            "Nynorsk": "Regn og torevêr",
            "Old ID": 22,
            "Variants": 0
          },
          {
            "Symbol ID": "heavyrainandthunder",
            "English": "Heavy rain and thunder",
            "Bokmål": "Kraftig regn og torden",
            "Nynorsk": "Kraftig regn og torevêr",
            "Old ID": 11,
            "Variants": 0
          },
          {
            "Symbol ID": "lightsleet",
            "English": "Light sleet",
            "Bokmål": "Lett sludd",
            "Nynorsk": "Lett sludd",
            "Old ID": 47,
            "Variants": 0
          },
          {
            "Symbol ID": "sleet",
            "English": "Sleet",
            "Bokmål": "Sludd",
            "Nynorsk": "Sludd",
            "Old ID": 12,
            "Variants": 0
          },
          {
            "Symbol ID": "heavysleet",
            "English": "Heavy sleet",
            "Bokmål": "Kraftig sludd",
            "Nynorsk": "Kraftig sludd",
            "Old ID": 48,
            "Variants": 0
          },
          {
            "Symbol ID": "lightsleetandthunder",
            "English": "Light sleet and thunder",
            "Bokmål": "Lett sludd og torden",
            "Nynorsk": "Lett sludd og torevêr",
            "Old ID": 31,
            "Variants": 0
          },
          {
            "Symbol ID": "sleetandthunder",
            "English": "Sleet and thunder",
            "Bokmål": "Sludd og torden",
            "Nynorsk": "Sludd og torevêr",
            "Old ID": 23,
            "Variants": 0
          },
          {
            "Symbol ID": "heavysleetandthunder",
            "English": "Heavy sleet and thunder",
            "Bokmål": "Kraftig sludd og torden",
            "Nynorsk": "Kraftig sludd og torevêr",
            "Old ID": 32,
            "Variants": 0
          },
          {
            "Symbol ID": "lightsnow",
            "English": "Light snow",
            "Bokmål": "Lett snø",
            "Nynorsk": "Lett snø",
            "Old ID": 49,
            "Variants": 0
          },
          {
            "Symbol ID": "snow",
            "English": "Snow",
            "Bokmål": "Snø",
            "Nynorsk": "Snø",
            "Old ID": 13,
            "Variants": 0
          },
          {
            "Symbol ID": "heavysnow",
            "English": "Heavy snow",
            "Bokmål": "Kraftig snø",
            "Nynorsk": "Kraftig snø",
            "Old ID": 50,
            "Variants": 0
          },
          {
            "Symbol ID": "lightsnowandthunder",
            "English": "Light snow and thunder",
            "Bokmål": "Lett snø og torden",
            "Nynorsk": "Lett snø og torevêr",
            "Old ID": 33,
            "Variants": 0
          },
          {
            "Symbol ID": "snowandthunder",
            "English": "Snow and thunder",
            "Bokmål": "Snø og torden",
            "Nynorsk": "Snø og torevêr",
            "Old ID": 14,
            "Variants": 0
          },
          {
            "Symbol ID": "heavysnowandthunder",
            "English": "Heavy snow and thunder",
            "Bokmål": "Kraftig snø og torden",
            "Nynorsk": "Kraftig snø og torevêr",
            "Old ID": 34,
            "Variants": 0
          },
          {
            "Symbol ID": "fog",
            "English": "Fog",
            "Bokmål": "Tåke",
            "Nynorsk": "Skodde",
            "Old ID": 15,
            "Variants": 0
          }
        ]
    """.trimIndent()
        private val weatherIcons = json.decodeFromString<List<WeatherIconEntity>>(jsonString)

        @SuppressLint("DiscouragedApi")
        fun resolve(context: Context, id: String): WeatherIconData? {
            try {
                val split = id.split("_", limit = 2)
                val prefix = split.first()
                val variant = split.getOrNull(1)
                return weatherIcons.find {
                    prefix == it.symbolId
                }?.let { icon ->
                    val resId = variant?.let { "${icon.symbolId}_$variant" } ?: icon.symbolId
                    WeatherIconData(
                        context.resources.getIdentifier(resId, "drawable", context.packageName),
                        icon.english
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }
        }
    }
}