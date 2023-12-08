package models;

public class Team extends Model {
  private String namaTeam;

public Team(String namaTeam, int teamID) {
	super();
	this.namaTeam = namaTeam;
}

public String getNamaTeam() {
	return namaTeam;
}

public void setNamaTeam(String namaTeam) {
	this.namaTeam = namaTeam;
} 
  
  
  
  
}
