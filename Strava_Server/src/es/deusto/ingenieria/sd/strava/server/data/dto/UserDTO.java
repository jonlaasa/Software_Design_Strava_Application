package es.deusto.ingenieria.sd.strava.server.data.dto;

import java.io.Serializable;
import java.util.Date;


//we decided to show the same as the USER (DOMAIN OBJECT) but without lists
//(sessions, challenges and active challenges)

//This class implements DTO pattern
public class UserDTO implements Serializable {	
	//This attribute is needed to implement the "Serializable" interface.
	private static final long serialVersionUID = 1L;	
	private String name;
	private String password;
	private String email;
	private Date birthdate;
	private double weight;
	private double height;
	private double maxHRate;
	private double rateHRest;
	private String provider;
	
	
	
	public UserDTO(String name, String password, String email, Date birthdate, double weight, double height,
			double maxHRate, double rateHRest,String provider) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.birthdate = birthdate;
		this.weight = weight;
		this.height = height;
		this.maxHRate = maxHRate;
		this.rateHRest = rateHRest;
		this.provider = provider;
		
		
	}
	
	
	
	public UserDTO() {
		super();
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double d) {
		this.height = d;
	}
	public double getMaxHRate() {
		return maxHRate;
	}
	public void setMaxHRate(double maxHRate) {
		this.maxHRate = maxHRate;
	}
	public double getRateHRest() {
		return rateHRest;
	}
	public void setRateHRest(double rateHRest) {
		this.rateHRest = rateHRest;
	}

	
	public String getProvider() {
		return provider;
	}



	public void setProvider(String provider) {
		this.provider = provider;
	}



	//WE SUMMARIZE AS NAME, EMAIL AND SIZE OF (SESSIONS, ACTIVE CHALLENGES AND CHALLENGES)
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		
		result.append(this.name);
		result.append(" - ");
		result.append(this.email);
		return result.toString();
	}
	
	//HOW TO COMPARE TWO USERS?? EMAIL
	
	@Override
	public boolean equals(Object obj) {
		//if both are USERS...
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			//COMPARE BY EMAIL
			return this.email.equals(((UserDTO)obj).email);
		}
		
		return false;
	}

}