package ph.homecredit.homework.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ph.homecredit.homework.domains.WeatherLog;
import ph.homecredit.homework.domains.WeatherLogDAO;
import ph.homecredit.homework.interfaces.WeatherService;
import ph.homecredit.homework.interfaces.dtos.CityWeatherDTO;
import ph.homecredit.homework.interfaces.dtos.CurrentWeatherResponseDTO;
import ph.homecredit.homework.interfaces.dtos.WeatherItem;

import java.util.*;

@Service
public class WeatherServicesImpl implements WeatherService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServicesImpl.class);
    private RestTemplate restTemplate;
    @Autowired
    private WeatherLogDAO repo;

    @Value("${weather_api_url}")
    private String OPEN_WEATHER_MAP_URL;
    @Value("${api_key}")
    private String API_KEY;
    @Value("${temp_unit}")
    private String TEMP_UNIT;

    @Autowired
    public WeatherServicesImpl() {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
    }

    @Override
    public CityWeatherDTO getCurrentCityWeather(String cityName) {
        if(cityName == null || cityName.isEmpty()){
            return new CityWeatherDTO();
        }
        Map<String, String> apiVariables = new HashMap<>();
        apiVariables.put("city", cityName);
        apiVariables.put("units", TEMP_UNIT);
        apiVariables.put("appid", API_KEY);

        CurrentWeatherResponseDTO currentWeather =
                restTemplate.getForObject(OPEN_WEATHER_MAP_URL,
                        CurrentWeatherResponseDTO.class, apiVariables);
        String location = currentWeather.getName() + "," + currentWeather.getSys().getCountry();
        String actualWeather = getActualWeather(currentWeather.getWeather());
        String temperature = Double.toString(currentWeather.getMain().getTemp());
        return new CityWeatherDTO(location,actualWeather,temperature);
    }

    private String getActualWeather(List<WeatherItem> weather) {
        if (weather == null || weather.isEmpty()) {
            return "";
        }
        if (weather.size() == 1) {
            return weather.get(0).getDescription();
        } else {
            StringBuilder appendWeathers = new StringBuilder(weather.get(0).getDescription());
            int weatherSize = weather.size();
            for (int index = 1; index < weatherSize - 1; index++) {
                appendWeathers.append(", " + weather.get(index).getDescription());
            }
            appendWeathers.append("& " + weather.get(weatherSize - 1).getDescription());
            return appendWeathers.toString();
        }
    }

    @Override
    @Transactional
    public List<CityWeatherDTO> logMaxFiveUniqueWeathers(List<CityWeatherDTO> currentWeathers) {
        List<WeatherLog> weatherLogs = reduceToFiveUniqueData(currentWeathers);
        repo.save(weatherLogs);
        return convertToCityWeatherDTO(weatherLogs);
    }

    private List<WeatherLog> reduceToFiveUniqueData(List<CityWeatherDTO> currentWeathers) {
        List<CityWeatherDTO> uniqueCurrentWeathers =
                new ArrayList<>(new LinkedHashSet<>(currentWeathers));
        List<WeatherLog> result = new ArrayList<>();
        if(uniqueCurrentWeathers.size() < 6) {
            for (CityWeatherDTO currentWeather : uniqueCurrentWeathers) {
                result.add(convertToWeatherLog(currentWeather));
            }
        } else{
            for(int index = uniqueCurrentWeathers.size()-5; index < uniqueCurrentWeathers.size(); index++){
                result.add(convertToWeatherLog(uniqueCurrentWeathers.get(index)));
            }
        }
        return result;
    }

    private WeatherLog convertToWeatherLog(CityWeatherDTO currentWeather) {
        return new WeatherLog(
                currentWeather.getLocation(),
                currentWeather.getActualWeather(),
                currentWeather.getTemperature());
    }
    private List<CityWeatherDTO> convertToCityWeatherDTO(List<WeatherLog> weatherLogs) {
        List<CityWeatherDTO> result = new ArrayList<>();
        for(WeatherLog weatherLog : weatherLogs){
            result.add(
                    new CityWeatherDTO(
                            weatherLog.getLocation(),
                            weatherLog.getActualWeather(),
                            weatherLog.getTemperature()));
        }
        return result;
    }
}
