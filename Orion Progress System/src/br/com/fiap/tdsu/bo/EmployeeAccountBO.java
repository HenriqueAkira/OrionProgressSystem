package br.com.fiap.tdsu.bo;

import javax.swing.JOptionPane;

import br.com.fiap.tdsu.bean.EmployeeAccount;

public class EmployeeAccountBO {

	public String validateEmployeeEmailIsCompanyAccountEmail(String employeeEmail, String emailCompanyAccount) {

		while (emailCompanyAccount.equals(employeeEmail)) {
			JOptionPane.showMessageDialog(null,
					"O e-mail da empresa n�o pode ser atribu�da como respons�vel da equipe.");
			employeeEmail = JOptionPane.showInputDialog("Digite novamente");
		}

		return employeeEmail;
	}

	public String getInfoEmployeeAccountAfterAssignToOffice(EmployeeAccount employeeAccount) {
		return "-------Funcion�rio atribu�do ao Cargo-------\nEmail: " + employeeAccount.getEmail()
				+ "\nTipo de Funcion�rio: " + employeeAccount.getEmployeeType() + "\nCargo: "
				+ employeeAccount.getOffice().getName();
	}
}
