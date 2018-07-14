package ph.homecredit.homework.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;


@Generated("com.robohorse.robopojogenerator")
public class Main{

	@JsonProperty("temp")
	private double temp;

	public void setTemp(double temp){
		this.temp = temp;
	}

	public double getTemp(){
		return temp;
	}

	@Override
 	public String toString(){
		return 
			"Main{" + 
			"temp = '" + temp + '\'' +
			"}";
		}
}