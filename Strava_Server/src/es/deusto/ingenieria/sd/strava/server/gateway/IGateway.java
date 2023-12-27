package es.deusto.ingenieria.sd.strava.server.gateway;

import java.io.IOException;
import java.rmi.RemoteException;

public interface IGateway {
	
	public boolean login(String email, String pass) throws RemoteException;
	public void register(String email, String pass) throws RemoteException;
	
	

}
