package br.com.fiap.tdsu.bo;

import javax.swing.JOptionPane;

public class TeamEmployeeBO {

	public String validateEmployeeEmailIsCompanyAccountEmail(String employeeEmail, String emailCompanyAccount) {
		
		while (emailCompanyAccount.equals(employeeEmail)) {
			JOptionPane.showMessageDialog(null,
					"O e-mail da empresa não pode ser atribuída como responsável da equipe.");
			employeeEmail = JOptionPane.showInputDialog("Digite novamente");
		}
		
		return employeeEmail;
	}
}
