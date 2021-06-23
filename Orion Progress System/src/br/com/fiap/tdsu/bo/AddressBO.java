package br.com.fiap.tdsu.bo;

public class AddressBO {
	
	public boolean zipCodeIsValid(String zipCode) {
		return zipCode.trim().replace("-", "").length() == 8;
	}
}
