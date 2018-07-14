package ph.homecredit.homework.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;


@Generated("com.robohorse.robopojogenerator")
public class Sys{

	@JsonProperty("country")
	private String country;

	@JsonProperty("id")
	private int id;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
	@Override
 	public String toString(){
		return 
			"Sys{" + 
			"country = '" + country + '\'' +
			",id = '" + id + '\'' +
			"}";
		}
}