package com.mafioso.classes;

public class Card {
	private String cardNumber;
	private String name = "";
	private String description = "";
	
	public Card(String[] data){ 
		if(data.length>=1){
			this.cardNumber = data[0];
		}
		if(data.length>=2){
			this.name = data[1];
		}
		if(data.length>=3){
			this.description = data[2];
		}
	}
	
	public String getCardNumber(){
		return this.cardNumber;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
}
