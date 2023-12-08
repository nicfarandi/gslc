package models;

public abstract class Model {
	private int teamID;
	
	public int getId()
	{
		return teamID; 
	}
	
	public void setId(int teamID)
	{
		this.teamID= teamID; 
	}
}
