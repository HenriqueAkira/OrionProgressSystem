package br.com.fiap.tdsu.bo;


public class CompanyBO {
	
	public boolean cnpjIsValid(String cnpj) {
		return cnpj.replace("-", "").replace("/", "").replace(".", "").trim().length() == 14;
	}
}
