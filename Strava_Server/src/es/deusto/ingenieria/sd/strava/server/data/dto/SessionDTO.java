package es.deusto.ingenieria.sd.strava.server.data.dto;


import java.io.Serializable;
import java.util.Date;

public class SessionDTO implements Serializable{
	
	//as it happens in case of DTOs, they could save the same attributes that domain object does
	// in this case this will be like this

	private String title;
	private String sportType;
	private double distance;
	private Date startDate;
	private String startHour;
	private double duration;
	
	
	
	
	public SessionDTO(String title, String sportType, double distance, Date startDate, String startHour,
			double duration) {
		super();
		this.title = title;
		this.sportType = sportType;
		this.distance = distance;
		this.startDate = startDate;
		this.startHour = startHour;
		this.duration = duration;
	}
	
	




	public SessionDTO() {
		super();
	}


	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getSportType() {
		return sportType;
	}




	public void setSportType(String sportType) {
		this.sportType = sportType;
	}




	public double getDistance() {
		return distance;
	}




	public void setDistance(double distance) {
		this.distance = distance;
	}




	public Date getStartDate() {
		return startDate;
	}




	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}




	public String getStartHour() {
		return startHour;
	}




	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}




	public double getDuration() {
		return duration;
	}




	public void setDuration(double duration) {
		this.duration = duration;
	}




	@Override
	public String toString() {
		return "SessionDTO [title=" + title + ", sportType=" + sportType + ", distance=" + distance + ", startDate="
				+ startDate + ", startHour=" + startHour + ", duration=" + duration + "]";
	}
	
	

}
