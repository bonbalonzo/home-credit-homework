package ph.homecredit.homework.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.List;

@Generated("com.robohorse.robopojogenerator")
public class CurrentWeatherResponseDTO {

	@JsonProperty("weather")
	private List<WeatherItem> weather;

	@JsonProperty("name")
	private String name;

	@JsonProperty("main")
	private Main main;

	@JsonProperty("id")
	private int id;

	@JsonProperty("sys")
	private Sys sys;


	public void setWeather(List<WeatherItem> weather){
		this.weather = weather;
	}

	public List<WeatherItem> getWeather(){
		return weather;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}


	public void setMain(Main main){
		this.main = main;
	}

	public Main getMain(){
		return main;
	}


	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setSys(Sys sys){
		this.sys = sys;
	}

	public Sys getSys(){
		return sys;
	}

	@Override
 	public String toString(){
		return 
			"CurrentWeatherResponseDTO{" +
			",weather = '" + weather + '\'' + 
			",name = '" + name + '\'' +
			",main = '" + main + '\'' +
			",id = '" + id + '\'' + 
			",sys = '" + sys + '\'' +
			"}";
		}
}