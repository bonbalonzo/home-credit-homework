package ph.homecredit.homework.domains;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class WeatherLogDAOTest {

    @Autowired
    private WeatherLogDAO weatherLogDAO;

    @Before
    public void setUp(){

    }
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSave3Cities(){
        List<WeatherLog> actual = createCityWeather();
        weatherLogDAO.save(actual);
        Long actualId = actual.get(0).getId();
        String actualResponseId = actual.get(0).getResponseId();
        assertNotEquals(actualId, actual.get(1).getId());
        assertNotEquals(actualResponseId, actual.get(1).getResponseId());

        actualId = actual.get(1).getId();
        actualResponseId = actual.get(1).getResponseId();
        assertNotEquals(actualId, actual.get(2).getId());
        assertNotEquals(actualResponseId, actual.get(2).getResponseId());

        actualId = actual.get(2).getId();
        actualResponseId = actual.get(2).getResponseId();
        assertNotEquals(actualId, actual.get(0).getId());
        assertNotEquals(actualResponseId, actual.get(0).getResponseId());
    }

    @Test
    public void testSaveDuplicates(){
        List<WeatherLog> actual = createCityWeather();
        actual.addAll(createCityWeather());
        weatherLogDAO.save(actual);
        List<WeatherLog> weatherLogs = weatherLogDAO.findByLocationContaining("london");
        assertNotEquals(weatherLogs.get(0).getId(),weatherLogs.get(1).getId());
        assertNotEquals(weatherLogs.get(0).getResponseId(),weatherLogs.get(1).getResponseId());
    }

    private List<WeatherLog> createCityWeather(){
        List<WeatherLog> results = new ArrayList<>();
        results.
                add(new WeatherLog("london", "sunny", "14"));
        results.
                add(new WeatherLog("san francisco", "rainy", "21"));
        results.
                add(new WeatherLog("prague", "thunderstorm", "18"));
        return results;
    }
}
