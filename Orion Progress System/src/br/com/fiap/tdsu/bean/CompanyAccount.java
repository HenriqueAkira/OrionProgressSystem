package br.com.fiap.tdsu.bean;

public class CompanyAccount extends Account{

	private Company company;

	public CompanyAccount(String email, String password, Company company) {
		this.email = email;
		this.password = password;
		this.company = company;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public String toString() {
		return "\n-------Conta Empresa-------" + "\n Email: " + email + company.toString();
	}

}
