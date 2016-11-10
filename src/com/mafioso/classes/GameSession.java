package com.mafioso.classes;

import java.util.ArrayList;

public class GameSession {
	private Configuration config = null;
	
	public GameSession(Configuration newConfig){
		this.config = newConfig;
		assignRoles();
		
	}
	public static void main(String[] args){
		test();
	}
	
	private void assignRoles(){
		ArrayList<Card> rolesForGame = config.getRolesForGame();
		int counter = 0;
		while(rolesForGame.size()>0){
			int random = (int)(Math.random() * rolesForGame.size());
			config.assignPlayerRole(counter, rolesForGame.remove(random));
			//Debug
			//System.out.println(random);
			//System.out.println("Player: " + config.getPlayer(counter).getName() + " is given the role: " + config.getPlayer(counter).getRole().getName());
			counter++;
		}
	}
	
	private static void test(){
		int c = 0;
		int[][] temp = new int[6][6];
		while(c<2){
			ArrayList<Integer> rolesForGame = new ArrayList<Integer>();
			rolesForGame.add(0);
			rolesForGame.add(1);
			rolesForGame.add(2);
			rolesForGame.add(3);
			rolesForGame.add(4);
			rolesForGame.add(5);
			int counter = 0;
			//System.out.println("size:"+rolesForGame.size());
			while(rolesForGame.size()>0){
				int random = (int)(Math.random() * rolesForGame.size());
				//System.out.println("random: " + random + ", user number " + rolesForGame.remove(random));
				temp[counter][rolesForGame.remove(random)]++;
				counter++;
			}
			c++;
		}
		int count = 0;
		while(count<6){
			System.out.println("["+temp[count][0]+"] "+"["+temp[count][1]+"] "+"["+temp[count][2]+"] "+"["+temp[count][3]+"] "+"["+temp[count][4]+"] "+"["+temp[count][5]+"] ");
			count++;
		}
	}
	
}
