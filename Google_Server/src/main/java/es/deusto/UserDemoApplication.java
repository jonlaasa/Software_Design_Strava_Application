package es.deusto;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import es.deusto.dao.UserRepository;
import es.deusto.model.User;


@SpringBootApplication
public class UserDemoApplication {
	
	private static final Logger log= LoggerFactory.getLogger(UserDemoApplication.class);
	 
	@Value("${spring.mail.host}")		
	private String host;
	@Value("${spring.mail.port}")		
	private int port;
	@Value("${spring.mail.username}")
    private String sender;
	@Value("${spring.mail.password}")
	private String password;
	


    public static void main(String[] args) {
        SpringApplication.run(UserDemoApplication.class, args);
        
          }
     
    
    
    @Bean
    CommandLineRunner demo(UserRepository repository) {
      return (args) -> {
          // INIT data ... some Users
    	    	  

    	  User jon = new User ("jon.lasa@opendeusto.es", "44f878afe53efc66b76772bd845eb65944ed8232");
    	  User iker = new User ("iker.ruesgas@opendeusto.es", "e165f1f439f2c92b7fd8f906c98f84677a6b45bb");
    	 
    	  repository.save(jon);
    	  repository.save(iker);
    
        log.info("Sample users created"); 
    	  
    	log.info("USER Services AVAILABLE ...");


      };
    }
}
    