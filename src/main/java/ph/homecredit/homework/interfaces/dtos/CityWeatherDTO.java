package ph.homecredit.homework.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CityWeatherDTO implements Serializable {
    @JsonProperty("location")
    private String location;

    @JsonProperty("actual weather")
    private String actualWeather;

    @JsonProperty("temperature")
    private String temperature;

    public CityWeatherDTO() {
        location = "";
        actualWeather = "";
        temperature = "";
    }

    public CityWeatherDTO(String location, String actualWeather, String temperature) {
        this.location = location;
        this.actualWeather = actualWeather;
        this.temperature = temperature;
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
        return "CityWeatherDTO{" +
                "location='" + location + '\'' +
                ", actualWeather='" + actualWeather + '\'' +
                ", temperature='" + temperature + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityWeatherDTO)) return false;

        CityWeatherDTO that = (CityWeatherDTO) o;

        if (getLocation() != null ? !getLocation().equals(that.getLocation()) : that.getLocation() != null)
            return false;
        if (getActualWeather() != null ? !getActualWeather().equals(that.getActualWeather()) : that.getActualWeather() != null)
            return false;
        return getTemperature() != null ? getTemperature().equals(that.getTemperature()) : that.getTemperature() == null;
    }

    @Override
    public int hashCode() {
        int result = getLocation() != null ? getLocation().hashCode() : 0;
        result = 31 * result + (getActualWeather() != null ? getActualWeather().hashCode() : 0);
        result = 31 * result + (getTemperature() != null ? getTemperature().hashCode() : 0);
        return result;
    }
}
