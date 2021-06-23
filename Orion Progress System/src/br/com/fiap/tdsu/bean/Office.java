package br.com.fiap.tdsu.bean;

public class Office {
	private int id;
	private String name;
	private String description;
	private String managerEmail;
	private String createdBy;
	private Company company;
	
	public Office(String name, String description, String managerEmail, String createdBy, Company company) {
		this.name = name;
		this.description = description;
		this.managerEmail = managerEmail;
		this.createdBy = createdBy;
		this.company = company;
		
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
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
	
	public String toString() {
		return "-------Cargo-------\nNome: " + name + "\nDescrição: " + description 
				+ "\nResposável pelo Cargo: " + managerEmail 
				+ "\nEmpresa: " + company.getName();
	}
}
