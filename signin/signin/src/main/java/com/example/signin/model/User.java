package com.example.signin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String logInMethod;
	private String password;
    private String password2;
    private String password3;
    private String password4;
    private String email;
    private String role; 

    public Long getId() { 
    	return id; 
    }
    
    public void setId(Long id) { 
    	this.id = id; 
    }
    
    public String getUsername() { 
    	return username; 
    }
    
    public void setUsername(String username) { 
    	this.username = username; 
    }
    
    public String getLogInMethod() {
		return logInMethod;
	}

	public void setLogInMethod(String logInMethod) {
		this.logInMethod = logInMethod;
	}
    
    public String getPassword() { 
    	return password; 
    }
    
    public void setPassword(String password) { 
    	this.password = password; 
    }

	public String getPassword2() {
		return password2;
	}
	
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	public String getPassword3() {
		return password3;
	}
	
	public void setPassword3(String password3) {
		this.password3 = password3;
	}
	
	public String getPassword4() {
		return password4;
	}
	
	public void setPassword4(String password4) {
		this.password4 = password4;
	}
	
	public String getEmail() { 
		return email; 
	}  
	
	public void setEmail(String email) { 
		this.email = email; 
	}
	
	public String getRole() { 
		return role; 
	}   
	
	public void setRole(String role) { 
		this.role = role; 
	}
}
