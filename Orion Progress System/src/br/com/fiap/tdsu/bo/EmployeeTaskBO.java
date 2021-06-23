package br.com.fiap.tdsu.bo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

import br.com.fiap.tdsu.bean.EmployeeTask.Status;

public class EmployeeTaskBO {
	
	public String validateEmployeeEmailIsCompanyAccountEmail(String employeeEmail, String emailCompanyAccount) {

		while (emailCompanyAccount.equals(employeeEmail)) {
			JOptionPane.showMessageDialog(null,
					"O e-mail da empresa não pode ser atribuída como responsável da equipe.");
			employeeEmail = JOptionPane.showInputDialog("Digite novamente");
		}

		return employeeEmail;
	}
	
	public String[] getStatusList(boolean isManagerTask, Status currentStatus) {
		List<String> enumNames = Stream.of(Status.values()).map(Enum::name).collect(Collectors.toList());
		enumNames.removeIf(x -> x.contentEquals(currentStatus.toString()));

		if (isManagerTask) {
			enumNames.removeIf(x -> x.contentEquals(Status.ToDo.toString()));
			enumNames.removeIf(x -> x.contentEquals(Status.WaitingForApproval.toString()));
		} else {
			enumNames.removeIf(x -> x.contentEquals(Status.Approved.toString()));
			enumNames.removeIf(x -> x.contentEquals(Status.Disapprove.toString()));
		}

		return enumNames.stream().toArray(String[]::new);
	}
}
