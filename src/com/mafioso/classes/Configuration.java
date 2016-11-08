package com.mafioso.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Configuration {
	//Configuration variables
	private int numberOfPlayers = 2;
	private ArrayList<Card> characterList = new ArrayList<Card>();
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private ArrayList<Card> rolesForGame = new ArrayList<Card>();
	private boolean isGameReadyToBegin = false;
	private Player host = null;
	
	//Character loading variables
	private static final String CharacterDB = "com/mafioso/classes/resources/CharacterDB.txt";
	private static int dataColumns = 3;
	
	public Configuration(Player host){
		this.host = host;
		this.characterList = importCharacters();
	}
	
	//Get host
	public Player getHost(){
		return this.host;
	}
	
	public void setHost(int index){
		if(index>=0 && index<playerList.size()){
			this.host = playerList.get(index);
			isGameReadyToBegin();
		}
	}
	
	
	//Number of players
	public int getNumberOfPlayers(){
		return this.numberOfPlayers;
	}
	public void setNumberOfPlayers(int newNumberOfPlayers){
		if(newNumberOfPlayers>0){
			this.numberOfPlayers = newNumberOfPlayers;
		}
		isGameReadyToBegin();
	}
	
	//Player configuration
	public ArrayList<Player> getPlayerList(){
		return this.playerList;
	}
	public void addToPlayerList(Player user){
		this.playerList.add(user);
		isGameReadyToBegin();
	}
	public void removePlayerFromList(int index){
		if(index>=0 && index<playerList.size()){
			if(this.playerList.get(index).getName().equals(host.getName())){//Replace host if host is removed
				if(index==0&&playerList.size()>1){
					host = playerList.get(1);
				}else if(playerList.size()>1){
					host = playerList.get(0);
				}else{
					host = null;
				}
			}
			this.playerList.remove(index);
		}
		isGameReadyToBegin();
	}
	
	//Role Configuration
	public ArrayList<Card> getRolesForGame(){
		return this.rolesForGame;
	}
	
	public void addRolesForGame(Card newRole){
		this.rolesForGame.add(newRole);
		isGameReadyToBegin();
	}
	public void removeRolesForGame(int index){
		if(index>=0 && index<playerList.size()){
			this.rolesForGame.remove(index);
			isGameReadyToBegin();
		}
	}
	
	//Character List
	public ArrayList<Card> getCharacterList(){
		return this.characterList;
	}
	
	private static ArrayList<Card> importCharacters(){
		ArrayList<Card> characters = new ArrayList<Card>();
		Scanner in = new Scanner(System.in);
		BufferedReader fileReader = null;
		try {//Try obtaining the database file
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			fileReader = new BufferedReader(new FileReader(new File(classloader.getResource(CharacterDB).getFile())));
			int cardNumber = 0;
			
			//Read the first line of the db file
			String readLine = fileReader.readLine();
			while(null!=readLine){//If the current line is not null process the data
				int counter = 0;
				String[] cardInfo = new String[dataColumns];
				cardInfo[counter] = cardNumber+"";//Always add card number to index 0 and start elements at index 1
				counter++;
				in = new Scanner(readLine).useDelimiter(",");
				while(in.hasNext() && counter<dataColumns){//Iterate through all the elements of the line
					cardInfo[counter] = in.next().trim();
					counter++;
				}
				cardNumber++;
				characters.add(new Card(cardInfo));
				readLine = fileReader.readLine();
			}
		} catch (FileNotFoundException e) {//File not found
			e.printStackTrace();
		} catch (IOException e) {//Buffered Reader error
			e.printStackTrace();
		}finally{
			try {
				fileReader.close();
			} catch (IOException e) {//Not initialized
				e.printStackTrace();
			}
			in.close();
		}
		return characters;
	}
	
	private boolean isGameReadyToBegin(){
		System.out.println(rolesForGame.size() + "-" + numberOfPlayers + "-" + playerList.size());
		if(null!=rolesForGame&&null!=playerList && rolesForGame.size() == numberOfPlayers && numberOfPlayers==playerList.size()){
			isGameReadyToBegin = true;
		}else{
			isGameReadyToBegin = false;
		}
		return isGameReadyToBegin;
	}
	
	public boolean getIsGameReadyToBegin(){
		return this.isGameReadyToBegin;
	}
}
