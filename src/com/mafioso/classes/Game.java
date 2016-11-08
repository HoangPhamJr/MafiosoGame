package com.mafioso.classes;

import java.io.IOException;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter your name: ");
		Player host = new Player(in.next());
		Configuration config = new Configuration(host);
		config.addToPlayerList(host);
		
		try{
			getDisplayMessage();
			String input = in.next();
			
			while(!input.equals("exit") || null!=config.getHost()){
				if(input.equals("1")){//List Cards
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
				else if(input.equals("4")){//Add Role
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
				}else if(input.equals("5")){//Remove Role
					System.out.println("Enter role number to remove: ");
					int removeRole = in.nextInt();
					config.removeRolesForGame(removeRole);
				}
				else if(input.equals("6")){//View Roles
					for(Card temp:config.getRolesForGame()){
						System.out.println("Role Number: " + temp.getCardNumber() + ", Card name: " + temp.getName() + ", Card description: " + temp.getDescription());
					}
				}else if(input.equals("7")){//Add player
					System.out.println("Enter player name: ");
					Player player = new Player(in.next());
					config.addToPlayerList(player);
				}else if(input.equals("8")){//Remove player
					System.out.println("Enter player number to remove: ");
					int removePlayer = in.nextInt() -1;
					config.removePlayerFromList(removePlayer);
				}else if(input.equals("9")){//View players
					int counter = 1;
					for(Player temp:config.getPlayerList()){
						System.out.println("Player number: " + counter + ": " + temp.getName());
						counter++;
					}
				}else if(input.equals("10")){//Get host
					System.out.println("Host is: " + config.getHost().getName());
				}
				else if(input.equals("11")){//Set host
					System.out.println("Enter player number to make host: ");
					int newHost = in.nextInt() -1;
					config.setHost(newHost);
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
				+ "5 to remove Roles");
		System.out.println("6 to get Roles, "
				+ "7 to add player, "
				+ "8 to remove player, "
				+ "9 to view players, "
				+ "10 to get host, "
				+ "11 to set host, "
				+ "and exit to leave");
	}
}
