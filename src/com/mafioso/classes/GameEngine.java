package com.mafioso.classes;

import java.io.FileNotFoundException;

public class GameEngine {
	
	public static void main(String[] args) throws FileNotFoundException{
		//TODO remove testing exception
		GameLobby gameLobby = new GameLobby();
		Configuration config = gameLobby.establishLoby();
		if(null!=config){//If the game has started
			
			//Assign roles to players
			GameSession game = new GameSession(config);
		}
	}
}
