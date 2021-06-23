package br.com.fiap.tdsu.bean;

public class Team {
	private int id;
	private String name;
	private Company company;
	private String createdBy;
	private boolean isEnabled; 
	
	public Team (String name, String createdBy, Company company) {
		this.name = name;
		this.createdBy = createdBy;
		this.company = company;
		isEnabled = true;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	public String toString() {
		return "\n-------Equipe-------\nName: " + name + "\nAtivo?: " + (isEnabled ? "Sim":"Não"
				+ "\nEmpresa: " + company.getName());
	}
	
}
