package repositories;

import models.Team;
import models.User; 

import utils.Connection;

import java.util.*;

public class UserRepository implements Repository<User> {
	 
	public ArrayList<User> find(String column, String[] kondisi, boolean joinTable, String joinTableName, Connection conn){
		ArrayList<User> userFound= new ArrayList<>(); 
		
		ArrayList<User> userData= conn.readUser();
		
		if(column == null && kondisi == null) {
			return userData;
		}
		
		if ((column != null && kondisi == null) || (column == null && kondisi != null))
		{
			System.out.println("Invalid");
			return null;
		}
		
		if(kondisi != null && kondisi.length!= 2 ) {
			System.out.println("Invalid ");
			return null;
		}
		
		if(column == null || kondisi == null) {
			System.out.println("Invalid");
			return null; 
		}
		
		int columnidx= -1; 
		
		switch(column.toLowerCase()) {
		case "name":
				columnidx= 1;
				break;
		case "NIM":
				columnidx= 0; 
				break;
		case "Idteam":
		case "id team":
		case "teamID":
			columnidx= 2;
			break;
			default:
				System.out.println("Invalid column name");
				return null;
		}
		
		if(columnidx == 0) {
			for (User user : userFound) {
				if(user.getNim().equals(kondisi[1])){
					userFound.add(user);
					return userFound; 
			}
		}
		} else if(columnidx == 1) {
			for (User user : userFound) {
				if(user.getName().equals(kondisi[1])) {
					userFound.add(user);
					return userFound;
					
				}
			}
		}
		else if(columnidx == 2) {
			for(User user : userFound) {
				if(user.getId()== Integer.parseInt(kondisi[1])) {
					userFound.add(user);
					return userFound; 
				}
			}
		}
		
//		for(String[] row: userData) {
//			if(row.length > columnidx && row[columnidx].equalsIgnoreCase(kondisi[1])){
//				User user= new User();
//				user.setName(row[1]);
//				user.setNim(row[0]);
//				user.setId(Integer.parseInt(row[2]));
//				userFound.add(user); 
//				
//			}
//		}
		if(userFound.isEmpty()) {
			return null; 
		}else {
			return userFound;
		}
		
	}
	
	public User findOne(String column, String[] kondisi, boolean joinTable, String joinTableName, Connection conn) {
		ArrayList<User> userFound= find(column, kondisi, joinTable, joinTableName, conn);
		return (userFound != null && !userFound.isEmpty()) ? userFound.get(0) : null;
	}
	
	public User insert(String[] data, Connection conn){
		ArrayList<User> curr = conn.readUser();
		ArrayList<Team> curr2 = conn.readTeam();
		int teamID= -1;
		
		
		
		for (Team team : curr2 ) {
			if(team.getNamaTeam().equals(data[2])) {
				teamID = team.getId(); 
				
			}
		}
		if(teamID == -1) {
			return null; 
		}
		
		User newUser = new User(data[1], data[0], teamID);  
		conn.writeUser(conn.getUserPath(), curr);
		return newUser;
	}
	
	
}
