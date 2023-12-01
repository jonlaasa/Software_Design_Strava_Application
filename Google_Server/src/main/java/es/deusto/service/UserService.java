package es.deusto.service;

import es.deusto.dao.UserRepository;
import es.deusto.model.User;

import org.springframework.stereotype.Service;

import java.util.Optional;

//Use @Service annotation for BUSINESS LOGIC and access to the @REPOSITORY
@Service
public class UserService {
	private UserRepository userRepository;
    public enum UserServiceResult {
		SUCCESS,
		FAIL;
	}
 
    
  
        
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    
    public UserServiceResult login(String email, String pass) {
    	Optional<User> result = userRepository.findByEmail(email);
    	User userBd = result.get();
    	if (email.equals(userBd.getEmail())) {
            if (pass.equals(userBd.getPass())) {
                return UserServiceResult.SUCCESS;
            }
        }
    	return UserServiceResult.FAIL;
    }
    
    public void regist(String email, String pass) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        } else {
            User newUser = new User(email, pass);
            userRepository.save(newUser);
        }
    }
	
}












