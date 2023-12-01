package es.deusto.controller;

import es.deusto.service.UserService;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/*Use @Controllers ONLY for Routing: providing end-points (get requests & provide JSON responses)
* They are STATELESS & SINGLETON
*/

@CrossOrigin(origins = "http://127.0.0.1:8888/")
@RestController
@RequestMapping("/user")
public class UserController {


    private UserService userService;
    
    // Example of constructor injection
    public UserController(UserService userService) {
        this.userService = userService;
    }
  
 
    @GetMapping("/details")
    public ResponseEntity<Object> userDetails(@RequestParam String email, @RequestParam String password) {
        try {
            
        	System.out.println("ACCEDIENDO A COMPROBACION LOGIN");
            switch (userService.login(email,password)) {
    		case FAIL:
    			return ResponseEntity.unprocessableEntity().body("Failed to Delete the specified User");
  	        
    		default:
    			return ResponseEntity.ok("Successfully deleted the specified user");
    	}

                     
        } catch (Exception e) {
            System.out.println("Error al obtener detalles del usuario: " + e.getMessage());
            return ResponseEntity.status(500).build(); 
        }
    }


 
    @PostMapping("/register") 
    public ResponseEntity<Object> registerUser(@RequestParam String email, @RequestParam String password) {
        try {
            userService.regist(email, password); 
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
    
    
}