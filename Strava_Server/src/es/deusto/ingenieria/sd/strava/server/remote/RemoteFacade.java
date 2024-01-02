package es.deusto.ingenieria.sd.strava.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.deusto.ingenieria.sd.strava.server.data.dao.ChallengeDAO;
import es.deusto.ingenieria.sd.strava.server.data.dao.UserDAO;
import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.strava.server.data.domain.Session;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeAssembler;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.SessionAssembler;
import es.deusto.ingenieria.sd.strava.server.data.dto.SessionDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.UserAssembler;
import es.deusto.ingenieria.sd.strava.server.data.dto.UserDTO;
import es.deusto.ingenieria.sd.strava.server.services.ActionAppService;
import es.deusto.ingenieria.sd.strava.server.services.LoginAppService;
import es.deusto.ingenieria.sd.strava.server.services.MailSender;




public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {	
	private static final long serialVersionUID = 1L;

	//Data structure for manage Server State

	private Map<Long, User> userState = new HashMap<>();

	//TODO: Remove this instances when Singleton Pattern is implemented
	
	private LoginAppService loginService;	
	private ActionAppService actionService = new ActionAppService();
	
	//WHEN WE ADD ANY CHALLENGE TO A USER WE WILL ADD ALSO HERE, SO LATER WE CAN USE IT FOR
	//LOOKING FOR TOHSE THAT ARE ACTIVE
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	

	public RemoteFacade(String[] lista) throws RemoteException, ParseException {	
		super();
		System.out.println("RemoteFacade lista:"+lista[1]);
		loginService=new LoginAppService(lista);
		
		User a = new User ("aitor","44f878afe53efc66b76772bd845eb65944ed8232","aitor.merodio@opendeusto.es", new Date(), 1,1,1,1,"FACEBOOK");
	
		loginService.regist(a);
		
		loginService.regist(
				new User("iker","e165f1f439f2c92b7fd8f906c98f84677a6b45bb","iker.ruesgas@opendeusto.es", new Date(), 1,1,1,1,"FACEBOOK"));
		
		
		User u = new User("jon","44f878afe53efc66b76772bd845eb65944ed8232","jon.lasa@opendeusto.es", new Date(), 1,1,1,1,"GOOGLE");
		
		
		loginService.regist(u);
		
		

		
		//CREATE THESE AS DEFAULT
		
		Challenge g1 = new Challenge("Challenge1",dateFormat.parse("2023-11-01"), dateFormat.parse("2023-11-30"),
						10.0, 15.0, "Running",a);
				
					
		Challenge g2 = new Challenge ("Challenge2", dateFormat.parse("2023-11-10"), dateFormat.parse("2024-12-05"), 8.0, 12.0, "Cycling",a);
		
		Challenge g3 = new Challenge("Challenge3",dateFormat.parse("2023-10-30"),dateFormat.parse("2024-05-01"), 10.0, 15.0, "Running",a);
				
		actionService.addChallenge(u,g1);
		actionService.addChallenge(u,g2);
		actionService.addChallenge(u,g3);
		
		
		
		
		
		
	}
	
	
	
	
	@Override
	public synchronized long login(String email, String password,String provider) throws RemoteException {
		
		System.out.println("RemoteFacade --  login() call: " + email + " / " + password);
		
		System.out.println("RemoteFacade lista login:"+loginService.getListo()[1]);
		
		//Perform login() using LoginAppService
		User user = loginService.login(email, password,provider);
			
		//If login works correctly the user is stored in the User State and will be assigned a token
		if (user != null) {
			//If user is not logged in 
			if (!this.userState.values().contains(user)) {
				//WE WILL GIVE THE TOKEN USING THIS 
				long token = Calendar.getInstance().getTimeInMillis();	
				System.out.println("Token = " + token);
				//WE ADD TO THE USER STATE
				this.userState.put(token, user);
				System.out.println("RemoteFacade --  login() call succesfully: " + email + " / " + password);
				return(token);
			} else {
				//IT IS ALREADY LOGGED
				throw new RemoteException("User is already logged in!");
			}
		} else {
			//DOESNT EXIST
			throw new RemoteException("Login fails!");
		}
	}
	
	@Override
	public synchronized void logout(long token) throws RemoteException {
		System.out.println("RemoteFacade -- logout() call: " + token);
		
		if (this.userState.containsKey(token)) {
			//Logout means remove the User from Server State
			this.userState.remove(token);
		} else {
			throw new RemoteException(" The user is not logged in!");
		}
	}
	
	@Override
	public List<ChallengeDTO> getActiveChallenges() throws RemoteException {
		System.out.println("RemoteFacade -- getActiveChallenges call ");
				
		
		//Get Challenges (look if they are active, we delegate to the app service)
		// using actionAppService 
		List<Challenge> challengesActive = actionService.getActiveChallenges();
		
		if (challengesActive != null) {
			List<ChallengeDTO> challengesActiveDTO = new ArrayList<ChallengeDTO> ();
			//Convert domain object to DTO in order to give to the client (IMPORTANT TO BE DTO!)
			for (Challenge challenge : challengesActive) {
	            ChallengeDTO dto = ChallengeAssembler.getInstance().challengeToDTO(challenge);
	            challengesActiveDTO.add(dto);
	        }
	        return challengesActiveDTO;
		} else {
			throw new RemoteException("getActiveChallenges() fails!");
		}
	}

	@Override
	public void register(UserDTO userDTO) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("RemoteFacade -- Register call: "+userDTO.getName());
		User user = UserAssembler.getInstance().userDTOtoD(userDTO);
		System.out.println("provider in the facade: " + user.getProvider());
		
		if(!loginService.regist(user)) {
			throw new RemoteException("Registration does not go correctly,  fails!");
		}else{
			loginService.regist(user);			
		}
	}

	@Override
	public void createSession(SessionDTO sessionDTO, Long token) throws RemoteException {
		// TODO Auto-generated method stub
		
		System.out.println("RemoteFacade -- Create session call:"); 

		//we know that the remote facade will use ASSEMBLER
		Session session =SessionAssembler.getInstance().sessionDTOToD(sessionDTO);
		
		//finally we find the user and add it..
		User user = userState.get(token);
		//we add using the app service
		actionService.addSession(user, session);
		return;
	}

	@Override
	public void setUpChallege(ChallengeDTO challengeDTO, Long token) throws RemoteException {
		// TODO Auto-generated method stub
		//we know that the remote facade will use ASSEMBLER
		System.out.println("RemoteFacade -- Set up challenge call: "+challengeDTO.getName()); 
		
		Challenge challenge = ChallengeAssembler.getInstance().challengeDTOToD(challengeDTO);
		
		//finally we find the user and add it..
		User user = userState.get(token);
		
		//now we add to the list of the class
		actionService.addChallenge(user, challenge);
		return;
	}

	@Override
	public boolean acceptChallenge(String name, Long token) throws RemoteException {
		// TODO Auto-generated method stub
		
		System.out.println("RemoteFacade -- Accept active challenge call: "+token+" accepts "+name);
		User user = userState.get(token);
		
		List<Challenge> challengesActive = actionService.getActiveChallenges();
		
		Challenge challengeAdd = actionService.acceptChallenge(name);
	
		//IT IT EXISTS WE ADD IT 
		if ( challengeAdd == null) {			
			//WE WILL PRINT A ERROR MESSAGE OR SOMETHING
			return false;
		}else {			
			
			//ADD IN DB
			UserDAO.getInstance().addUserChallenge(user.getEmail(), challengeAdd.getName());
			new MailSender(user.getEmail()).sendMessage("Accepted challenge with name: " + name);
			
			return true;
		}
		
	}
}