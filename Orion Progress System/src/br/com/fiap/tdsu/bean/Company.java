package br.com.fiap.tdsu.bean;

public class Company {
	private int id;
	private String cnpj;
	private String name;
	private Address address;
	
	public Company(String cnpj, String name, Address address) {
		this.cnpj = cnpj;
		this.name = name;
		this.address = address;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
	
	public String toString() {
		return "\n-------Empresa-------" + "\n CNPJ: " + cnpj + "\n Nome:  " + name + address.toString();
	}
}
