package repositories;

import models.Team;

import utils.Connection;

import java.util.ArrayList;
import java.util.List;



public class TeamRepository implements Repository<Team> {
		
	public ArrayList<Team> find(String column, String[] kondisi, boolean joinTable, String joinTableName,Connection conn){
		ArrayList<Team> teamData= conn.readTeam();
		ArrayList<Team> teamfound = new ArrayList<>(); 
		
		if(column == null && kondisi == null) {
			return teamData; 
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
		case "team name":
		case "nama tim":
				columnidx= 1;
				break;
		case "idteam":
		case "id team":
		case "teamid":
			columnidx= 0;
			break;
			default:
				System.out.println("Invalid column name");
				return null;
		}
		
		if(columnidx == 1) {
			for (Team team : teamfound) {
				if(team.getNamaTeam().equals(kondisi[1])){
					teamfound.add(team);
					return teamfound; 
			}
		}
		} else if(columnidx == 0) {
			for (Team team : teamfound) {
				if(team.getId()== Integer.parseInt(kondisi[1])) {
					teamfound.add(team);
					return teamfound;
					
				}
			}
		}
		
//		for(String[] row: teamData) {
//			if(row.length > columnidx && row[columnidx].equalsIgnoreCase(kondisi[1])){
//				Team team= new Team();
//				team.setNamaTeam(row[1]);
//				team.setId(Integer.parseInt(row[0]));
//			teamfound.add(team); 
//				
//			}
//		}
		if(teamfound.isEmpty()) {
			return null; 
		}else {
			return teamfound;
		}
}
	
	public Team findOne(String column, String[] kondisi, boolean joinTable, String joinTableName, Connection conn) {
		ArrayList<Team> teamfound = find(column, kondisi, joinTable, joinTableName, conn); 
		return (teamfound != null && !teamfound.isEmpty()) ? teamfound.get(0) : null;
		}
	
	public Team insert(String[] data, Connection conn){
		List<Team> curr = conn.readTeam();
		
		
		int newID= 1 + curr.get(curr.size() - 1).getId();
		
//		int newID= 1; 
//		if(!curr.isEmpty()) {
//			String[] lastrow = curr.get(curr.size() - 1);
//			newID = Integer.parseInt(lastrow[0] + 1);
//		}
//		
//		StringBuilder newrow= new StringBuilder(newID + ",");
//		for(String t : data) {
//			newrow.append(t).append(",");
//		}
//		
//		curr.add(newrow.toString().split(","));
		
		
		
		Team newteam = new Team(data[0], newID); 
		
		conn.writeTeam(conn.getTeamPath(), curr);
	
		return newteam;
		
	}
	
	
}

