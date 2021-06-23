package br.com.fiap.tdsu.bean;

public class TeamEmployee {
	private int id;
	private String employeeEmail;
	private Team team;

	public TeamEmployee(String employeeEmail, Team team) {
		this.employeeEmail = employeeEmail;
		this.team = team;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public String toString() {
		return team.toString() + "\n-------Funcionário da equipe-------\nEmail: " + employeeEmail;
	}
}
