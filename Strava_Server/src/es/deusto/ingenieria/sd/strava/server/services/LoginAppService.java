package es.deusto.ingenieria.sd.strava.server.services;


import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import es.deusto.ingenieria.sd.strava.server.data.dao.UserDAO;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.gateway.Factory;

public class LoginAppService {
	
	// a map for storing the registration (key = EMAIL)
	
	//here the user list of challenge /sessions or active challenges wont be updated
	//( in this version)
		private String[] listo=new String[4]; 
	
	

	//method for registrating a user
	
	
	
	
	public boolean regist(User user) {
		
		User u = UserDAO.getInstance().find(user.getEmail());
		if(u != null) {
			return false;
		}
		else {
			try {
				System.out.println("PROVIDER: " + user.getProvider());
				//CALL GATEWAY
				Factory.getInstance(listo).createGateway(user.getProvider()).register(user.getEmail(), user.getPassword());
			
				UserDAO.getInstance().store(user);
				System.out.println("STORED USER");
				return true;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			//}
			
		}
			
			
		
	}
	}
	
	
	//this method will be to check if the user that wants to log in is already registrated or not.
	


	
	//this method will be to check if the user that wants to log in is already registrated or not.
	

	public LoginAppService(String[] lista) {
		super();
		listo[0]=lista[0];
		listo[1]=lista[1];
		listo[2]=lista[2];
		listo[3]=lista[3];
	}


	public User login(String email, String password , String provider) {
		//TODO: Get User using DAO and check
		User u = UserDAO.getInstance().find(email);
		if(u!=null) {
			try {
				if(Factory.getInstance(listo).createGateway(provider).login(email, password)){
					return u;
				}else {
					//the password is incorrect so it isnt correct (NULL)
					User userNull = null;
					return userNull;
					
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
					//ERROR WE RETURN NULL
				e.printStackTrace();
				User userNull = null;
				return userNull;	
			}
		}else {
			//THIS EMAIL DOESNT EXIST (NULL)
			User userNull = null;
			return userNull;
		}
				
					
	}


	public String[] getListo() {
		return listo;
	}
	public void setListo(String[] listo) {
		this.listo = listo;
	}
	
	
	
}
	

