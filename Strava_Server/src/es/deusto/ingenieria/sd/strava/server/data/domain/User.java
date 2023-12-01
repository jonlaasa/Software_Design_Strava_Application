package es.deusto.ingenieria.sd.strava.server.data.domain;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {	
	
	private String name;
	private String password;
	
	@Id
	private String email;
	private Date birthdate;
	private double  weight;
	private double height;
	private double maxHRate;
	private double rateHRest;
	private String provider;
	
	@OneToMany(mappedBy="user", fetch= FetchType.EAGER, cascade = (CascadeType.ALL))
	private ArrayList<Challenge> challenges = new ArrayList<>();
	
	
	@OneToMany(mappedBy="user", fetch= FetchType.EAGER, cascade = (CascadeType.ALL))
	private ArrayList<Challenge> activeChallenges = new ArrayList<>();
	
	@OneToMany(mappedBy="user", fetch= FetchType.EAGER, cascade = (CascadeType.ALL))
	private ArrayList<Session> sessions = new ArrayList<>();
	
	
	
	public User(String name, String password, String email, Date birthdate, double weight, double height,
			double maxHRate, double rateHRest, String provider, ArrayList<Challenge> challenges, ArrayList<Challenge> activeChallenges,
			ArrayList<Session> sessions) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.birthdate = birthdate;
		this.weight = weight;
		this.height = height;
		this.maxHRate = maxHRate;
		this.rateHRest = rateHRest;
		this.provider = provider;
		this.challenges = challenges;
		this.activeChallenges = activeChallenges;
		this.sessions = sessions;
	}
	
	public User(String name, String password, String email, Date birthdate, double weight, double height,
			double maxHRate, double rateHRest,String provider) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.birthdate = birthdate;
		this.weight = weight;
		this.height = height;
		this.maxHRate = maxHRate;
		this.rateHRest = rateHRest;
		this.provider = provider;
		this.challenges = new ArrayList <Challenge> ();
		this.activeChallenges = new ArrayList <Challenge> ();
		this.sessions = new ArrayList <Session> ();
	}
	
	public User() {}
	
	public User(String email, String pass) {
		this.password = pass;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getMaxHRate() {
		return maxHRate;
	}
	public void setMaxHRate(double maxHRate) {
		this.maxHRate = maxHRate;
	}
	

	public double getRateHRest() {
		return rateHRest;
	}
	public void setRateHRest(double rateHRest) {
		this.rateHRest = rateHRest;
	}
	public ArrayList<Challenge> getChallenges() {
		return challenges;
	}
	public void setChallenges(ArrayList<Challenge> challenges) {
		this.challenges = challenges;
	}
	public ArrayList<Challenge> getActiveChallenges() {
		return activeChallenges;
	}
	public void setActiveChallenges(ArrayList<Challenge> activeChallenges) {
		this.activeChallenges = activeChallenges;
	}
	public ArrayList<Session> getSessions() {
		return sessions;
	}
	public void setSessions(ArrayList<Session> sessions) {
		this.sessions = sessions;
	}
	
	
	public void addSession(Session session) {
		if (session != null) {
			this.sessions.add(session);
		}
	}
	
	public void addChallenge(Challenge challenge) {
		if (challenge != null ) {
			this.challenges.add(challenge);
		}
	}
	
	public void addActiveChallenge(Challenge challenge) {
		if (challenge != null ) {
			this.activeChallenges.add(challenge);
		}
	}
	
	public String getProvider() {
		// TODO Auto-generated method stub
		return provider;
	}
	
	public void setProvider(String provider) {
		// TODO Auto-generated method stub
		this.provider = provider;
	}
	
	
	
	// IN ORDER TO PRINT IN A EASY WAY:
	
	//WE SUMMARIZE AS NAME, EMAIL AND SIZE OF (SESSIONS, ACTIVE CHALLENGES AND CHALLENGES)
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		
		result.append(this.name);
		result.append(" - ");
		result.append(this.email);
		result.append(" - (");
		result.append(this.challenges.size());
		result.append(" challenges) - (");
		result.append(this.activeChallenges.size());
		result.append(" active challenges) - (");
		result.append(this.sessions.size());
		result.append(" sessions)");
		
		return result.toString();
	}
	
	//HOW TO COMPARE TWO USERS?? EMAIL
	
	@Override
	public boolean equals(Object obj) {
		//if both are USERS...
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			//COMPARE BY EMAIL
			return this.email.equals(((User)obj).email);
		}
		
		return false;
	}


	



	
	
}
	
	