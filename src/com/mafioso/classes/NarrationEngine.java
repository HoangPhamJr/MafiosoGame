package com.mafioso.classes;

import java.util.ArrayList;

public class NarrationEngine {
	private int timePerTurn = 15;
	private int timeForDiscussion = 30;
	private int transitionTime = 3;
	private int timeToDisplayText = 3;
	private int storyTime = 10;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	
	public NarrationEngine(Configuration config){
		this.timePerTurn = config.getTimePerTurn();
		this.timeForDiscussion = config.getTimeForDiscussion();
		this.timeToDisplayText = config.getTimeToDisplayText();
		this.transitionTime = config.getTransitionTime();
		this.playerList = config.getPlayerList();
		this.storyTime = config.getStoryTime();
		
		//Start game
		while(playerList.size()>0){
			nightPhase();
			dayPhase();
		}
	}
	
	private void nightPhase(){
		try {
			System.out.println("Mafia open your eyes and remove one player");
		    Thread.sleep(timePerTurn*1000);
		    System.out.println("Mafia close your eyes");
		    Thread.sleep(transitionTime*1000);
		    
		    System.out.println("Nurse open your eyes and save one player");
		    Thread.sleep(timePerTurn*1000);
		    System.out.println("Nurse close your eyes");
		    Thread.sleep(transitionTime*1000);
		    
		    System.out.println("Cop open your eyes and optionally remove one player");
		    Thread.sleep(timePerTurn*1000);
		    System.out.println("Cop close your eyes");
		    Thread.sleep(transitionTime*1000);
		    
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
	private void dayPhase(){
		try {
			System.out.println("Everyone open your eyes and read the story");
		    Thread.sleep(storyTime*1000);
		    System.out.println("Everyone vote to remove one player");
		    Thread.sleep(timeForDiscussion*1000);
		    System.out.println("Everyone close your eyes");
		    Thread.sleep(transitionTime*1000);
		    
		    
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
}
