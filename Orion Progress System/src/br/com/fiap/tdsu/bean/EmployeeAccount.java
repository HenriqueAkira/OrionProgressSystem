package br.com.fiap.tdsu.bean;

public class EmployeeAccount extends Account {

	private String name;
	private Address address;
	private Office office;
	private int point;
	private boolean isEnable;
	private EmployeeType employeeType;

	public EmployeeAccount() {
		
	}
	
	public EmployeeAccount(String email, Office office, EmployeeType employeeType) {
		this.email = email;
		this.office = office;
		this.employeeType = employeeType;
	}

	public EmployeeAccount(String name, String password, Address address) {
		this.name = name;
		this.password = password;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public boolean isEnable() {
		return isEnable;
	}

	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public enum EmployeeType {
		Operational, Manager
	}

	public EmployeeAccount getEmployeeByEmailAndCompanyId(String employeeEmail, int companyId) {	
		//METHOD TO SIMULATE GET EMPLOYEE ACCOUNT BY EMPLOYEE EMAIL AND COMPANY ID
		return new EmployeeAccount();
	}

	public String toString() {
		return "-------Funcionário-------" + "\nNome: " + name + "\nEmail: " + email + address.toString() + "\n" +
				 office.toString() + "\nPoint: " + point + "\nTipo de Funcionário: " + employeeType + "\n Ativo: "
				+ (isEnable ? "Sim" : "Não");
	}
}
