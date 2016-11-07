package com.mafioso.classes;

import java.io.IOException;
import java.util.Scanner;

public class Game {


	
	public static void main(String[] args) {
		Configuration config = new Configuration();
		
		Scanner in = new Scanner(System.in);
		try{
			getDisplayMessage();
			String input = in.next();
			
			while(!input.equals("exit")){
				if(input.equals("1")){
					for(Card temp:config.getCharacterList()){
						System.out.println("Card Number: " + temp.getCardNumber() + ", Card name: " + temp.getName() + ", Card description: " + temp.getDescription());
					}
				}
				//Number of players
				else if(input.equals("2")){
					System.out.println("Number of players: " + config.getNumberOfPlayers());
				}else if(input.equals("3")){
					System.out.println("Enter the new number of players");
					int newNumberOfPlayers = in.nextInt();
					config.setNumberOfPlayers(newNumberOfPlayers);
					System.out.println("New number of players: " + config.getNumberOfPlayers());
				}
				//Roles
				else if(input.equals("4")){
					System.out.println("Enter card number to add: ");
					int addRole = in.nextInt();
					int count = 0;
					for(Card temp:config.getCharacterList()){
						if(addRole == count){
							System.out.println("Role Number: " + temp.getCardNumber() + ", Card name: " + temp.getName() + ", Card description: " + temp.getDescription());
							config.addRolesForGame(temp);
							break;
						}
						count++;
					}
				}else if(input.equals("5")){
					System.out.println("Enter role number to remove: ");
					int removeRole = in.nextInt();
					config.removeRolesForGame(removeRole);
				}
				else if(input.equals("6")){
					for(Card temp:config.getRolesForGame()){
						System.out.println("Role Number: " + temp.getCardNumber() + ", Card name: " + temp.getName() + ", Card description: " + temp.getDescription());
					}
				}
				
				System.out.println();
				getDisplayMessage();
				input = in.next();
			}
		}catch (Exception e) {//Scanner error
			e.printStackTrace();
		}finally{
			in.close();
		}
		
		System.out.println("Program Closing");
	}
	
	private static void getDisplayMessage(){
		System.out.println("Enter 1 to display roles, "
				+ "2 to see players, "
				+ "3 to set number of players, "
				+ "4 to add Roles, "
				+ "5 to remove Roles, "
				+ "6 to get Roles, "
				+ "and exit to leave");
	}
}
