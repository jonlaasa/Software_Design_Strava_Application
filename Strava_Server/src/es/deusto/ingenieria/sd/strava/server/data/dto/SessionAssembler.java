package es.deusto.ingenieria.sd.strava.server.data.dto;

import es.deusto.ingenieria.sd.strava.server.data.domain.Session;

//This class is part of the DTO pattern. It also implements Singleton Pattern.

//AS WE CAN SEE, WE DECIDED TO USE "LAZY" Singleton pattern, as it will create an instance
// only if it necessary.


public class SessionAssembler {
	
	private static SessionAssembler instance;

	private SessionAssembler() { }
	
	public static SessionAssembler getInstance() {
		if (instance == null) {
			instance = new SessionAssembler();
		}

		return instance;
	}

	public SessionDTO sessionToDTO(Session sess) {
		SessionDTO dto = new SessionDTO();
		
		dto.setTitle(sess.getTitle());
		dto.setStartDate(sess.getStartDate());
		dto.setStartHour(sess.getStartHour());
		dto.setDistance(sess.getDistance());
		dto.setSportType(sess.getSportType());
		dto.setDuration(sess.getDuration());
		
		return dto;
	}
	

	public Session sessionDTOToD(SessionDTO sess) {
		Session session = new Session();
		
		session.setTitle(sess.getTitle());
		session.setStartDate(sess.getStartDate());
		session.setStartHour(sess.getStartHour());
		session.setDistance(sess.getDistance());
		session.setSportType(sess.getSportType());
		session.setDuration(sess.getDuration());
		
		return session;
	}
	


	
}
