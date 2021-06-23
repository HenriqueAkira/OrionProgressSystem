package br.com.fiap.tdsu.bean;

public class TeamManager {

	private int id;
	private String managerEmail;
	private Team team;
	
	public TeamManager(String managerEmail, Team team) {
		this.managerEmail = managerEmail;
		this.team =  team;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String toString() {
		return team.toString() + "\n-------Responsável pela Equipe-------\nEmail: " + managerEmail;
	}
}
