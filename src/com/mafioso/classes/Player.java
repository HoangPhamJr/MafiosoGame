package com.mafioso.classes;

public class Player {
	
	private String name = "";
	private Card role = null;
	
	public Player(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setRole(Card newRole){
		this.role = newRole;
	}
	
	public Card getRole(){
		return this.role;
	}
}
