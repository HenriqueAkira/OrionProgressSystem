package br.com.fiap.tdsu.bo;

import javax.swing.JOptionPane;

import br.com.fiap.tdsu.bean.EmployeeAccount;

public class EmployeeAccountBO {

	public String validateEmployeeEmailIsCompanyAccountEmail(String employeeEmail, String emailCompanyAccount) {

		while (emailCompanyAccount.equals(employeeEmail)) {
			JOptionPane.showMessageDialog(null,
					"O e-mail da empresa não pode ser atribuída como responsável da equipe.");
			employeeEmail = JOptionPane.showInputDialog("Digite novamente");
		}

		return employeeEmail;
	}

	public String getInfoEmployeeAccountAfterAssignToOffice(EmployeeAccount employeeAccount) {
		return "-------Funcionário atribuído ao Cargo-------\nEmail: " + employeeAccount.getEmail()
				+ "\nTipo de Funcionário: " + employeeAccount.getEmployeeType() + "\nCargo: "
				+ employeeAccount.getOffice().getName();
	}
}
