package ph.homecredit.homework.interfaces;

import ph.homecredit.homework.interfaces.dtos.CityWeatherDTO;

import java.util.List;

public interface WeatherService {

    CityWeatherDTO getCurrentCityWeather(String cityName);

    List<CityWeatherDTO> logMaxFiveUniqueWeathers(List<CityWeatherDTO> currentWeather);
}
