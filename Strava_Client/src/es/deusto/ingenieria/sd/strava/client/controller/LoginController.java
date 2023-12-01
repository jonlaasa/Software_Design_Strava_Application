package es.deusto.ingenieria.sd.strava.client.controller;

import java.rmi.RemoteException;
import java.util.Date;

import es.deusto.ingenieria.sd.strava.server.data.dto.UserDTO;
import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;

//This class implements Controller pattern.
//Login
public class LoginController {	
	
	// Service Locator
	private ServiceLocator serviceLocator;


	public LoginController(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
	public boolean login(String email, String password,String provider) {
		
		try {
			// IF IT IS TRUE, THE CONTROLLER WILL UPDATE THE TOKEN
			long token = this.serviceLocator.getService().login(email, password,provider);
			Token.getInstance().setToken(token);
			return true;
		} catch (RemoteException e) {
			System.out.println("# Error during login: " + e);
			Token.getInstance().setToken(-1);
			return false;
		}
	}
	
	public void logout() {
		try {
			long token = Token.getInstance().getToken();
			this.serviceLocator.getService().logout(token);
			Token.getInstance().setToken(-1);
		} catch (RemoteException e) {
			System.out.println("# Error during logout: " + e);
		}
	}



	public ServiceLocator getServiceLocator() {
		return serviceLocator;
	}
	
	public void register(String name, String pass, String email, Date birthDate, double weight, 
			double height, double maxHRate, double rateHRest,String provider) {
		try {
			UserDTO dto = new UserDTO();
			dto.setHeight(Double.valueOf(height));
			dto.setEmail(email);
			dto.setMaxHRate(Double.valueOf(maxHRate));
			dto.setRateHRest(Double.valueOf(rateHRest));
			dto.setBirthdate(birthDate);
			dto.setName(name);
			dto.setPassword(pass);
			dto.setWeight(Double.valueOf(weight));
			dto.setProvider(provider);
			//WE REGIST THE USER
			this.serviceLocator.getService().register(dto);
		} catch (RemoteException e) {
			//in case there is an error
			System.out.println("# Error during registration: " + e);
		}
	}
	

	
}