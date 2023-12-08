package utils;


import java.io.*; 
import java.util.ArrayList;
import java.util.List;

import models.Team;
import models.User; 

public class Connection {
	private final String userPath = "user.csv"; 
	private final String teamPath = "teams.csv";

	
	public ArrayList<User> readUser(){
		ArrayList<User> user= new ArrayList<>();
		
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(getUserPath()))
		{
			if (inputStream != null) {
		        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		        String read; 
				while((read = br.readLine()) != null)
				{
					String[] values= read.split(",");
					user.add(new User(values[1], values[0], Integer.parseInt(values[2])));
					
				}
				br.close();
		    } else {
		        System.out.println("File not found");
		    }
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return user; 
	}
	
	public void writeUser(String userPath, List<User> user) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(userPath));
			for(User row : user)
			{
				bw.write(String.format("%s,%s,%d",row.getNim(), row.getName(), row.getId()));
				bw.newLine();
			}
			bw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Team> readTeam(){
		ArrayList<Team> team= new ArrayList<>(); 
		
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(getTeamPath()))
	             
		{
			if (inputStream != null) {
		        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		        String read; 
				while((read = br.readLine()) != null)
				{
					String[] values= read.split(",");
					team.add(new Team(values[0], Integer.parseInt(values[1])));
				}
				br.close();
		    } else {
		        System.out.println("File not found");
		    }
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return team; 
	}
	
	public void writeTeam(String teamPath, List<Team> team) {
		try {
			BufferedWriter bw= new BufferedWriter(new FileWriter(teamPath));
			for(Team row : team)
			{
				bw.write(String.format("%d, %s", row.getId(), row.getNamaTeam()));
				bw.newLine();
			}
			bw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public String getUserPath() {
		return userPath;
	}

	public String getTeamPath() {
		return teamPath;
	}

	
}
