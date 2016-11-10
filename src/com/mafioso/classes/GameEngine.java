package com.mafioso.classes;

public class GameEngine {
	
	public static void main(String[] args){
		GameLobby gameLobby = new GameLobby();
		Configuration config = gameLobby.establishLoby();
		if(null!=config){//If the game has started
			
			//Assign roles to players
			GameSession game = new GameSession(config);
		}
	}
}
