package br.com.fiap.tdsu.bo;

import javax.swing.JOptionPane;

public class TeamManagerBO {

	public String  validateManagerEmailIsCompanyAccountEmail(String emailManager, String emailCompanyAccount) {

		while (emailCompanyAccount.equals(emailManager)) {
			JOptionPane.showMessageDialog(null,
					"O e-mail da empresa n�o pode ser atribu�da como respons�vel da equipe.");
			emailManager = JOptionPane.showInputDialog("Digite novamente");
		}
		
		return emailManager;
	}

}
