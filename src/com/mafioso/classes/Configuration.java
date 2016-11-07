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
	private ArrayList<Card> characterList = null;
	private ArrayList<Card> rolesForGame = new ArrayList<Card>();
	
	//Character loading variables
	private static final String CharacterDB = "com/mafioso/classes/resources/CharacterDB.txt";
	private static int dataColumns = 3;
	
	public Configuration(){
		this.characterList = importCharacters();
	}
	
	public int getNumberOfPlayers(){
		return this.numberOfPlayers;
	}
	public void setNumberOfPlayers(int newNumberOfPlayers){
		this.numberOfPlayers = newNumberOfPlayers;
	}
	
	public ArrayList<Card> getRolesForGame(){
		return this.rolesForGame;
	}
	
	public void addRolesForGame(Card newRole){
		this.rolesForGame.add(newRole);
	}
	public void removeRolesForGame(int index){
		this.rolesForGame.remove(index);
	}
	
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
}
