package com.cygni.tim.weatherexplore

class Fixtures {
    companion object {
        val complete = """
            {
              "type": "Feature",
              "geometry": {
                "type": "Point",
                "coordinates": [
                  17.81,
                  59.32,
                  3
                ]
              },
              "properties": {
                "meta": {
                  "updated_at": "2024-01-10T09:41:57Z",
                  "units": {
                    "air_pressure_at_sea_level": "hPa",
                    "air_temperature": "celsius",
                    "air_temperature_max": "celsius",
                    "air_temperature_min": "celsius",
                    "air_temperature_percentile_10": "celsius",
                    "air_temperature_percentile_90": "celsius",
                    "cloud_area_fraction": "%",
                    "cloud_area_fraction_high": "%",
                    "cloud_area_fraction_low": "%",
                    "cloud_area_fraction_medium": "%",
                    "dew_point_temperature": "celsius",
                    "fog_area_fraction": "%",
                    "precipitation_amount": "mm",
                    "precipitation_amount_max": "mm",
                    "precipitation_amount_min": "mm",
                    "probability_of_precipitation": "%",
                    "probability_of_thunder": "%",
                    "relative_humidity": "%",
                    "ultraviolet_index_clear_sky": "1",
                    "wind_from_direction": "degrees",
                    "wind_speed": "m/s",
                    "wind_speed_of_gust": "m/s",
                    "wind_speed_percentile_10": "m/s",
                    "wind_speed_percentile_90": "m/s"
                  }
                },
                "timeseries": [
                  {
                    "time": "2024-01-10T09:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1027.5,
                          "air_temperature": -2.3,
                          "air_temperature_percentile_10": -2.9,
                          "air_temperature_percentile_90": -1.6,
                          "cloud_area_fraction": 62.5,
                          "cloud_area_fraction_high": 62.1,
                          "cloud_area_fraction_low": 1.4,
                          "cloud_area_fraction_medium": 0.0,
                          "dew_point_temperature": -4.4,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 86.8,
                          "ultraviolet_index_clear_sky": 0.2,
                          "wind_from_direction": 291.0,
                          "wind_speed": 3.6,
                          "wind_speed_of_gust": 6.4,
                          "wind_speed_percentile_10": 2.8,
                          "wind_speed_percentile_90": 3.7
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 0.0
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "air_temperature_max": -0.8,
                          "air_temperature_min": -1.7,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-10T10:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1027.0,
                          "air_temperature": -1.7,
                          "air_temperature_percentile_10": -2.7,
                          "air_temperature_percentile_90": -0.7,
                          "cloud_area_fraction": 88.6,
                          "cloud_area_fraction_high": 87.9,
                          "cloud_area_fraction_low": 4.8,
                          "cloud_area_fraction_medium": 0.0,
                          "dew_point_temperature": -4.2,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 84.9,
                          "ultraviolet_index_clear_sky": 0.3,
                          "wind_from_direction": 286.2,
                          "wind_speed": 3.7,
                          "wind_speed_of_gust": 6.5,
                          "wind_speed_percentile_10": 3.1,
                          "wind_speed_percentile_90": 4.1
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 0.0
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "air_temperature_max": -0.8,
                          "air_temperature_min": -1.6,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-10T11:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1026.5,
                          "air_temperature": -1.2,
                          "air_temperature_percentile_10": -2.4,
                          "air_temperature_percentile_90": -0.1,
                          "cloud_area_fraction": 90.2,
                          "cloud_area_fraction_high": 88.0,
                          "cloud_area_fraction_low": 11.8,
                          "cloud_area_fraction_medium": 0.0,
                          "dew_point_temperature": -3.9,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 83.4,
                          "ultraviolet_index_clear_sky": 0.3,
                          "wind_from_direction": 287.8,
                          "wind_speed": 3.9,
                          "wind_speed_of_gust": 7.0,
                          "wind_speed_percentile_10": 3.2,
                          "wind_speed_percentile_90": 4.4
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 0.0
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "air_temperature_max": -0.8,
                          "air_temperature_min": -1.6,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-10T12:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1026.3,
                          "air_temperature": -0.8,
                          "air_temperature_percentile_10": -2.2,
                          "air_temperature_percentile_90": 0.3,
                          "cloud_area_fraction": 98.1,
                          "cloud_area_fraction_high": 97.6,
                          "cloud_area_fraction_low": 11.6,
                          "cloud_area_fraction_medium": 0.0,
                          "dew_point_temperature": -3.6,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 83.3,
                          "ultraviolet_index_clear_sky": 0.3,
                          "wind_from_direction": 288.9,
                          "wind_speed": 4.0,
                          "wind_speed_of_gust": 7.2,
                          "wind_speed_percentile_10": 3.3,
                          "wind_speed_percentile_90": 4.2
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 0.0
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "air_temperature_max": -0.9,
                          "air_temperature_min": -1.6,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-10T13:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1025.4,
                          "air_temperature": -0.9,
                          "air_temperature_percentile_10": -2.3,
                          "air_temperature_percentile_90": 0.3,
                          "cloud_area_fraction": 94.7,
                          "cloud_area_fraction_high": 94.7,
                          "cloud_area_fraction_low": 0.0,
                          "cloud_area_fraction_medium": 0.0,
                          "dew_point_temperature": -3.6,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 84.0,
                          "ultraviolet_index_clear_sky": 0.1,
                          "wind_from_direction": 282.9,
                          "wind_speed": 4.4,
                          "wind_speed_of_gust": 7.7,
                          "wind_speed_percentile_10": 3.4,
                          "wind_speed_percentile_90": 4.4
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 0.0
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "air_temperature_max": -0.5,
                          "air_temperature_min": -1.6,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-10T14:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1024.6,
                          "air_temperature": -1.3,
                          "air_temperature_percentile_10": -2.7,
                          "air_temperature_percentile_90": 0.0,
                          "cloud_area_fraction": 98.9,
                          "cloud_area_fraction_high": 98.9,
                          "cloud_area_fraction_low": 0.0,
                          "cloud_area_fraction_medium": 0.0,
                          "dew_point_temperature": -3.7,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 85.2,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 286.8,
                          "wind_speed": 4.3,
                          "wind_speed_of_gust": 7.8,
                          "wind_speed_percentile_10": 3.1,
                          "wind_speed_percentile_90": 4.5
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 0.0
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "air_temperature_max": -0.1,
                          "air_temperature_min": -1.6,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-10T15:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1024.1,
                          "air_temperature": -1.6,
                          "air_temperature_percentile_10": -3.2,
                          "air_temperature_percentile_90": -0.4,
                          "cloud_area_fraction": 97.1,
                          "cloud_area_fraction_high": 97.1,
                          "cloud_area_fraction_low": 0.0,
                          "cloud_area_fraction_medium": 0.0,
                          "dew_point_temperature": -3.8,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 86.7,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 289.2,
                          "wind_speed": 4.1,
                          "wind_speed_of_gust": 7.6,
                          "wind_speed_percentile_10": 2.5,
                          "wind_speed_percentile_90": 4.4
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 0.0
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "air_temperature_max": 0.3,
                          "air_temperature_min": -1.6,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-10T16:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1023.8,
                          "air_temperature": -1.6,
                          "air_temperature_percentile_10": -3.7,
                          "air_temperature_percentile_90": 0.1,
                          "cloud_area_fraction": 59.9,
                          "cloud_area_fraction_high": 59.8,
                          "cloud_area_fraction_low": 0.0,
                          "cloud_area_fraction_medium": 0.0,
                          "dew_point_temperature": -3.8,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 86.9,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 288.1,
                          "wind_speed": 4.3,
                          "wind_speed_of_gust": 7.6,
                          "wind_speed_percentile_10": 2.5,
                          "wind_speed_percentile_90": 5.1
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 0.0
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "air_temperature_max": 0.4,
                          "air_temperature_min": -1.5,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-10T17:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1023.3,
                          "air_temperature": -1.5,
                          "air_temperature_percentile_10": -3.9,
                          "air_temperature_percentile_90": 0.3,
                          "cloud_area_fraction": 31.4,
                          "cloud_area_fraction_high": 31.1,
                          "cloud_area_fraction_low": 0.0,
                          "cloud_area_fraction_medium": 0.6,
                          "dew_point_temperature": -3.7,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 86.5,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 287.3,
                          "wind_speed": 4.4,
                          "wind_speed_of_gust": 7.8,
                          "wind_speed_percentile_10": 3.1,
                          "wind_speed_percentile_90": 5.3
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 4.5
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "fair_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "air_temperature_max": 0.4,
                          "air_temperature_min": -1.2,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-10T18:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1022.9,
                          "air_temperature": -1.2,
                          "air_temperature_percentile_10": -3.8,
                          "air_temperature_percentile_90": 0.4,
                          "cloud_area_fraction": 68.2,
                          "cloud_area_fraction_high": 67.6,
                          "cloud_area_fraction_low": 0.1,
                          "cloud_area_fraction_medium": 7.7,
                          "dew_point_temperature": -3.5,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 85.7,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 285.7,
                          "wind_speed": 4.5,
                          "wind_speed_of_gust": 7.9,
                          "wind_speed_percentile_10": 3.3,
                          "wind_speed_percentile_90": 5.5
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 7.6
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "air_temperature_max": 0.4,
                          "air_temperature_min": -0.5,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-10T19:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1022.5,
                          "air_temperature": -0.5,
                          "air_temperature_percentile_10": -2.7,
                          "air_temperature_percentile_90": 0.8,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 100.0,
                          "cloud_area_fraction_low": 6.7,
                          "cloud_area_fraction_medium": 62.6,
                          "dew_point_temperature": -3.1,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 84.3,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 283.9,
                          "wind_speed": 5.2,
                          "wind_speed_of_gust": 9.1,
                          "wind_speed_percentile_10": 4.1,
                          "wind_speed_percentile_90": 5.3
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 7.8
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.4,
                          "probability_of_thunder": 0.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "air_temperature_max": 0.6,
                          "air_temperature_min": -0.1,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-10T20:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1021.9,
                          "air_temperature": -0.1,
                          "air_temperature_percentile_10": -2.3,
                          "air_temperature_percentile_90": 1.5,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 99.7,
                          "cloud_area_fraction_low": 15.5,
                          "cloud_area_fraction_medium": 90.1,
                          "dew_point_temperature": -2.8,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 83.5,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 286.3,
                          "wind_speed": 5.5,
                          "wind_speed_of_gust": 9.9,
                          "wind_speed_percentile_10": 4.9,
                          "wind_speed_percentile_90": 5.6
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 8.4
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 10.0,
                          "probability_of_thunder": 0.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "fair_night"
                        },
                        "details": {
                          "air_temperature_max": 0.6,
                          "air_temperature_min": 0.0,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-10T21:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1021.5,
                          "air_temperature": 0.3,
                          "air_temperature_percentile_10": -2.5,
                          "air_temperature_percentile_90": 2.2,
                          "cloud_area_fraction": 99.6,
                          "cloud_area_fraction_high": 96.5,
                          "cloud_area_fraction_low": 5.4,
                          "cloud_area_fraction_medium": 89.6,
                          "dew_point_temperature": -2.4,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 83.5,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 290.3,
                          "wind_speed": 5.4,
                          "wind_speed_of_gust": 9.7,
                          "wind_speed_percentile_10": 4.2,
                          "wind_speed_percentile_90": 6.3
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 15.5
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "clearsky_night"
                        },
                        "details": {
                          "air_temperature_max": 0.6,
                          "air_temperature_min": 0.0,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-10T22:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1020.8,
                          "air_temperature": 0.4,
                          "air_temperature_percentile_10": -3.0,
                          "air_temperature_percentile_90": 2.8,
                          "cloud_area_fraction": 22.0,
                          "cloud_area_fraction_high": 2.7,
                          "cloud_area_fraction_low": 0.1,
                          "cloud_area_fraction_medium": 20.1,
                          "dew_point_temperature": -2.2,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 84.3,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 297.7,
                          "wind_speed": 6.2,
                          "wind_speed_of_gust": 11.0,
                          "wind_speed_percentile_10": 3.6,
                          "wind_speed_percentile_90": 6.2
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 21.4
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "fair_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "clearsky_night"
                        },
                        "details": {
                          "air_temperature_max": 0.6,
                          "air_temperature_min": 0.0,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-10T23:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1020.7,
                          "air_temperature": 0.0,
                          "air_temperature_percentile_10": -3.5,
                          "air_temperature_percentile_90": 3.2,
                          "cloud_area_fraction": 0.3,
                          "cloud_area_fraction_high": 0.3,
                          "cloud_area_fraction_low": 0.0,
                          "cloud_area_fraction_medium": 0.0,
                          "dew_point_temperature": -2.3,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 85.8,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 305.6,
                          "wind_speed": 5.8,
                          "wind_speed_of_gust": 11.1,
                          "wind_speed_percentile_10": 3.3,
                          "wind_speed_percentile_90": 6.3
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 25.3
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "clearsky_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "fair_night"
                        },
                        "details": {
                          "air_temperature_max": 1.2,
                          "air_temperature_min": 0.0,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 4.5
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T00:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1020.7,
                          "air_temperature": 0.1,
                          "air_temperature_percentile_10": -3.9,
                          "air_temperature_percentile_90": 3.0,
                          "cloud_area_fraction": 3.9,
                          "cloud_area_fraction_high": 2.6,
                          "cloud_area_fraction_low": 1.3,
                          "cloud_area_fraction_medium": 0.0,
                          "dew_point_temperature": -2.1,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 86.7,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 310.1,
                          "wind_speed": 5.4,
                          "wind_speed_of_gust": 10.4,
                          "wind_speed_percentile_10": 3.2,
                          "wind_speed_percentile_90": 5.8
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 25.4
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "clearsky_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "fair_night"
                        },
                        "details": {
                          "air_temperature_max": 1.3,
                          "air_temperature_min": 0.0,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 7.6
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T01:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1020.5,
                          "air_temperature": 0.6,
                          "air_temperature_percentile_10": -4.2,
                          "air_temperature_percentile_90": 2.7,
                          "cloud_area_fraction": 3.0,
                          "cloud_area_fraction_high": 2.0,
                          "cloud_area_fraction_low": 1.0,
                          "cloud_area_fraction_medium": 0.0,
                          "dew_point_temperature": -1.8,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 84.9,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 306.5,
                          "wind_speed": 5.9,
                          "wind_speed_of_gust": 10.7,
                          "wind_speed_percentile_10": 3.9,
                          "wind_speed_percentile_90": 6.6
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 25.4
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "clearsky_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "air_temperature_max": 1.3,
                          "air_temperature_min": 0.0,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 7.8
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T02:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1020.5,
                          "air_temperature": 0.4,
                          "air_temperature_percentile_10": -2.9,
                          "air_temperature_percentile_90": 2.3,
                          "cloud_area_fraction": 0.9,
                          "cloud_area_fraction_high": 0.6,
                          "cloud_area_fraction_low": 0.3,
                          "cloud_area_fraction_medium": 0.0,
                          "dew_point_temperature": -1.9,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 85.8,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 304.4,
                          "wind_speed": 5.2,
                          "wind_speed_of_gust": 10.7,
                          "wind_speed_percentile_10": 3.6,
                          "wind_speed_percentile_90": 6.4
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 26.0
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "clearsky_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "air_temperature_max": 1.3,
                          "air_temperature_min": 0.0,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 8.4
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T03:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1020.4,
                          "air_temperature": 0.0,
                          "air_temperature_percentile_10": -3.5,
                          "air_temperature_percentile_90": 2.3,
                          "cloud_area_fraction": 4.7,
                          "cloud_area_fraction_high": 2.8,
                          "cloud_area_fraction_low": 1.9,
                          "cloud_area_fraction_medium": 0.0,
                          "dew_point_temperature": -2.0,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 87.6,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 303.1,
                          "wind_speed": 5.2,
                          "wind_speed_of_gust": 10.3,
                          "wind_speed_percentile_10": 3.0,
                          "wind_speed_percentile_90": 6.0
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 26.4
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "clearsky_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 2.5,
                          "probability_of_thunder": 0.9
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "air_temperature_max": 1.3,
                          "air_temperature_min": 0.4,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.7,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 15.4
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T04:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1020.8,
                          "air_temperature": 0.4,
                          "air_temperature_percentile_10": -3.5,
                          "air_temperature_percentile_90": 2.5,
                          "cloud_area_fraction": 31.6,
                          "cloud_area_fraction_high": 0.1,
                          "cloud_area_fraction_low": 19.4,
                          "cloud_area_fraction_medium": 22.6,
                          "dew_point_temperature": -1.7,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 87.7,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 324.9,
                          "wind_speed": 4.4,
                          "wind_speed_of_gust": 9.2,
                          "wind_speed_percentile_10": 2.9,
                          "wind_speed_percentile_90": 5.5
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 26.0
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "fair_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.2,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 17.5,
                          "probability_of_thunder": 0.8
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "air_temperature_max": 1.3,
                          "air_temperature_min": 0.8,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.8,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 21.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T05:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1021.3,
                          "air_temperature": 1.2,
                          "air_temperature_percentile_10": -2.5,
                          "air_temperature_percentile_90": 2.4,
                          "cloud_area_fraction": 68.8,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 51.9,
                          "cloud_area_fraction_medium": 60.3,
                          "dew_point_temperature": -0.7,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 87.8,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 311.9,
                          "wind_speed": 5.2,
                          "wind_speed_of_gust": 9.3,
                          "wind_speed_percentile_10": 3.8,
                          "wind_speed_percentile_90": 5.4
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 18.9
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.2,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 16.8,
                          "probability_of_thunder": 0.5
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "air_temperature_max": 1.3,
                          "air_temperature_min": 0.5,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.8,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 17.4
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T06:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1022.2,
                          "air_temperature": 1.3,
                          "air_temperature_percentile_10": -1.7,
                          "air_temperature_percentile_90": 2.1,
                          "cloud_area_fraction": 88.1,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 77.3,
                          "cloud_area_fraction_medium": 68.0,
                          "dew_point_temperature": 0.4,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 95.4,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 350.5,
                          "wind_speed": 3.1,
                          "wind_speed_of_gust": 9.6,
                          "wind_speed_percentile_10": 3.0,
                          "wind_speed_percentile_90": 4.4
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 14.1
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 1.8,
                          "probability_of_thunder": 0.5
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "air_temperature_max": 1.3,
                          "air_temperature_min": 0.4,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.7,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 12.9
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T07:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1022.9,
                          "air_temperature": 1.3,
                          "air_temperature_percentile_10": -1.5,
                          "air_temperature_percentile_90": 1.9,
                          "cloud_area_fraction": 79.6,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 75.2,
                          "cloud_area_fraction_medium": 35.9,
                          "dew_point_temperature": -0.3,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 90.2,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 342.6,
                          "wind_speed": 4.1,
                          "wind_speed_of_gust": 7.5,
                          "wind_speed_percentile_10": 2.6,
                          "wind_speed_percentile_90": 4.6
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 13.7
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 5.7,
                          "probability_of_thunder": 0.6
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "air_temperature_max": 0.9,
                          "air_temperature_min": 0.0,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.6,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 12.6
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T08:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1023.7,
                          "air_temperature": 0.9,
                          "air_temperature_percentile_10": -2.4,
                          "air_temperature_percentile_90": 1.7,
                          "cloud_area_fraction": 79.9,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 71.3,
                          "cloud_area_fraction_medium": 38.6,
                          "dew_point_temperature": -1.3,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 86.4,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 344.1,
                          "wind_speed": 3.9,
                          "wind_speed_of_gust": 8.1,
                          "wind_speed_percentile_10": 2.9,
                          "wind_speed_percentile_90": 4.6
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 12.5
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.2,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 18.2,
                          "probability_of_thunder": 0.7
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "air_temperature_max": 0.8,
                          "air_temperature_min": -0.8,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.6,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 11.2
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T09:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1024.3,
                          "air_temperature": 0.8,
                          "air_temperature_percentile_10": -1.9,
                          "air_temperature_percentile_90": 1.5,
                          "cloud_area_fraction": 86.4,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 83.0,
                          "cloud_area_fraction_medium": 51.9,
                          "dew_point_temperature": -1.1,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 88.3,
                          "ultraviolet_index_clear_sky": 0.2,
                          "wind_from_direction": 334.0,
                          "wind_speed": 3.2,
                          "wind_speed_of_gust": 7.4,
                          "wind_speed_percentile_10": 3.1,
                          "wind_speed_percentile_90": 4.4
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 5.7
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 12.0,
                          "probability_of_thunder": 0.8
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "air_temperature_max": 0.8,
                          "air_temperature_min": -1.3,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 4.8
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T10:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1025.0,
                          "air_temperature": 0.8,
                          "air_temperature_percentile_10": -1.2,
                          "air_temperature_percentile_90": 1.4,
                          "cloud_area_fraction": 88.9,
                          "cloud_area_fraction_high": 0.1,
                          "cloud_area_fraction_low": 88.1,
                          "cloud_area_fraction_medium": 27.6,
                          "dew_point_temperature": -1.0,
                          "fog_area_fraction": 0.2,
                          "relative_humidity": 90.0,
                          "ultraviolet_index_clear_sky": 0.3,
                          "wind_from_direction": 337.0,
                          "wind_speed": 2.3,
                          "wind_speed_of_gust": 5.7,
                          "wind_speed_percentile_10": 2.3,
                          "wind_speed_percentile_90": 3.9
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 2.0
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 12.2,
                          "probability_of_thunder": 0.7
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "air_temperature_max": 0.5,
                          "air_temperature_min": -2.1,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 1.1
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T11:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1025.6,
                          "air_temperature": 0.5,
                          "air_temperature_percentile_10": -0.9,
                          "air_temperature_percentile_90": 1.2,
                          "cloud_area_fraction": 76.7,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 76.6,
                          "cloud_area_fraction_medium": 1.5,
                          "dew_point_temperature": -0.9,
                          "fog_area_fraction": 0.1,
                          "relative_humidity": 91.3,
                          "ultraviolet_index_clear_sky": 0.3,
                          "wind_from_direction": 326.5,
                          "wind_speed": 2.9,
                          "wind_speed_of_gust": 5.2,
                          "wind_speed_percentile_10": 2.6,
                          "wind_speed_percentile_90": 3.9
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 0.6
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 4.4,
                          "probability_of_thunder": 0.8
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "air_temperature_max": 0.4,
                          "air_temperature_min": -2.3,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.5
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T12:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1026.0,
                          "air_temperature": 0.4,
                          "air_temperature_percentile_10": -1.1,
                          "air_temperature_percentile_90": 1.1,
                          "cloud_area_fraction": 74.2,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 74.2,
                          "cloud_area_fraction_medium": 0.3,
                          "dew_point_temperature": -1.4,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 88.8,
                          "ultraviolet_index_clear_sky": 0.3,
                          "wind_from_direction": 321.6,
                          "wind_speed": 2.7,
                          "wind_speed_of_gust": 5.3,
                          "wind_speed_percentile_10": 2.6,
                          "wind_speed_percentile_90": 3.7
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 0.6
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 1.4,
                          "probability_of_thunder": 0.8
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "air_temperature_max": 0.0,
                          "air_temperature_min": -2.3,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.3
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T13:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1026.1,
                          "air_temperature": 0.0,
                          "air_temperature_percentile_10": -1.3,
                          "air_temperature_percentile_90": 1.0,
                          "cloud_area_fraction": 46.1,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 46.1,
                          "cloud_area_fraction_medium": 0.1,
                          "dew_point_temperature": -1.8,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 89.1,
                          "ultraviolet_index_clear_sky": 0.1,
                          "wind_from_direction": 308.6,
                          "wind_speed": 2.5,
                          "wind_speed_of_gust": 4.7,
                          "wind_speed_percentile_10": 2.5,
                          "wind_speed_percentile_90": 3.3
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 0.6
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.9,
                          "probability_of_thunder": 0.8
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "air_temperature_max": -0.8,
                          "air_temperature_min": -2.3,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.3
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T14:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1026.8,
                          "air_temperature": -0.8,
                          "air_temperature_percentile_10": -1.6,
                          "air_temperature_percentile_90": 0.9,
                          "cloud_area_fraction": 72.8,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 72.8,
                          "cloud_area_fraction_medium": 0.3,
                          "dew_point_temperature": -2.4,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 90.7,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 307.6,
                          "wind_speed": 2.7,
                          "wind_speed_of_gust": 4.7,
                          "wind_speed_percentile_10": 2.5,
                          "wind_speed_percentile_90": 3.3
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 0.2
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 1.1,
                          "probability_of_thunder": 0.7
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "air_temperature_max": -1.3,
                          "air_temperature_min": -2.3,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.2
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T15:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1026.8,
                          "air_temperature": -1.3,
                          "air_temperature_percentile_10": -2.1,
                          "air_temperature_percentile_90": 0.4,
                          "cloud_area_fraction": 81.9,
                          "cloud_area_fraction_high": 3.1,
                          "cloud_area_fraction_low": 80.8,
                          "cloud_area_fraction_medium": 3.0,
                          "dew_point_temperature": -2.8,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 91.2,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 314.9,
                          "wind_speed": 2.2,
                          "wind_speed_of_gust": 4.7,
                          "wind_speed_percentile_10": 1.8,
                          "wind_speed_percentile_90": 2.8
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 0.0
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.4
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "air_temperature_max": -1.7,
                          "air_temperature_min": -2.3,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T16:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1027.0,
                          "air_temperature": -2.1,
                          "air_temperature_percentile_10": -2.9,
                          "air_temperature_percentile_90": -0.1,
                          "cloud_area_fraction": 60.6,
                          "cloud_area_fraction_high": 2.6,
                          "cloud_area_fraction_low": 57.6,
                          "cloud_area_fraction_medium": 21.5,
                          "dew_point_temperature": -3.4,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 92.8,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 304.8,
                          "wind_speed": 1.8,
                          "wind_speed_of_gust": 3.8,
                          "wind_speed_percentile_10": 1.2,
                          "wind_speed_percentile_90": 3.1
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 0.0
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 2.6,
                          "probability_of_thunder": 0.4
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "air_temperature_max": -1.7,
                          "air_temperature_min": -2.3,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T17:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1027.0,
                          "air_temperature": -2.3,
                          "air_temperature_percentile_10": -3.2,
                          "air_temperature_percentile_90": -0.6,
                          "cloud_area_fraction": 81.1,
                          "cloud_area_fraction_high": 2.5,
                          "cloud_area_fraction_low": 79.9,
                          "cloud_area_fraction_medium": 24.1,
                          "dew_point_temperature": -3.5,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 94.1,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 310.4,
                          "wind_speed": 1.7,
                          "wind_speed_of_gust": 3.2,
                          "wind_speed_percentile_10": 1.4,
                          "wind_speed_percentile_90": 2.6
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 0.0
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.2,
                          "probability_of_thunder": 0.6
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "air_temperature_max": -1.7,
                          "air_temperature_min": -2.2,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T18:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1026.6,
                          "air_temperature": -1.8,
                          "air_temperature_percentile_10": -4.0,
                          "air_temperature_percentile_90": -1.0,
                          "cloud_area_fraction": 96.3,
                          "cloud_area_fraction_high": 0.4,
                          "cloud_area_fraction_low": 96.2,
                          "cloud_area_fraction_medium": 1.0,
                          "dew_point_temperature": -3.0,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 93.2,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 304.5,
                          "wind_speed": 1.5,
                          "wind_speed_of_gust": 2.9,
                          "wind_speed_percentile_10": 1.1,
                          "wind_speed_percentile_90": 2.4
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 0.0
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.9
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "air_temperature_max": -1.7,
                          "air_temperature_min": -2.4,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T19:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1026.1,
                          "air_temperature": -1.7,
                          "air_temperature_percentile_10": -4.2,
                          "air_temperature_percentile_90": -1.1,
                          "cloud_area_fraction": 93.5,
                          "cloud_area_fraction_high": 2.6,
                          "cloud_area_fraction_low": 92.7,
                          "cloud_area_fraction_medium": 8.4,
                          "dew_point_temperature": -2.9,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 93.4,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 280.4,
                          "wind_speed": 1.2,
                          "wind_speed_of_gust": 2.6,
                          "wind_speed_percentile_10": 1.1,
                          "wind_speed_percentile_90": 2.1
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 0.0
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.8
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "air_temperature_max": -1.6,
                          "air_temperature_min": -2.4,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T20:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1025.7,
                          "air_temperature": -1.8,
                          "air_temperature_percentile_10": -4.3,
                          "air_temperature_percentile_90": -1.2,
                          "cloud_area_fraction": 88.9,
                          "cloud_area_fraction_high": 1.6,
                          "cloud_area_fraction_low": 83.5,
                          "cloud_area_fraction_medium": 32.7,
                          "dew_point_temperature": -2.9,
                          "fog_area_fraction": 1.1,
                          "relative_humidity": 93.7,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 271.3,
                          "wind_speed": 1.5,
                          "wind_speed_of_gust": 2.6,
                          "wind_speed_percentile_10": 1.3,
                          "wind_speed_percentile_90": 2.3
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 0.0
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 1.2,
                          "probability_of_thunder": 0.7
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "air_temperature_max": -1.0,
                          "air_temperature_min": -2.4,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T21:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1024.8,
                          "air_temperature": -1.7,
                          "air_temperature_percentile_10": -4.5,
                          "air_temperature_percentile_90": -0.9,
                          "cloud_area_fraction": 83.8,
                          "cloud_area_fraction_high": 0.2,
                          "cloud_area_fraction_low": 72.2,
                          "cloud_area_fraction_medium": 39.7,
                          "dew_point_temperature": -2.7,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 94.5,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 246.3,
                          "wind_speed": 1.8,
                          "wind_speed_of_gust": 3.2,
                          "wind_speed_percentile_10": 1.5,
                          "wind_speed_percentile_90": 2.8
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 0.0
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 2.8,
                          "probability_of_thunder": 0.5
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "air_temperature_max": -1.0,
                          "air_temperature_min": -2.4,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T22:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1023.9,
                          "air_temperature": -2.0,
                          "air_temperature_percentile_10": -4.3,
                          "air_temperature_percentile_90": -0.7,
                          "cloud_area_fraction": 41.3,
                          "cloud_area_fraction_high": 0.1,
                          "cloud_area_fraction_low": 17.6,
                          "cloud_area_fraction_medium": 28.6,
                          "dew_point_temperature": -3.0,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 94.6,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 250.4,
                          "wind_speed": 2.1,
                          "wind_speed_of_gust": 3.6,
                          "wind_speed_percentile_10": 1.7,
                          "wind_speed_percentile_90": 2.9
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 0.5
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 5.9,
                          "probability_of_thunder": 0.4
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "air_temperature_max": -1.0,
                          "air_temperature_min": -2.4,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-11T23:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1023.0,
                          "air_temperature": -2.2,
                          "air_temperature_percentile_10": -3.6,
                          "air_temperature_percentile_90": -0.4,
                          "cloud_area_fraction": 66.8,
                          "cloud_area_fraction_high": 0.3,
                          "cloud_area_fraction_low": 62.3,
                          "cloud_area_fraction_medium": 12.5,
                          "dew_point_temperature": -3.7,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 91.6,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 268.1,
                          "wind_speed": 2.8,
                          "wind_speed_of_gust": 5.2,
                          "wind_speed_percentile_10": 2.0,
                          "wind_speed_percentile_90": 3.6
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 2.5
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.4
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "air_temperature_max": -1.0,
                          "air_temperature_min": -2.4,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-12T00:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1021.9,
                          "air_temperature": -2.4,
                          "air_temperature_percentile_10": -3.1,
                          "air_temperature_percentile_90": 0.0,
                          "cloud_area_fraction": 72.5,
                          "cloud_area_fraction_high": 1.8,
                          "cloud_area_fraction_low": 53.1,
                          "cloud_area_fraction_medium": 36.4,
                          "dew_point_temperature": -3.9,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 91.4,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 261.8,
                          "wind_speed": 3.0,
                          "wind_speed_of_gust": 5.2,
                          "wind_speed_percentile_10": 2.4,
                          "wind_speed_percentile_90": 4.0
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 3.4
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 2.6,
                          "probability_of_thunder": 0.4
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -0.7,
                          "air_temperature_min": -1.6,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-12T01:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1020.5,
                          "air_temperature": -1.6,
                          "air_temperature_percentile_10": -2.4,
                          "air_temperature_percentile_90": 0.0,
                          "cloud_area_fraction": 94.3,
                          "cloud_area_fraction_high": 4.2,
                          "cloud_area_fraction_low": 91.4,
                          "cloud_area_fraction_medium": 50.9,
                          "dew_point_temperature": -3.0,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 91.8,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 261.4,
                          "wind_speed": 3.4,
                          "wind_speed_of_gust": 6.0,
                          "wind_speed_percentile_10": 3.3,
                          "wind_speed_percentile_90": 4.8
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 5.6
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 2.3,
                          "probability_of_thunder": 0.9
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -0.2,
                          "air_temperature_min": -1.4,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-12T02:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1019.2,
                          "air_temperature": -1.0,
                          "air_temperature_percentile_10": -2.4,
                          "air_temperature_percentile_90": 0.0,
                          "cloud_area_fraction": 99.1,
                          "cloud_area_fraction_high": 4.8,
                          "cloud_area_fraction_low": 93.8,
                          "cloud_area_fraction_medium": 80.7,
                          "dew_point_temperature": -2.5,
                          "fog_area_fraction": 0.2,
                          "relative_humidity": 91.3,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 266.8,
                          "wind_speed": 4.3,
                          "wind_speed_of_gust": 7.6,
                          "wind_speed_percentile_10": 3.5,
                          "wind_speed_percentile_90": 5.2
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 12.0
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.9
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": 0.2,
                          "air_temperature_min": -1.4,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-12T03:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1017.8,
                          "air_temperature": -1.0,
                          "air_temperature_percentile_10": -2.9,
                          "air_temperature_percentile_90": 0.2,
                          "cloud_area_fraction": 63.2,
                          "cloud_area_fraction_high": 4.6,
                          "cloud_area_fraction_low": 25.6,
                          "cloud_area_fraction_medium": 55.3,
                          "dew_point_temperature": -2.6,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 90.5,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 273.2,
                          "wind_speed": 5.0,
                          "wind_speed_of_gust": 8.9,
                          "wind_speed_percentile_10": 3.9,
                          "wind_speed_percentile_90": 5.6
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 14.3
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.6
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": 0.7,
                          "air_temperature_min": -1.4,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-12T04:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1016.8,
                          "air_temperature": -1.4,
                          "air_temperature_percentile_10": -3.1,
                          "air_temperature_percentile_90": 0.4,
                          "cloud_area_fraction": 87.4,
                          "cloud_area_fraction_high": 11.1,
                          "cloud_area_fraction_low": 61.5,
                          "cloud_area_fraction_medium": 56.9,
                          "dew_point_temperature": -3.3,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 88.3,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 285.0,
                          "wind_speed": 5.0,
                          "wind_speed_of_gust": 9.0,
                          "wind_speed_percentile_10": 4.1,
                          "wind_speed_percentile_90": 6.1
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 17.2
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.7
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": 1.4,
                          "air_temperature_min": -1.1,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-12T05:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1015.8,
                          "air_temperature": -1.1,
                          "air_temperature_percentile_10": -3.2,
                          "air_temperature_percentile_90": 0.5,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 6.0,
                          "cloud_area_fraction_low": 87.1,
                          "cloud_area_fraction_medium": 99.8,
                          "dew_point_temperature": -3.4,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 86.4,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 288.4,
                          "wind_speed": 5.2,
                          "wind_speed_of_gust": 9.3,
                          "wind_speed_percentile_10": 4.8,
                          "wind_speed_percentile_90": 7.0
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 17.7
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.1,
                          "probability_of_thunder": 0.7
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": 1.7,
                          "air_temperature_min": -0.7,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 1.0
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-12T06:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1015.2,
                          "air_temperature": -0.7,
                          "air_temperature_percentile_10": -3.4,
                          "air_temperature_percentile_90": 0.6,
                          "cloud_area_fraction": 97.9,
                          "cloud_area_fraction_high": 3.6,
                          "cloud_area_fraction_low": 81.0,
                          "cloud_area_fraction_medium": 91.2,
                          "dew_point_temperature": -3.3,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 84.2,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 294.4,
                          "wind_speed": 5.8,
                          "wind_speed_of_gust": 10.3,
                          "wind_speed_percentile_10": 4.8,
                          "wind_speed_percentile_90": 7.0
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 17.3
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.6
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": 1.7,
                          "air_temperature_min": -0.2,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 1.3
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-12T07:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1014.4,
                          "air_temperature": -0.2,
                          "air_temperature_percentile_10": -2.6,
                          "air_temperature_percentile_90": 0.9,
                          "cloud_area_fraction": 99.8,
                          "cloud_area_fraction_high": 43.0,
                          "cloud_area_fraction_low": 74.2,
                          "cloud_area_fraction_medium": 99.2,
                          "dew_point_temperature": -3.1,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 82.1,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 292.3,
                          "wind_speed": 6.1,
                          "wind_speed_of_gust": 10.8,
                          "wind_speed_percentile_10": 5.0,
                          "wind_speed_percentile_90": 7.2
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 0.4
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": 1.7,
                          "air_temperature_min": 0.2,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 4.4
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-12T08:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1013.8,
                          "air_temperature": 0.2,
                          "air_temperature_percentile_10": -2.5,
                          "air_temperature_percentile_90": 1.3,
                          "cloud_area_fraction": 99.6,
                          "cloud_area_fraction_high": 39.7,
                          "cloud_area_fraction_low": 51.7,
                          "cloud_area_fraction_medium": 98.8,
                          "dew_point_temperature": -3.1,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 80.2,
                          "ultraviolet_index_clear_sky": 0.1,
                          "wind_from_direction": 296.9,
                          "wind_speed": 6.4,
                          "wind_speed_of_gust": 11.4,
                          "wind_speed_percentile_10": 5.2,
                          "wind_speed_percentile_90": 8.2
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.0,
                          "probability_of_thunder": 1.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": 1.7,
                          "air_temperature_min": 0.7,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 11.3
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-12T09:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1013.8,
                          "air_temperature": 0.7,
                          "air_temperature_percentile_10": -2.4,
                          "air_temperature_percentile_90": 1.4,
                          "cloud_area_fraction": 92.2,
                          "cloud_area_fraction_high": 6.2,
                          "cloud_area_fraction_low": 59.2,
                          "cloud_area_fraction_medium": 73.9,
                          "dew_point_temperature": -2.8,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 78.8,
                          "ultraviolet_index_clear_sky": 0.2,
                          "wind_from_direction": 303.5,
                          "wind_speed": 6.2,
                          "wind_speed_of_gust": 11.5,
                          "wind_speed_percentile_10": 5.1,
                          "wind_speed_percentile_90": 7.9
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 5.7,
                          "probability_of_thunder": 1.2
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": 1.7,
                          "air_temperature_min": 1.1,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.6,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 13.2
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-12T10:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1013.9,
                          "air_temperature": 1.4,
                          "air_temperature_percentile_10": -1.6,
                          "air_temperature_percentile_90": 2.0,
                          "cloud_area_fraction": 91.5,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 90.2,
                          "cloud_area_fraction_medium": 13.4,
                          "dew_point_temperature": -2.5,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 77.0,
                          "ultraviolet_index_clear_sky": 0.3,
                          "wind_from_direction": 310.5,
                          "wind_speed": 7.2,
                          "wind_speed_of_gust": 13.0,
                          "wind_speed_percentile_10": 5.5,
                          "wind_speed_percentile_90": 7.9
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.2,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 15.9,
                          "probability_of_thunder": 1.2
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": 1.7,
                          "air_temperature_min": -0.4,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.6,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 12.7
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-12T11:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1014.3,
                          "air_temperature": 1.7,
                          "air_temperature_percentile_10": -1.1,
                          "air_temperature_percentile_90": 2.4,
                          "cloud_area_fraction": 83.5,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 69.8,
                          "cloud_area_fraction_medium": 52.7,
                          "dew_point_temperature": -2.1,
                          "fog_area_fraction": 0.1,
                          "relative_humidity": 77.3,
                          "ultraviolet_index_clear_sky": 0.4,
                          "wind_from_direction": 317.3,
                          "wind_speed": 6.7,
                          "wind_speed_of_gust": 13.7,
                          "wind_speed_percentile_10": 5.3,
                          "wind_speed_percentile_90": 7.0
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 13.4,
                          "probability_of_thunder": 1.5
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": 1.7,
                          "air_temperature_min": -1.7,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.6,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 12.6
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-12T12:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1014.7,
                          "air_temperature": 1.7,
                          "air_temperature_percentile_10": -0.5,
                          "air_temperature_percentile_90": 2.4,
                          "cloud_area_fraction": 99.9,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 94.3,
                          "cloud_area_fraction_medium": 97.8,
                          "dew_point_temperature": -1.2,
                          "fog_area_fraction": 0.1,
                          "relative_humidity": 82.2,
                          "ultraviolet_index_clear_sky": 0.3,
                          "wind_from_direction": 311.9,
                          "wind_speed": 7.2,
                          "wind_speed_of_gust": 13.7,
                          "wind_speed_percentile_10": 5.3,
                          "wind_speed_percentile_90": 7.6
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 11.8
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.3,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 30.8,
                          "probability_of_thunder": 1.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": 1.2,
                          "air_temperature_min": -2.3,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 7.1
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-12T13:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1015.5,
                          "air_temperature": 1.2,
                          "air_temperature_percentile_10": -0.8,
                          "air_temperature_percentile_90": 2.5,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 98.2,
                          "cloud_area_fraction_medium": 99.3,
                          "dew_point_temperature": -0.6,
                          "fog_area_fraction": 1.0,
                          "relative_humidity": 88.4,
                          "ultraviolet_index_clear_sky": 0.1,
                          "wind_from_direction": 318.5,
                          "wind_speed": 7.0,
                          "wind_speed_of_gust": 13.3,
                          "wind_speed_percentile_10": 5.7,
                          "wind_speed_percentile_90": 7.2
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.2,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 20.8,
                          "probability_of_thunder": 0.8
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-12T14:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1016.5,
                          "air_temperature": 1.1,
                          "air_temperature_percentile_10": -1.4,
                          "air_temperature_percentile_90": 2.6,
                          "cloud_area_fraction": 99.9,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 99.0,
                          "cloud_area_fraction_medium": 80.0,
                          "dew_point_temperature": -0.4,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 91.3,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 327.6,
                          "wind_speed": 6.2,
                          "wind_speed_of_gust": 12.7,
                          "wind_speed_percentile_10": 4.8,
                          "wind_speed_percentile_90": 7.5
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 6.0,
                          "probability_of_thunder": 0.8
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-12T15:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1017.8,
                          "air_temperature": 1.1,
                          "air_temperature_percentile_10": -2.4,
                          "air_temperature_percentile_90": 2.8,
                          "cloud_area_fraction": 95.4,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 91.3,
                          "cloud_area_fraction_medium": 47.1,
                          "dew_point_temperature": -1.5,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 84.1,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 349.7,
                          "wind_speed": 5.9,
                          "wind_speed_of_gust": 11.2,
                          "wind_speed_percentile_10": 4.5,
                          "wind_speed_percentile_90": 6.5
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 5.5,
                          "probability_of_thunder": 0.7
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-12T16:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1019.0,
                          "air_temperature": -0.4,
                          "air_temperature_percentile_10": -2.9,
                          "air_temperature_percentile_90": 2.6,
                          "cloud_area_fraction": 95.9,
                          "cloud_area_fraction_high": 0.6,
                          "cloud_area_fraction_low": 95.9,
                          "cloud_area_fraction_medium": 0.1,
                          "dew_point_temperature": -3.6,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 80.3,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 19.4,
                          "wind_speed": 5.9,
                          "wind_speed_of_gust": 11.1,
                          "wind_speed_percentile_10": 3.9,
                          "wind_speed_percentile_90": 6.1
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 1.6,
                          "probability_of_thunder": 0.6
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-12T17:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1020.5,
                          "air_temperature": -1.7,
                          "air_temperature_percentile_10": -3.1,
                          "air_temperature_percentile_90": 2.2,
                          "cloud_area_fraction": 92.0,
                          "cloud_area_fraction_high": 17.6,
                          "cloud_area_fraction_low": 90.7,
                          "cloud_area_fraction_medium": 0.0,
                          "dew_point_temperature": -4.9,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 80.4,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 23.3,
                          "wind_speed": 3.8,
                          "wind_speed_of_gust": 10.7,
                          "wind_speed_percentile_10": 2.8,
                          "wind_speed_percentile_90": 5.2
                        }
                      },
                      "next_1_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 0.3,
                          "probability_of_thunder": 0.5
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-12T18:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1020.8,
                          "air_temperature": -2.3,
                          "air_temperature_percentile_10": -3.7,
                          "air_temperature_percentile_90": 1.8,
                          "cloud_area_fraction": 71.6,
                          "cloud_area_fraction_high": 28.5,
                          "cloud_area_fraction_low": 59.7,
                          "cloud_area_fraction_medium": 0.0,
                          "dew_point_temperature": -5.1,
                          "fog_area_fraction": 0.0,
                          "relative_humidity": 83.3,
                          "ultraviolet_index_clear_sky": 0.0,
                          "wind_from_direction": 21.6,
                          "wind_speed": 3.0,
                          "wind_speed_of_gust": 7.0,
                          "wind_speed_percentile_10": 2.4,
                          "wind_speed_percentile_90": 4.5
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 17.6
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -1.2,
                          "air_temperature_min": -4.8,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 5.9
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-13T00:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1020.7,
                          "air_temperature": -4.8,
                          "air_temperature_percentile_10": -6.9,
                          "air_temperature_percentile_90": -0.9,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 100.0,
                          "cloud_area_fraction_low": 52.0,
                          "cloud_area_fraction_medium": 94.9,
                          "dew_point_temperature": -6.7,
                          "relative_humidity": 88.7,
                          "wind_from_direction": 198.8,
                          "wind_speed": 3.5,
                          "wind_speed_percentile_10": 1.6,
                          "wind_speed_percentile_90": 4.6
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "lightsnow",
                          "symbol_confidence": "uncertain"
                        },
                        "details": {
                          "probability_of_precipitation": 41.2
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -1.2,
                          "air_temperature_min": -5.4,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 15.7
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-13T06:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1012.5,
                          "air_temperature": -1.2,
                          "air_temperature_percentile_10": -3.8,
                          "air_temperature_percentile_90": 0.3,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 100.0,
                          "cloud_area_fraction_low": 81.2,
                          "cloud_area_fraction_medium": 100.0,
                          "dew_point_temperature": -3.7,
                          "relative_humidity": 84.7,
                          "wind_from_direction": 197.0,
                          "wind_speed": 5.9,
                          "wind_speed_percentile_10": 4.8,
                          "wind_speed_percentile_90": 8.2
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "lightsnow",
                          "symbol_confidence": "uncertain"
                        },
                        "details": {
                          "probability_of_precipitation": 49.0
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "snow"
                        },
                        "details": {
                          "air_temperature_max": -0.9,
                          "air_temperature_min": -1.9,
                          "precipitation_amount": 2.0,
                          "precipitation_amount_max": 2.5,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 33.3
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-13T12:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1002.6,
                          "air_temperature": -1.1,
                          "air_temperature_percentile_10": -2.3,
                          "air_temperature_percentile_90": -0.1,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 100.0,
                          "cloud_area_fraction_low": 100.0,
                          "cloud_area_fraction_medium": 100.0,
                          "dew_point_temperature": -2.8,
                          "relative_humidity": 89.5,
                          "wind_from_direction": 202.4,
                          "wind_speed": 5.0,
                          "wind_speed_percentile_10": 2.8,
                          "wind_speed_percentile_90": 7.1
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 31.4
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -0.9,
                          "air_temperature_min": -1.6,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.5,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 25.5
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-13T18:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 996.5,
                          "air_temperature": -1.1,
                          "air_temperature_percentile_10": -2.5,
                          "air_temperature_percentile_90": 0.3,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 63.3,
                          "cloud_area_fraction_low": 100.0,
                          "cloud_area_fraction_medium": 83.6,
                          "dew_point_temperature": -2.3,
                          "relative_humidity": 93.1,
                          "wind_from_direction": 253.5,
                          "wind_speed": 4.0,
                          "wind_speed_percentile_10": 2.7,
                          "wind_speed_percentile_90": 4.9
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 19.6
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -0.1,
                          "air_temperature_min": -1.7,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 13.7
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-14T00:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 993.7,
                          "air_temperature": -1.2,
                          "air_temperature_percentile_10": -4.6,
                          "air_temperature_percentile_90": 0.1,
                          "cloud_area_fraction": 99.2,
                          "cloud_area_fraction_high": 4.7,
                          "cloud_area_fraction_low": 57.8,
                          "cloud_area_fraction_medium": 46.1,
                          "dew_point_temperature": -2.7,
                          "relative_humidity": 91.3,
                          "wind_from_direction": 287.8,
                          "wind_speed": 4.6,
                          "wind_speed_percentile_10": 3.3,
                          "wind_speed_percentile_90": 5.1
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 15.7
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -0.5,
                          "air_temperature_min": -2.9,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 7.8
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-14T06:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 990.9,
                          "air_temperature": -2.7,
                          "air_temperature_percentile_10": -6.1,
                          "air_temperature_percentile_90": 0.2,
                          "cloud_area_fraction": 99.2,
                          "cloud_area_fraction_high": 37.5,
                          "cloud_area_fraction_low": 64.8,
                          "cloud_area_fraction_medium": 14.8,
                          "dew_point_temperature": -4.1,
                          "relative_humidity": 91.3,
                          "wind_from_direction": 287.0,
                          "wind_speed": 4.6,
                          "wind_speed_percentile_10": 3.4,
                          "wind_speed_percentile_90": 4.9
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 15.7
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -1.2,
                          "air_temperature_min": -4.4,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 7.8
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-14T12:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 990.0,
                          "air_temperature": -3.3,
                          "air_temperature_percentile_10": -6.4,
                          "air_temperature_percentile_90": -1.5,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 28.9,
                          "cloud_area_fraction_low": 98.0,
                          "cloud_area_fraction_medium": 63.7,
                          "dew_point_temperature": -5.9,
                          "relative_humidity": 84.0,
                          "wind_from_direction": 289.7,
                          "wind_speed": 4.3,
                          "wind_speed_percentile_10": 1.9,
                          "wind_speed_percentile_90": 5.2
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 23.5
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -3.3,
                          "air_temperature_min": -5.4,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 9.8
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-14T18:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 990.4,
                          "air_temperature": -4.7,
                          "air_temperature_percentile_10": -7.8,
                          "air_temperature_percentile_90": -3.1,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 55.5,
                          "cloud_area_fraction_low": 88.3,
                          "cloud_area_fraction_medium": 96.5,
                          "dew_point_temperature": -6.8,
                          "relative_humidity": 86.7,
                          "wind_from_direction": 340.5,
                          "wind_speed": 4.5,
                          "wind_speed_percentile_10": 1.9,
                          "wind_speed_percentile_90": 5.0
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 25.5
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -3.6,
                          "air_temperature_min": -6.2,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 17.6
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-15T00:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 991.0,
                          "air_temperature": -6.2,
                          "air_temperature_percentile_10": -9.7,
                          "air_temperature_percentile_90": -5.0,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 98.4,
                          "cloud_area_fraction_low": 91.4,
                          "cloud_area_fraction_medium": 96.1,
                          "dew_point_temperature": -8.4,
                          "relative_humidity": 86.7,
                          "wind_from_direction": 331.1,
                          "wind_speed": 4.5,
                          "wind_speed_percentile_10": 2.3,
                          "wind_speed_percentile_90": 5.9
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "uncertain"
                        },
                        "details": {
                          "probability_of_precipitation": 15.7
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -6.1,
                          "air_temperature_min": -9.9,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 13.7
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-15T06:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 991.2,
                          "air_temperature": -8.7,
                          "air_temperature_percentile_10": -13.6,
                          "air_temperature_percentile_90": -5.6,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 94.5,
                          "cloud_area_fraction_low": 71.9,
                          "cloud_area_fraction_medium": 99.2,
                          "dew_point_temperature": -11.2,
                          "relative_humidity": 84.7,
                          "wind_from_direction": 332.7,
                          "wind_speed": 4.6,
                          "wind_speed_percentile_10": 3.0,
                          "wind_speed_percentile_90": 5.6
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "uncertain"
                        },
                        "details": {
                          "probability_of_precipitation": 13.7
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -7.1,
                          "air_temperature_min": -11.2,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 7.8
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-15T12:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 993.7,
                          "air_temperature": -9.9,
                          "air_temperature_percentile_10": -14.5,
                          "air_temperature_percentile_90": -6.4,
                          "cloud_area_fraction": 99.2,
                          "cloud_area_fraction_high": 78.1,
                          "cloud_area_fraction_low": 68.0,
                          "cloud_area_fraction_medium": 45.3,
                          "dew_point_temperature": -12.9,
                          "relative_humidity": 81.6,
                          "wind_from_direction": 288.5,
                          "wind_speed": 3.9,
                          "wind_speed_percentile_10": 2.0,
                          "wind_speed_percentile_90": 5.3
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 13.7
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -9.1,
                          "air_temperature_min": -12.3,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 9.8
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-15T18:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 994.5,
                          "air_temperature": -11.4,
                          "air_temperature_percentile_10": -14.9,
                          "air_temperature_percentile_90": -8.4,
                          "cloud_area_fraction": 73.0,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 0.0,
                          "cloud_area_fraction_medium": 0.0,
                          "dew_point_temperature": -14.0,
                          "relative_humidity": 83.8,
                          "wind_from_direction": 290.4,
                          "wind_speed": 4.2,
                          "wind_speed_percentile_10": 2.1,
                          "wind_speed_percentile_90": 5.1
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 11.8
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "air_temperature_max": -9.9,
                          "air_temperature_min": -12.4,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 7.8
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-16T00:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 995.7,
                          "air_temperature": -12.4,
                          "air_temperature_percentile_10": -15.4,
                          "air_temperature_percentile_90": -8.2,
                          "cloud_area_fraction": 8.2,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 0.0,
                          "cloud_area_fraction_medium": 0.8,
                          "dew_point_temperature": -15.0,
                          "relative_humidity": 83.3,
                          "wind_from_direction": 300.2,
                          "wind_speed": 4.1,
                          "wind_speed_percentile_10": 2.2,
                          "wind_speed_percentile_90": 4.8
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "certain"
                        },
                        "details": {
                          "probability_of_precipitation": 9.8
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "clearsky_night"
                        },
                        "details": {
                          "air_temperature_max": -12.4,
                          "air_temperature_min": -16.8,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 5.9
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-16T06:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 996.5,
                          "air_temperature": -15.8,
                          "air_temperature_percentile_10": -17.8,
                          "air_temperature_percentile_90": -10.1,
                          "cloud_area_fraction": 88.7,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 22.7,
                          "cloud_area_fraction_medium": 28.9,
                          "dew_point_temperature": -18.0,
                          "relative_humidity": 86.6,
                          "wind_from_direction": 292.1,
                          "wind_speed": 4.0,
                          "wind_speed_percentile_10": 1.5,
                          "wind_speed_percentile_90": 4.8
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 11.8
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -11.2,
                          "air_temperature_min": -16.1,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 5.9
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-16T12:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 996.9,
                          "air_temperature": -12.5,
                          "air_temperature_percentile_10": -17.5,
                          "air_temperature_percentile_90": -9.3,
                          "cloud_area_fraction": 10.9,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 0.0,
                          "cloud_area_fraction_medium": 0.0,
                          "dew_point_temperature": -15.8,
                          "relative_humidity": 78.8,
                          "wind_from_direction": 284.8,
                          "wind_speed": 2.6,
                          "wind_speed_percentile_10": 1.4,
                          "wind_speed_percentile_90": 4.6
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 19.6
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "clearsky_day"
                        },
                        "details": {
                          "air_temperature_max": -12.5,
                          "air_temperature_min": -16.0,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 7.8
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-16T18:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 998.3,
                          "air_temperature": -15.3,
                          "air_temperature_percentile_10": -16.7,
                          "air_temperature_percentile_90": -7.3,
                          "cloud_area_fraction": 54.7,
                          "cloud_area_fraction_high": 1.6,
                          "cloud_area_fraction_low": 0.0,
                          "cloud_area_fraction_medium": 1.6,
                          "dew_point_temperature": -18.0,
                          "relative_humidity": 83.1,
                          "wind_from_direction": 286.5,
                          "wind_speed": 3.5,
                          "wind_speed_percentile_10": 1.4,
                          "wind_speed_percentile_90": 4.7
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "uncertain"
                        },
                        "details": {
                          "probability_of_precipitation": 25.5
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_night"
                        },
                        "details": {
                          "air_temperature_max": -10.9,
                          "air_temperature_min": -15.3,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 13.7
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-17T00:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 998.0,
                          "air_temperature": -12.8,
                          "air_temperature_percentile_10": -17.2,
                          "air_temperature_percentile_90": -6.2,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 93.0,
                          "cloud_area_fraction_low": 93.7,
                          "cloud_area_fraction_medium": 97.7,
                          "dew_point_temperature": -15.0,
                          "relative_humidity": 86.0,
                          "wind_from_direction": 296.6,
                          "wind_speed": 3.1,
                          "wind_speed_percentile_10": 1.5,
                          "wind_speed_percentile_90": 4.9
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "uncertain"
                        },
                        "details": {
                          "probability_of_precipitation": 25.5
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -9.7,
                          "air_temperature_min": -14.8,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 15.7
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-17T06:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 997.0,
                          "air_temperature": -10.6,
                          "air_temperature_percentile_10": -19.2,
                          "air_temperature_percentile_90": -5.8,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 28.1,
                          "cloud_area_fraction_low": 85.2,
                          "cloud_area_fraction_medium": 84.4,
                          "dew_point_temperature": -12.8,
                          "relative_humidity": 86.9,
                          "wind_from_direction": 16.3,
                          "wind_speed": 3.7,
                          "wind_speed_percentile_10": 1.0,
                          "wind_speed_percentile_90": 7.1
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 21.6
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -8.2,
                          "air_temperature_min": -12.6,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 15.7
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-17T12:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 996.1,
                          "air_temperature": -10.2,
                          "air_temperature_percentile_10": -17.9,
                          "air_temperature_percentile_90": -4.5,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 17.2,
                          "cloud_area_fraction_low": 100.0,
                          "cloud_area_fraction_medium": 35.2,
                          "dew_point_temperature": -13.0,
                          "relative_humidity": 82.7,
                          "wind_from_direction": 298.9,
                          "wind_speed": 3.2,
                          "wind_speed_percentile_10": 1.4,
                          "wind_speed_percentile_90": 7.5
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "lightsnow",
                          "symbol_confidence": "uncertain"
                        },
                        "details": {
                          "probability_of_precipitation": 21.6
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -8.9,
                          "air_temperature_min": -11.5,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 13.7
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-17T18:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 996.9,
                          "air_temperature": -9.6,
                          "air_temperature_percentile_10": -18.1,
                          "air_temperature_percentile_90": -3.0,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 2.3,
                          "cloud_area_fraction_low": 83.2,
                          "cloud_area_fraction_medium": 77.3,
                          "dew_point_temperature": -11.9,
                          "relative_humidity": 85.6,
                          "wind_from_direction": 297.0,
                          "wind_speed": 4.3,
                          "wind_speed_percentile_10": 2.1,
                          "wind_speed_percentile_90": 6.1
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "lightsnowshowers_day",
                          "symbol_confidence": "uncertain"
                        },
                        "details": {
                          "probability_of_precipitation": 23.5
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "snow"
                        },
                        "details": {
                          "air_temperature_max": -6.9,
                          "air_temperature_min": -9.6,
                          "precipitation_amount": 1.9,
                          "precipitation_amount_max": 1.9,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 15.7
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-18T00:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 997.3,
                          "air_temperature": -8.9,
                          "air_temperature_percentile_10": -18.4,
                          "air_temperature_percentile_90": -3.2,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 60.2,
                          "cloud_area_fraction_low": 99.6,
                          "cloud_area_fraction_medium": 98.4,
                          "dew_point_temperature": -11.0,
                          "relative_humidity": 87.8,
                          "wind_from_direction": 298.6,
                          "wind_speed": 3.8,
                          "wind_speed_percentile_10": 2.0,
                          "wind_speed_percentile_90": 6.0
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 17.6
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -8.9,
                          "air_temperature_min": -12.2,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 13.7
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-18T06:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 999.5,
                          "air_temperature": -11.5,
                          "air_temperature_percentile_10": -20.0,
                          "air_temperature_percentile_90": -2.5,
                          "cloud_area_fraction": 37.1,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 9.0,
                          "cloud_area_fraction_medium": 0.0,
                          "dew_point_temperature": -14.0,
                          "relative_humidity": 84.5,
                          "wind_from_direction": 291.3,
                          "wind_speed": 3.9,
                          "wind_speed_percentile_10": 1.8,
                          "wind_speed_percentile_90": 5.7
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 25.5
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "fair_day"
                        },
                        "details": {
                          "air_temperature_max": -11.5,
                          "air_temperature_min": -18.7,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 15.7
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-18T12:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1003.9,
                          "air_temperature": -16.7,
                          "air_temperature_percentile_10": -19.0,
                          "air_temperature_percentile_90": -4.0,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 3.1,
                          "cloud_area_fraction_low": 100.0,
                          "cloud_area_fraction_medium": 66.4,
                          "dew_point_temperature": -19.6,
                          "relative_humidity": 82.1,
                          "wind_from_direction": 294.4,
                          "wind_speed": 4.2,
                          "wind_speed_percentile_10": 1.7,
                          "wind_speed_percentile_90": 5.0
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "cloudy",
                          "symbol_confidence": "uncertain"
                        },
                        "details": {
                          "probability_of_precipitation": 23.5
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -10.6,
                          "air_temperature_min": -16.7,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 15.7
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-18T18:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1003.1,
                          "air_temperature": -11.4,
                          "air_temperature_percentile_10": -17.8,
                          "air_temperature_percentile_90": -3.7,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 89.1,
                          "cloud_area_fraction_medium": 94.5,
                          "dew_point_temperature": -13.7,
                          "relative_humidity": 85.8,
                          "wind_from_direction": 297.0,
                          "wind_speed": 4.4,
                          "wind_speed_percentile_10": 2.7,
                          "wind_speed_percentile_90": 5.0
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 17.6
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -7.7,
                          "air_temperature_min": -11.4,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 15.7
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-19T00:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1000.2,
                          "air_temperature": -9.7,
                          "air_temperature_percentile_10": -18.6,
                          "air_temperature_percentile_90": -5.4,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 2.3,
                          "cloud_area_fraction_low": 96.1,
                          "cloud_area_fraction_medium": 86.7,
                          "dew_point_temperature": -11.8,
                          "relative_humidity": 87.1,
                          "wind_from_direction": 296.7,
                          "wind_speed": 4.0,
                          "wind_speed_percentile_10": 2.1,
                          "wind_speed_percentile_90": 5.0
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 17.6
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -9.0,
                          "air_temperature_min": -12.6,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 9.8
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-19T06:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1000.4,
                          "air_temperature": -10.6,
                          "air_temperature_percentile_10": -19.7,
                          "air_temperature_percentile_90": -6.0,
                          "cloud_area_fraction": 18.4,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 0.0,
                          "cloud_area_fraction_medium": 0.0,
                          "dew_point_temperature": -13.4,
                          "relative_humidity": 82.5,
                          "wind_from_direction": 289.7,
                          "wind_speed": 4.1,
                          "wind_speed_percentile_10": 2.1,
                          "wind_speed_percentile_90": 6.2
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 15.7
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "fair_day"
                        },
                        "details": {
                          "air_temperature_max": -10.6,
                          "air_temperature_min": -17.5,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 11.8
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-19T12:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1013.2,
                          "air_temperature": -15.5,
                          "air_temperature_percentile_10": -18.8,
                          "air_temperature_percentile_90": -4.4,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 71.9,
                          "cloud_area_fraction_low": 75.4,
                          "cloud_area_fraction_medium": 93.0,
                          "dew_point_temperature": -18.7,
                          "relative_humidity": 79.8,
                          "wind_from_direction": 298.0,
                          "wind_speed": 3.2,
                          "wind_speed_percentile_10": 1.5,
                          "wind_speed_percentile_90": 5.6
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 17.6
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -9.4,
                          "air_temperature_min": -15.5,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 11.8
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-19T18:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1004.3,
                          "air_temperature": -10.7,
                          "air_temperature_percentile_10": -17.5,
                          "air_temperature_percentile_90": -3.5,
                          "cloud_area_fraction": 31.2,
                          "cloud_area_fraction_high": 0.0,
                          "cloud_area_fraction_low": 2.3,
                          "cloud_area_fraction_medium": 0.0,
                          "dew_point_temperature": -13.1,
                          "relative_humidity": 85.0,
                          "wind_from_direction": 289.4,
                          "wind_speed": 4.3,
                          "wind_speed_percentile_10": 1.6,
                          "wind_speed_percentile_90": 6.3
                        }
                      },
                      "next_12_hours": {
                        "summary": {
                          "symbol_code": "partlycloudy_day",
                          "symbol_confidence": "somewhat certain"
                        },
                        "details": {
                          "probability_of_precipitation": 17.6
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "fair_night"
                        },
                        "details": {
                          "air_temperature_max": -10.7,
                          "air_temperature_min": -15.2,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 11.8
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-20T00:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1014.1,
                          "air_temperature": -14.9,
                          "air_temperature_percentile_10": -18.1,
                          "air_temperature_percentile_90": -3.3,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 89.8,
                          "cloud_area_fraction_low": 87.5,
                          "cloud_area_fraction_medium": 21.1,
                          "dew_point_temperature": -17.2,
                          "relative_humidity": 85.9,
                          "wind_from_direction": 288.3,
                          "wind_speed": 4.0,
                          "wind_speed_percentile_10": 1.6,
                          "wind_speed_percentile_90": 7.5
                        }
                      },
                      "next_6_hours": {
                        "summary": {
                          "symbol_code": "cloudy"
                        },
                        "details": {
                          "air_temperature_max": -11.6,
                          "air_temperature_min": -14.9,
                          "precipitation_amount": 0.0,
                          "precipitation_amount_max": 0.0,
                          "precipitation_amount_min": 0.0,
                          "probability_of_precipitation": 11.8
                        }
                      }
                    }
                  },
                  {
                    "time": "2024-01-20T06:00:00Z",
                    "data": {
                      "instant": {
                        "details": {
                          "air_pressure_at_sea_level": 1007.9,
                          "air_temperature": -12.0,
                          "air_temperature_percentile_10": -19.8,
                          "air_temperature_percentile_90": -1.1,
                          "cloud_area_fraction": 100.0,
                          "cloud_area_fraction_high": 42.6,
                          "cloud_area_fraction_low": 96.5,
                          "cloud_area_fraction_medium": 63.3,
                          "dew_point_temperature": -14.2,
                          "relative_humidity": 86.7,
                          "wind_from_direction": 293.4,
                          "wind_speed": 3.9,
                          "wind_speed_percentile_10": 1.5,
                          "wind_speed_percentile_90": 6.7
                        }
                      }
                    }
                  }
                ]
              }
            }
        """.trimIndent()
    }
}