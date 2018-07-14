package ph.homecredit.homework.domains;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherLogDAO extends CrudRepository<WeatherLog, Long> {
    List<WeatherLog> findByLocationContaining(String location);
}
