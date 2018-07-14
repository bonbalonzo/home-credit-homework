package ph.homecredit.homework.contorllers;

import org.hamcrest.CoreMatchers;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ph.homecredit.homework.HomeworkApplication;
import ph.homecredit.homework.interfaces.dtos.CityWeatherDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = HomeworkApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherControllerIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testListWeathersLondon() throws JSONException {

        ResponseEntity<List<CityWeatherDTO>> response = restTemplate.exchange(
                createURLWithPort("/weather?cities=london"), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CityWeatherDTO>>(){});

        List<CityWeatherDTO> weatherResponse = response.getBody();

        assertEquals("size:", 1, weatherResponse.size());
        assertThat(weatherResponse.get(0).getLocation().toLowerCase(),
                CoreMatchers.containsString("london"));
    }

    @Test
    public void testListWeathersSanFrancisco() throws JSONException {

        ResponseEntity<List<CityWeatherDTO>> response = restTemplate.exchange(
                createURLWithPort("/weather?cities=san francisco"),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CityWeatherDTO>>(){});

        List<CityWeatherDTO> weatherResponse = response.getBody();

        assertEquals("size:", 1, weatherResponse.size());
        assertThat(weatherResponse.get(0).getLocation().toLowerCase(),
                CoreMatchers.containsString("san francisco"));
    }

    @Test
    public void testListWeathersPrague() throws JSONException {

        ResponseEntity<List<CityWeatherDTO>> response = restTemplate.exchange(
                createURLWithPort("/weather?cities=prague"), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CityWeatherDTO>>(){});

        List<CityWeatherDTO> weatherResponse = response.getBody();
        assertEquals("size:", 1, weatherResponse.size());
        assertThat(weatherResponse.get(0).getLocation().toLowerCase(),
                CoreMatchers.containsString("prague"));
    }

    @Test
    public void testListWeathers3Cities() throws JSONException {

        ResponseEntity<List<CityWeatherDTO>> response = restTemplate.exchange(
                createURLWithPort("/weather?cities=london, san francisco, prague"),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CityWeatherDTO>>(){});

        List<CityWeatherDTO> weatherResponse = response.getBody();
        assertEquals("size:", 3, weatherResponse.size());
    }

    @Test
    public void testListWeathersLondonSanFranciscoPrague() throws JSONException {

        ResponseEntity<List<CityWeatherDTO>> response = restTemplate.exchange(
                createURLWithPort("/weather?cities=london,san francisco, prague"),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CityWeatherDTO>>(){});

        List<CityWeatherDTO> weatherResponse = response.getBody();

        StringBuilder actualCities = new StringBuilder();
        for(CityWeatherDTO cityWeather : weatherResponse){
            actualCities.append(cityWeather.getLocation().toLowerCase()+ ", ");
        }
        assertThat(actualCities.toString(),
                CoreMatchers.containsString("london"));
        assertThat(actualCities.toString(),
                CoreMatchers.containsString("san francisco"));
        assertThat(actualCities.toString(),
                CoreMatchers.containsString("prague"));
    }
    @Test
    public void testLogWeathers() throws JSONException {
        List<CityWeatherDTO> requestBody = new ArrayList<>();
        requestBody.add(new CityWeatherDTO("Chicago,US","light rain","23.88"));
        requestBody.add(new CityWeatherDTO("London,GB","haze","15.41"));
        requestBody.add(new CityWeatherDTO("London,GB","haze","15.41"));
        requestBody.add(new CityWeatherDTO("Manila,PH","broken clouds","31.0"));
        requestBody.add(new CityWeatherDTO("Manila,PH","broken clouds","31.0"));
        requestBody.add(new CityWeatherDTO("Manila,PH","broken clouds","31.0"));
        requestBody.add(new CityWeatherDTO("Tokyo,JP","scattered clouds","33.59"));
        requestBody.add(new CityWeatherDTO("Tokyo,JP","scattered clouds","33.59"));
        requestBody.add(new CityWeatherDTO("Tokyo,JP","scattered clouds","33.59"));
        requestBody.add(new CityWeatherDTO("San Francisco,US","mist","17.09"));
        requestBody.add(new CityWeatherDTO("Prague,CZ","clear sky","19.0"));
        HttpEntity<List<CityWeatherDTO>> request = new HttpEntity<>(requestBody);
        ResponseEntity<List<CityWeatherDTO>> response = restTemplate.exchange(
                createURLWithPort("/weather/log"),
                HttpMethod.POST, request,
                new ParameterizedTypeReference<List<CityWeatherDTO>>(){});

        List<CityWeatherDTO> weatherResponse = response.getBody();
        assertEquals(5,weatherResponse.size());

        StringBuilder actualCities = new StringBuilder();
        for(CityWeatherDTO cityWeather : weatherResponse){
            actualCities.append(cityWeather.getLocation().toLowerCase()+ ", ");
        }
        assertThat(actualCities.toString(),
                CoreMatchers.containsString("london"));
        assertThat(actualCities.toString(),
                CoreMatchers.containsString("san francisco"));
        assertThat(actualCities.toString(),
                CoreMatchers.containsString("prague"));
        assertThat(actualCities.toString(),
                CoreMatchers.containsString("manila"));
        assertThat(actualCities.toString(),
                CoreMatchers.containsString("tokyo"));
        assertThat(actualCities.toString(),
                CoreMatchers.not(CoreMatchers.containsString("chicago")));
    }@Test

    public void testLogDupplicateWeathers() throws JSONException {
        List<CityWeatherDTO> requestBody = new ArrayList<>();
        requestBody.add(new CityWeatherDTO("London,GB","haze","15.41"));
        requestBody.add(new CityWeatherDTO("London,GB","haze","15.41"));
        requestBody.add(new CityWeatherDTO("London,GB","haze","15.41"));
        requestBody.add(new CityWeatherDTO("London,GB","haze","15.41"));
        requestBody.add(new CityWeatherDTO("London,GB","haze","15.41"));
        requestBody.add(new CityWeatherDTO("London,GB","haze","15.41"));
        requestBody.add(new CityWeatherDTO("London,GB","haze","15.41"));
        requestBody.add(new CityWeatherDTO("London,GB","haze","15.41"));
        requestBody.add(new CityWeatherDTO("London,GB","haze","15.41"));
        requestBody.add(new CityWeatherDTO("London,GB","haze","15.41"));
        HttpEntity<List<CityWeatherDTO>> request = new HttpEntity<>(requestBody);
        ResponseEntity<List<CityWeatherDTO>> response = restTemplate.exchange(
                createURLWithPort("/weather/log"),
                HttpMethod.POST, request,
                new ParameterizedTypeReference<List<CityWeatherDTO>>(){});

        List<CityWeatherDTO> weatherResponse = response.getBody();
        assertEquals(1,weatherResponse.size());

        assertThat(weatherResponse.get(0).getLocation().toLowerCase(),
                CoreMatchers.containsString("london"));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
