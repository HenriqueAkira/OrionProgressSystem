package br.com.fiap.tdsu.bean;

public class Address {

	private String zipCode;
	private String street;
	private int streetNumber;
	private String complement;
	private String neighborhood;
	private String state;
	private String city;

	public Address(String zipCode, String street, int streetNumber, String complement, String neighborhood,
			String state, String city) {
		this.zipCode = zipCode;
		this.street = street;
		this.streetNumber = streetNumber;
		this.complement = complement;
		this.neighborhood = neighborhood;
		this.state = state;
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String toString() {
		return "\n-------Endereço-------\n CEP: " + zipCode + "\n Endereço: " + street + ", " + streetNumber
				+ (complement.isEmpty() ? "" : "\n Complemento: " + complement) + "\n Bairro: " + neighborhood
				+ "\n Cidade: " + city + "\n Estado:  " + state.toUpperCase() ;
	}

}
