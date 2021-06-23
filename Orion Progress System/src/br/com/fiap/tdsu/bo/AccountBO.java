package br.com.fiap.tdsu.bo;

public class AccountBO {
	
	public boolean passwordIsValid(String password) {
		return password.length() >= 8;
	}
}
