package ph.homecredit.homework.service;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ph.homecredit.homework.interfaces.WeatherService;
import ph.homecredit.homework.interfaces.dtos.CityWeatherDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherServiceTest {
    @Autowired
    private WeatherService service;

    @Test
    public void testGetCurrentCityWeatherLondon(){
        CityWeatherDTO actual = service.getCurrentCityWeather("london");
        assertThat(actual.getLocation().toLowerCase(), CoreMatchers.containsString("london"));
    }

    @Test
    public void testGetCurrentCityWeatherManila(){
        CityWeatherDTO actual = service.getCurrentCityWeather("manila");
        assertThat(actual.getLocation().toLowerCase(), CoreMatchers.containsString("manila"));
    }
    @Test
    public void testGetCurrentCityWeatherNull(){
        CityWeatherDTO actual = service.getCurrentCityWeather("");
        assertEquals("city name", "", actual.getLocation());
    }

    @Test
    public void testLogMaxFiveUniqueWeathersFiveUniqueData(){
        List<CityWeatherDTO> request = new ArrayList<>();
        request.add(new CityWeatherDTO("Chicago,US","light rain","23.88"));
        request.add(new CityWeatherDTO("London,GB","haze","15.41"));
        request.add(new CityWeatherDTO("Manila,PH","broken clouds","31.0"));
        request.add(new CityWeatherDTO("San Francisco,US","mist","17.09"));
        request.add(new CityWeatherDTO("Prague,CZ","clear sky","19.0"));
        List<CityWeatherDTO> actual = service.logMaxFiveUniqueWeathers(request);
        assertEquals("Size", 5, actual.size());
        assertEquals("city name", 5, actual.size());
    }

    @Test
    public void testLogMaxFiveUniqueWeathersDuplicateData(){
        List<CityWeatherDTO> request = new ArrayList<>();
        request.add(new CityWeatherDTO("Chicago,US","light rain","23.88"));
        request.add(new CityWeatherDTO("Chicago,US","light rain","23.88"));
        request.add(new CityWeatherDTO("Chicago,US","light rain","23.88"));
        request.add(new CityWeatherDTO("Chicago,US","light rain","23.88"));
        request.add(new CityWeatherDTO("Chicago,US","light rain","23.88"));
        List<CityWeatherDTO> actual = service.logMaxFiveUniqueWeathers(request);
        assertEquals("Size", 1, actual.size());
        assertEquals("city name", "Chicago,US", actual.get(0).getLocation());
    }

    @Test
    public void testLogMaxFiveUniqueWeathersEightUniqueData(){
        List<CityWeatherDTO> request = new ArrayList<>();
        request.add(new CityWeatherDTO("Chicago,US","light rain","23.88"));
        request.add(new CityWeatherDTO("Naga,PH","broken clouds","31.0"));
        request.add(new CityWeatherDTO("New York,US","mist","17.09"));
        request.add(new CityWeatherDTO("Brno,CZ","clear sky","19.0"));
        request.add(new CityWeatherDTO("London,GB","haze","15.41"));
        request.add(new CityWeatherDTO("Manila,PH","broken clouds","31.0"));
        request.add(new CityWeatherDTO("San Francisco,US","mist","17.09"));
        request.add(new CityWeatherDTO("Prague,CZ","clear sky","19.0"));
        List<CityWeatherDTO> actual = service.logMaxFiveUniqueWeathers(request);
        assertEquals("Size", 5, actual.size());
        assertEquals("city name", "Brno,CZ", actual.get(0).getLocation());
    }
    @Test
    public void testLogMaxFiveUniqueWeathersEightUniqueOneDuplicateData(){
        List<CityWeatherDTO> request = new ArrayList<>();
        request.add(new CityWeatherDTO("Chicago,US","light rain","23.88"));
        request.add(new CityWeatherDTO("Naga,PH","broken clouds","31.0"));
        request.add(new CityWeatherDTO("New York,US","mist","17.09"));
        request.add(new CityWeatherDTO("Brno,CZ","clear sky","19.0"));
        request.add(new CityWeatherDTO("London,GB","haze","15.41"));
        request.add(new CityWeatherDTO("Manila,PH","broken clouds","31.0"));
        request.add(new CityWeatherDTO("San Francisco,US","mist","17.09"));
        request.add(new CityWeatherDTO("Prague,CZ","clear sky","19.0"));
        request.add(new CityWeatherDTO("Prague,CZ","clear sky","19.0"));
        List<CityWeatherDTO> actual = service.logMaxFiveUniqueWeathers(request);
        assertEquals("Size", 5, actual.size());
        assertEquals("city name", "Brno,CZ", actual.get(0).getLocation());
    }
}
