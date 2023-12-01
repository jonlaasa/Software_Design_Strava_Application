package es.deusto.ingenieria.sd.strava.server.data.dto;


import java.io.Serializable;
import java.util.Date;

public class ChallengeDTO implements Serializable {

	
	//as it happens in case of DTOs, they could save the same attributes that domain object does
	// in this case this will be like this
	

	private String name;
	private Date startDate;
	private Date endDate;
	private double distance;
	private double goal;
	private String sportType;
	
	
	public ChallengeDTO(String name, Date startDate, Date endDate, double distance, double goal, String sportType) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.distance = distance;
		this.goal= goal;
		this.sportType = sportType;
	}
	
	
	public ChallengeDTO() {
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
	
	
	

}
