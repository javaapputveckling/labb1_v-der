package linker;
/*
{
  "type": "Feature",
  "geometry": {
    "type": "Point",
    "coordinates": [
      9.58,
      60.1,
      496
    ]
  },
  "properties": {
    "meta": {
      "updated_at": "2023-11-08T12:40:21Z",
      "units": {
        "air_pressure_at_sea_level": "hPa",
        "air_temperature": "celsius",
        "cloud_area_fraction": "%",
        "preci pitation_amount": "mm",
        "relative_humidity": "%",
        "wind_from_direction": "degrees",
        "wind_speed": "m/s"
      }
    },
    "timeseries": [
      {
        "time": "2023-11-08T13:00:00Z",
        "data": {
          "instant": {
            "details": {
              "air_pressure_at_sea_level": 1004,
              "air_temperature": -0.7,
              "cloud_area_fraction": 5.6,
              "relative_humidity": 80,
              "wind_from_direction": 252.7,
              "wind_speed": 0.5
            }
          },
          "next_12_hours": {
            "summary": {
              "symbol_code": "partlycloudy_day"
            }
          },
          "next_1_hours": {
            "summary": {
              "symbol_code": "clearsky_day"
            },
            "details": {
              "precipitation_amount": 0
            }
          },
          "next_6_hours": {
            "summary": {
              "symbol_code": "partlycloudy_day"
            },
            "details": {
              "precipitation_amount": 0
            }
          }
        }
      }
    }
  }
}



VINDHASTIGHET, OM DET REGNAR ELLER INTE, TEMPERATUR, MOLNIGHET
*/
public class objectlinker {
    String link = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=60.10&lon=9.58";

}
