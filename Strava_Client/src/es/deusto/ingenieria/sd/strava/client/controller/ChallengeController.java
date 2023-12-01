package es.deusto.ingenieria.sd.strava.client.controller;

import java.rmi.RemoteException;
import java.util.Date;


import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;



//This class implements Controller pattern.
//Login
public class ChallengeController {	
	
	// Service Locator
	private ServiceLocator serviceLocator;
	//Token as id of the user when log in.


	public ChallengeController(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
	
	


	public ServiceLocator getServiceLocator() {
		return serviceLocator;
	}
	
	
	// we need three methods to add.
	

	public void setUpChallenge(String name, Date endDate, double distance, double goal, Date startDate, String sportType) {
		ChallengeDTO challenge = new ChallengeDTO();
		challenge.setName(name);
		challenge.setEndDate(endDate);
		challenge.setDistance(distance);
		challenge.setGoal(goal);
		challenge.setStartDate(startDate);
		challenge.setSportType(sportType);
		try {
			long token = Token.getInstance().getToken();
			this.serviceLocator.getService().setUpChallege(challenge, token);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println(" Set up challange fail: " + e);
		}
		
	}
	
	
	
	public boolean acceptChallenge(String name) {
		boolean value = false;
		try {
			long token = Token.getInstance().getToken();
			value = this.serviceLocator.getService().acceptChallenge(name, token);
			return value;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println(" Accept challenge fail: " + e);
			return value;
		}
	}
			


}

	

