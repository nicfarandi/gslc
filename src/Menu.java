import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Team;
import models.User;
import utils.Connection;
import repositories.UserRepository;
import repositories.TeamRepository;
public class Menu {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Menu();
	}
	
	public Menu() {
		Scanner sc = new Scanner(System.in);
		Connection conn = new Connection(); 
		
		UserRepository ur = new UserRepository();
		TeamRepository tr = new TeamRepository(); 
		
		boolean exit= false; 
		
		while(!exit) {
			System.out.println("===Main Menu===");
			System.out.println("1. Insert Data");
			System.out.println("2. Show Data");
			System.out.println("3. Exit");
			System.out.print("Enter your choice: ");
			
			int choice = sc.nextInt();
			sc.nextLine(); 
			
			switch(choice) {
			case 1: 
				insertData(ur, tr, conn, sc); 
				break;
			case 2: 
				showData(ur, tr, conn, sc);
				break;
			case 3: 
				exit= true;
				break;
				default:
					System.out.println("Invalid choice");
			}
		}
		sc.close();
	}
	
	private void insertData(UserRepository ur, TeamRepository tr, Connection conn, Scanner sc){
		System.out.println("Which table do you want to Insert?");
		System.out.println("1. User, 2. Team");
		int pilihan= sc.nextInt();
		sc.nextLine(); 
		
		switch(pilihan) {
		case 1: 
			System.out.println("add name:");
			String name= sc.nextLine();
			System.out.println("add nim:");
			String nim= sc.nextLine();
			System.out.println("add team:");
			String teamname= sc.nextLine();
			
			String[] data= {name, nim, teamname}; 
			
			ArrayList<Team> teamData= conn.readTeam();
			ArrayList<User> userData= conn.readUser();
			int countuser= 0; 
			for (Team i : teamData) {
				if(i.getNamaTeam().equals(teamname)) {
					ur.insert(data, conn);
					for(User u : userData) {
						if(u.getId() == i.getId()) {
							countuser++; 
						}if(countuser == 3) {
							System.out.println("Team is Full.");
							return;
						}
								
					}
				}
			}
			System.out.println("Team not found.");
			break; 
		case 2: 
			System.out.println("add team:");
			String namateam= sc.nextLine(); 
			String[] data2= {namateam}; 
			tr.insert(data2, conn); 
			break;
		default: 
			System.out.println("Invalid choice\n");
			
			
			
			
		}
	}
	
	private void showData(UserRepository ur, TeamRepository tr, Connection conn, Scanner sc){
		System.out.println("Which table to show?");
		System.out.println("1. User, 2. Team");
		int choice = sc.nextInt();
		sc.nextLine(); 
		
		System.out.println("Want to filter by condition?");
		System.out.println("1.yes, 2.no");
		int filterchoice= sc.nextInt();
		sc.nextLine();
	 
		
		String[] kondisi= null; 
		if(filterchoice == 1) {
			System.out.println("Add condition, separate by semicolon");
			String kondisiInput= sc.nextLine(); 
			kondisi= kondisiInput.split(";");
		}
		
		switch(choice) {
		case 1 :
			List<User> users= ur.find(null, kondisi, false, null, conn);
			if(users != null && !users.isEmpty()) {
				for(User ble : users) {
					System.out.println("User: " + ble.getName() + ", NIM: " + ble.getNim() +", Team: " +ble.getId());
				}
			}else {
				System.out.println("No data found.");
			}
			break; 
			
		case 2: 
			List<Team> teams= tr.find(null, kondisi, false, null, conn);
			if(teams != null && !teams.isEmpty()) {
				for(Team cape : teams) {
					System.out.println("Team: " + cape.getNamaTeam() +", TeamID: " + cape.getId());
					
				}
			}else {
				System.out.println("Data not found.");
			}
			break; 
			
			default:
				System.out.println("Error.");
		}

	

	}

}
