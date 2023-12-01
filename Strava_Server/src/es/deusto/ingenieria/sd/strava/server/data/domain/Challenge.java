package es.deusto.ingenieria.sd.strava.server.data.domain;


import java.util.Date;


import javax.persistence.Entity;

import org.datanucleus.api.jpa.annotations.DatastoreId;


@Entity
@DatastoreId
public class Challenge {
	
	private String name;
	
	//YYYY-MM-DD FORMAT
	private Date startDate;
	private Date endDate;
	private double goal;
	private double distance;
	private String sportType;
	public Challenge(String name, Date startDate, Date endDate, double distance, double goal, String sportType) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.distance = distance;
		this.goal=goal;
		this.sportType = sportType;
	}

	
	
	
	public Challenge() {
		super();
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getSportType() {
		return sportType;
	}
	public void setSportType(String sportType) {
		this.sportType = sportType;
	}
	public double getGoal() {
		return goal;
	}
	public void setGoal(double goal) {
		this.goal = goal;
	}




	@Override
	public String toString() {
		return "Challenge [name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", distance="
				+ distance + ", sportType=" + sportType + "]";
	}
	

	@Override
	public boolean equals(Object obj) {
		//if both are CHALLENGES...
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			//COMPARE BY NAME
			return this.name.equals(((Challenge)obj).name);
		}
		
		return false;
	}

	
}