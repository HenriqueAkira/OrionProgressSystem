package br.com.fiap.tdsu.bo;

import javax.swing.JOptionPane;

public class TeamEmployeeBO {

	public String validateEmployeeEmailIsCompanyAccountEmail(String employeeEmail, String emailCompanyAccount) {
		
		while (emailCompanyAccount.equals(employeeEmail)) {
			JOptionPane.showMessageDialog(null,
					"O e-mail da empresa n�o pode ser atribu�da como respons�vel da equipe.");
			employeeEmail = JOptionPane.showInputDialog("Digite novamente");
		}
		
		return employeeEmail;
	}
}
