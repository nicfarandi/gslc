package models;

public class User extends Model {
	private String name; 
	private String nim; 
	
	public User(String name, String nim, int teamID) {
		super();
		this.name = name;
		this.nim = nim;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNim() {
		return nim;
	}
	public void setNim(String nim) {
		this.nim = nim;
	}
	
	
}
