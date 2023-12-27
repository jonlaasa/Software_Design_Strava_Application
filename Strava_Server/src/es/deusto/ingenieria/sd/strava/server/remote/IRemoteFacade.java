package es.deusto.ingenieria.sd.strava.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.SessionDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.UserDTO;





//This interface defines the API of the Server. It represents the Remote Facade pattern
public interface IRemoteFacade extends Remote {	

	public long login(String email, String password , String provider) throws RemoteException;
	
	public void logout(long token) throws RemoteException; 
	
	List<ChallengeDTO> getActiveChallenges() throws RemoteException;	
	
	public void register(UserDTO userDTO) throws RemoteException;	
	
	public void createSession(SessionDTO sessionDTO, Long token) throws RemoteException;	
	
	public void setUpChallege(ChallengeDTO challengeDTO, Long token) throws RemoteException;	
	
	public boolean acceptChallenge(String name, Long token) throws RemoteException;	
	
}