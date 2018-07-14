package ph.homecredit.homework.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;


@Generated("com.robohorse.robopojogenerator")
public class WeatherItem{

	@JsonProperty("description")
	private String description;

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	@Override
 	public String toString(){
		return 
			"WeatherItem{" +
			"description = '" + description + '\'' +
			"}";
		}
}