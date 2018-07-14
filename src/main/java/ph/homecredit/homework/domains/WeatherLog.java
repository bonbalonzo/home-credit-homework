package ph.homecredit.homework.domains;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "WeatherLog")
@SequenceGenerator(name = "WeatherLog_SEQ", initialValue = 1, allocationSize = 1)
public class WeatherLog implements Serializable{
    @Id
    @GeneratedValue(
            generator = "WeatherLog_SEQ",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @Column(name = "ResponseId")
    private String responseId;

    @Column(name = "Location")
    private String location;

    @Column(name = "ActualWeather")
    private String actualWeather;

    @Column(name = "Temperature")
    private String temperature;

    @Column(name = "DTimeInserted")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dtimeInserted;

    public WeatherLog() {
        //
    }

    @PrePersist
    public void prePersist(){
        this.responseId = UUID.randomUUID().toString();
    }

    public WeatherLog(String location, String actualWeather, String temperature) {
        this.location = location;
        this.actualWeather = actualWeather;
        this.temperature = temperature;
    }

    public Long getId() {
        return id;
    }

    public String getResponseId() {
        return responseId;
    }

    public Date getDtimeInserted() {
        return dtimeInserted;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getActualWeather() {
        return actualWeather;
    }

    public void setActualWeather(String actualWeather) {
        this.actualWeather = actualWeather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "WeatherLog{" +
                "location='" + location + '\'' +
                ", actualWeather='" + actualWeather + '\'' +
                ", temperature='" + temperature + '\'' +
                '}';
    }

}
