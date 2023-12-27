package es.deusto.ingenieria.sd.strava.client.controller;

import java.util.Date;

import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;
import es.deusto.ingenieria.sd.strava.server.data.dto.SessionDTO;


//This class implements Controller pattern.
//Session
public class SessionController {	
	
	// Service Locator
	private ServiceLocator serviceLocator;


	public SessionController(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	

	
	public ServiceLocator getServiceLocator() {
		return serviceLocator;
	}
	
	//CREATE SESSION METHOD
	
	public void createSession(String title, String sportType,
			double distance, Date startDate, String startHour, double duration) {
		try {
			SessionDTO dto = new SessionDTO();
			dto.setSportType(sportType);
			dto.setDuration(duration);
			dto.setStartDate(startDate);
			dto.setStartHour(startHour);
			dto.setDistance(distance);
			dto.setTitle(title);
			//CALL REMOTE METOHD
			long token = Token.getInstance().getToken();
			
			this.serviceLocator.getService().createSession(dto, token);
			} catch(Exception e) {
			
			//IN CASE THERE IS AN ERROR
			System.out.println("# Error during session making: " + e);
		}
	}

	
	
	
}
	

	

