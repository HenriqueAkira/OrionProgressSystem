package br.com.fiap.tdsu.bo;

import javax.swing.JOptionPane;

public class OfficeBO {

	public boolean officeDescriptionIsValid(String description) {
		return description.length() >= 10;
	}

	public String validateEmailManagerIsCompanyAccountEmail(String emailManager, String emailCompanyAccount) {
		
		while (emailCompanyAccount.equals(emailManager)) {
			JOptionPane.showMessageDialog(null,
					"O e-mail da empresa não pode ser atribuída como responsável da equipe.");
			emailManager = JOptionPane.showInputDialog("Digite novamente");
		}
		
		return emailManager;
	}
}
