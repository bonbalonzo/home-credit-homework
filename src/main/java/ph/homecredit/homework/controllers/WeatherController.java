package ph.homecredit.homework.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ph.homecredit.homework.interfaces.WeatherService;
import ph.homecredit.homework.interfaces.dtos.CityWeatherDTO;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private WeatherService service;

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON})
    public List<CityWeatherDTO> listWeathers(@RequestParam("cities") List<String> cities) {
        return retrieveWeathers(cities);
    }

    @PostMapping(
            path = "/log",
            produces = {MediaType.APPLICATION_JSON}
    )
    public List<CityWeatherDTO> logWeathers(@RequestBody List<CityWeatherDTO> cityWeathers){
        return service.logMaxFiveUniqueWeathers(cityWeathers);
    }

    private List<CityWeatherDTO> retrieveWeathers(List<String> cities) {
        List<CityWeatherDTO> results = new ArrayList<>();
        for (String city : cities) {
            CityWeatherDTO currentWeather = service.getCurrentCityWeather(city);
            results.add(currentWeather);
        }
        return results;
    }
}
