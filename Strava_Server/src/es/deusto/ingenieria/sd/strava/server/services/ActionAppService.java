
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
<<<<<<< HEAD
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
	            System.out.println(currentDate);

	            // CHECK IF ACTUAL
	            if (currentDate.after(startDate) && currentDate.before(endDate)) {
	                challengeActive.add(chal);
	               
=======
		List<Challenge> listChallenges = ChallengeDAO.getInstance().findAll();
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
	    	System.out.println(chal);
	        Date startDate = chal.getStartDate();
	        Date endDate = chal.getEndDate(); 

	        try {
	            // Parse DATES
	            Date currentDate = sdf.parse(currentDateStr);
	            System.out.println(currentDate);

	            // CHECK IF ACTUAL
	            if (currentDate.after(startDate) && currentDate.before(endDate)) {
	                challengeActive.add(chal);
	                System.out.println("AÑADIDO");
	                System.out.println(chal);
>>>>>>> branch 'master' of https://github.com/jonlaasa/STRAVA_PT3.git
	            }
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	    }

	   
	    return challengeActive;
	}
	
	//ADD METHODS
	
	public User addChallenge(User user, Challenge challenge) {
		user.getChallenges().add(challenge);
		challenge.setUser(user);
		ChallengeDAO.getInstance().store(challenge);
		return user;
	}
	
	public User addSession(User user, Session session) {
		user.getSessions().add(session);
		session.setUser(user);
		SessionDAO.getInstance().store(session);
		return user;
	}
	
	
	
}








