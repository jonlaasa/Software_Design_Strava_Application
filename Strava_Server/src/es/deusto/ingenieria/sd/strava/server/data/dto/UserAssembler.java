package es.deusto.ingenieria.sd.strava.server.data.dto;

import es.deusto.ingenieria.sd.strava.server.data.domain.User;

//This class is part of the DTO pattern. It also implements Singleton Pattern.

//AS WE CAN SEE, WE DECIDED TO USE "LAZY" Singleton pattern, as it will create an instance
// only if it necessary.


public class UserAssembler {
	private static UserAssembler instance;

	private UserAssembler() { }
	
	public static UserAssembler getInstance() {
		if (instance == null) {
			instance = new UserAssembler();
		}

		return instance;
	}

	public User userDTOtoD(UserDTO userDTO) {
		User user = new User(userDTO.getName(),userDTO.getPassword(),userDTO.getEmail(),userDTO.getBirthdate(),userDTO.getWeight(),userDTO.getHeight(),userDTO.getMaxHRate(),userDTO.getMaxHRate(),userDTO.getProvider());		
		
		return user;
	}
	
}
	
	
	
	
