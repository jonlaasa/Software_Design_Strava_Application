package es.deusto.ingenieria.sd.strava.server.data.dto;


import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;

//This class is part of the DTO pattern. It also implements Singleton Pattern.

//AS WE CAN SEE, WE DECIDED TO USE "LAZY" Singleton pattern, as it will create an instance
// only if it necessary.


public class ChallengeAssembler {
	
	private static ChallengeAssembler instance;

	private ChallengeAssembler() { }
	
	public static ChallengeAssembler getInstance() {
		if (instance == null) {
			instance = new ChallengeAssembler();
		}

		return instance;
	}

	public ChallengeDTO challengeToDTO(Challenge chl) {
		ChallengeDTO dto = new ChallengeDTO();
		
		dto.setName(chl.getName());
		dto.setStartDate(chl.getStartDate());
		dto.setEndDate(chl.getEndDate());
		dto.setDistance(chl.getDistance());
		dto.setSportType(chl.getSportType());
		dto.setGoal(chl.getGoal());
		
		return dto;
	}
	

	
	public Challenge challengeDTOToD(ChallengeDTO chl) {
		Challenge chal = new Challenge();
		
		chal.setName(chl.getName());
		chal.setStartDate(chl.getStartDate());
		chal.setEndDate(chl.getEndDate());
		chal.setDistance(chl.getDistance());
		chal.setSportType(chl.getSportType());
		chal.setGoal(chl.getGoal());
		
		return chal;
	}
	
	
	
	


}
	