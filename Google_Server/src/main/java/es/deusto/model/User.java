package es.deusto.model;

import jakarta.persistence.*;


//"User" is a reserved word in many DBs. We can programmatically provide a name for the table to avoid problems.
@Table(name="userTable")
@Entity
public class User  {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String pass;
@Column(unique = true)
private String email;

public User () {}


public User (String email, String pass) {

	 	this.pass=pass;
	 	this.email=email;
	 }

public Long getId() {
 return id;
}
public void setId(Long id) {
 this.id = id;
}
public String getEmail() {
 return email;
}
public void setEmail(String email) {
 this.email = email;
}

public String getPass() {
	return pass;
}

public void setPass(String pass) {
	this.pass = pass;
}

  
}
