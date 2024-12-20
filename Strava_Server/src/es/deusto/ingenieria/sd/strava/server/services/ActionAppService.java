
package es.deusto.ingenieria.sd.strava.server.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.dao.ChallengeDAO;
import es.deusto.ingenieria.sd.strava.server.data.dao.SessionDAO;
import es.deusto.ingenieria.sd.strava.server.data.dao.UserDAO;
import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.strava.server.data.domain.Session;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;

import java.text.SimpleDateFormat;
import java.text.ParseException;

public class ActionAppService {

	public Challenge acceptChallenge(String name) {

		List<Challenge> listChallenges = getActiveChallenges();
		Challenge challengeAdd = null;
		for (Challenge chall : listChallenges) {
		    
		    	if (chall.getName().equals(name)) {
		            challengeAdd=chall;
		            break;// Agrega el desafío coincidente a la lista de coincidencias
		    }
		}
		return challengeAdd;
	}
	
	
	
	
	public List<Challenge> getActiveChallenges() {
	    List<Challenge> challengeActive = new ArrayList<Challenge>();
	    List<Challenge> listChallenges = ChallengeDAO.getInstance().findAll();

	    // FORMAT "yyyy-MM-dd"
	    //NECESITA CAMBIOS
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String currentDateStr = sdf.format(new Date());

	    
	    for (Challenge chal : listChallenges) {
	        // ObTAIN DATES
	    	
	        Date startDate = chal.getStartDate();
	        Date endDate = chal.getEndDate(); 

	        try {
	            // Parse DATES
	            Date currentDate = sdf.parse(currentDateStr);
	           

	            // CHECK IF ACTUAL
	            if (currentDate.after(startDate) && currentDate.before(endDate)) {
	                challengeActive.add(chal);
	               
	            }
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	    }

	   
	    return challengeActive;
	}
	
	//ADD METHODS
	
	
	
	
	public User addChallenge(User user, Challenge challenge) {
		challenge.setUser(user);
		ChallengeDAO.getInstance().store(challenge);
		System.out.println("STORED CHALLENGE");
		new MailSender(user.getEmail()).sendMessage("Created challenge with name: " + challenge.getName());
		return user;
	}
	
	public User addSession(User user, Session session) {
		session.setUser(user);
		SessionDAO.getInstance().store(session);
		System.out.println("STORED SESSION");
		new MailSender(user.getEmail()).sendMessage("Created session with title: " + session.getTitle());
		return user;
	}
	
	
	
}










