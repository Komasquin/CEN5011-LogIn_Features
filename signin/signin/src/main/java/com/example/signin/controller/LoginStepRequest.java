package com.example.signin.controller;

public class LoginStepRequest {
    private String username;
    private String enteredPassword;
    private int step;

    public String getUsername() { 
    	return username; 
    }
    
    public void setUsername(String username) { 
    	this.username = username; 
    }

    public String getEnteredPassword() { 
    	return enteredPassword; 
    }
    
    public void setEnteredPassword(String enteredPassword) { 
    	this.enteredPassword = enteredPassword; 
    }

    public int getStep() { 
    	return step; 
    }
    
    public void setStep(int step) { 
    	this.step = step; 
    }
}
