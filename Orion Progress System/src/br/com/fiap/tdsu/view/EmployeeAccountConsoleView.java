package br.com.fiap.tdsu.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import br.com.fiap.tdsu.bean.Address;
import br.com.fiap.tdsu.bean.Company;
import br.com.fiap.tdsu.bean.CompanyAccount;
import br.com.fiap.tdsu.bean.EmployeeAccount;
import br.com.fiap.tdsu.bean.EmployeeAccount.EmployeeType;
import br.com.fiap.tdsu.bean.EmployeeTask;
import br.com.fiap.tdsu.bean.EmployeeTask.Status;
import br.com.fiap.tdsu.bean.Office;
import br.com.fiap.tdsu.bean.Task;
import br.com.fiap.tdsu.bean.Team;
import br.com.fiap.tdsu.bean.TeamEmployee;
import br.com.fiap.tdsu.bean.TeamManager;
import br.com.fiap.tdsu.bo.AccountBO;
import br.com.fiap.tdsu.bo.AddressBO;
import br.com.fiap.tdsu.bo.EmployeeAccountBO;
import br.com.fiap.tdsu.bo.EmployeeTaskBO;
import br.com.fiap.tdsu.bo.OfficeBO;
import br.com.fiap.tdsu.bo.TaskBO;
import br.com.fiap.tdsu.bo.TeamBO;
import br.com.fiap.tdsu.bo.TeamEmployeeBO;
import br.com.fiap.tdsu.bo.TeamManagerBO;
import br.com.fiap.tdsu.util.EmailValidator;

// Nome: Marcus Vinicius Titanero Guelfi RM: 86223
// Nome: Gustavo dos Santos de Melo RM: 86394
// Nome: Vinicius Mazinetti RM: 84310
// Nome: Henrique Akira Yasuda RM: 86349
// Nome: Gabriel Costa Santos Meireles RM: 80913
// Nome: Kelly Naomi Mitsuishi RM: 84299

public class EmployeeAccountConsoleView {

	private static EmployeeAccount employeeAccount;
	private static CompanyAccount companyAccount;

	private static Task task;
	private static EmployeeTask employeeTask;
	private static List<Task> taskList; // FOR SIIMULATION
	private static List<EmployeeTask> employeeTaskList; // FOR SIIMULATION
	private static int countId = 0; // INCREMENTATION ID FOR SIMULATION

	public static void main(String[] args) {

		try {
			
			createCompanyAccountForSimulation();
			createEmployeeAccount();
			employeeAccount.logIn();

			if (employeeAccount.getEmployeeType() == EmployeeType.Manager) {
				teamBuildingProcess();
				officeBuildingProcess();
				taskBuildingProcess();
			}

			setForSimulation();

			if (!taskList.isEmpty()) {
				String tasksTitles = "";
				for (Task task : taskList)
					tasksTitles += String.format("\n%s", task.getTitle());

				int optionUpdateTask = JOptionPane.showConfirmDialog(null,
						String.format("Deseja alterar o status da(s) tarefa(s) abaixo:%s", tasksTitles),
						"Alterar Status", JOptionPane.YES_NO_OPTION);

				if (optionUpdateTask == JOptionPane.YES_OPTION)
					updateEmployeeTasks();
			}
			
			employeeAccount.logOut();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado.");
		}
	}

	private static void setEployeeAccountInfomationForSimulation() {
		Office office = new Office("Gerente de TI",
				"O Gerente de Tecnologia da Informa��o (TI) � o profissional respons�vel por gerenciar projetos e opera��es de servi�os de Tecnologia da Informa��o.",
				"gerente.geral@fiap.com", companyAccount.getEmail(), companyAccount.getCompany());
		employeeAccount.setEmail("rogerio.torres@fiap.com");
		employeeAccount.setEmployeeType(EmployeeType.Manager);
		employeeAccount.setOffice(office);
	}

	private static void createCompanyAccountForSimulation() {
		Address address = new Address("01538-001", "Av. Lins de Vasconcelos", 1222,"", "Aclima��o", "SP",
				"S�o Paulo");
		Company company = new Company("83.231.609/0001-07", "FIAP", address);
		companyAccount = new CompanyAccount("manager.gg@fiap.com", "********", company);
	}

	private static void createEmployeeAccount() {
		JOptionPane.showMessageDialog(null, "Cadastre seus dados");
		String name = JOptionPane.showInputDialog("Digite seu nome");

		String password = JOptionPane.showInputDialog("Digite sua senha");
		AccountBO accountBo = new AccountBO();

		while (!accountBo.passwordIsValid(password)) {
			JOptionPane.showMessageDialog(null, "Senha inv�lida. Senha deve conter no m�nimo 8 caracteres.");
			password = JOptionPane.showInputDialog("Digite novamente");
		}
		Address address = getAddressInfo();
		
		employeeAccount = new EmployeeAccount(name, password, address);
		setEployeeAccountInfomationForSimulation();
		JOptionPane.showMessageDialog(null, employeeAccount);
	}

	private static Address getAddressInfo() {

		JOptionPane.showMessageDialog(null, "Cadastre o endere�o");
		String zipCode = JOptionPane.showInputDialog("Digite o CEP");
		AddressBO addressBo = new AddressBO();

		while (!addressBo.zipCodeIsValid(zipCode)) {
			JOptionPane.showMessageDialog(null, "CEP inv�lido");
			zipCode = JOptionPane.showInputDialog("Digite novamente");
		}

		String street = JOptionPane.showInputDialog("Digite o endere�o");
		int streetNumber = Integer.parseInt(JOptionPane.showInputDialog("Digite o n�mero do endere�o"));

		String complement = "";
		int setComplement = JOptionPane.showConfirmDialog(null, "Voc� deseja inserir um complemento?", "Complemento",
				JOptionPane.YES_NO_OPTION);
		if (setComplement == JOptionPane.YES_OPTION)
			complement = JOptionPane.showInputDialog("Digite o complemento");

		String neighborhood = JOptionPane.showInputDialog("Digite o bairro");
		String city = JOptionPane.showInputDialog("Digite a cidade");
		String state = JOptionPane.showInputDialog("Digite o estado");

		return new Address(zipCode, street, streetNumber, complement, neighborhood, state, city);
	}

	private static void taskBuildingProcess() {
		// CREATING TASK
		int createAction = JOptionPane.YES_OPTION;
		int assigAction = JOptionPane.YES_OPTION;
		do {
			Task task = createTask();
			do {
				assignEmployeeToTask(task);
				assigAction = JOptionPane.showConfirmDialog(null, "Gostaria de atribuir outro funcion�rio a tarefa?",
						"Tarefa", JOptionPane.YES_NO_OPTION);
			} while (assigAction == JOptionPane.YES_OPTION);

			createAction = JOptionPane.showConfirmDialog(null, "Gostaria de criar uma nova Tarefa?", "Tarefa",
					JOptionPane.YES_NO_OPTION);
		} while (createAction == JOptionPane.YES_OPTION);

	}

	private static void officeBuildingProcess() {
		// CREATING OFFICE
		int createAction = JOptionPane.YES_OPTION;
		int assigAction = JOptionPane.YES_OPTION;
		do {

			Office office = createOffice();
			do {
				assignEmployeeToOffice(office);
				assigAction = JOptionPane.showConfirmDialog(null, "Gostaria de atribuir outro funcion�rio ao Cargo?",
						"Cargo", JOptionPane.YES_NO_OPTION);

			} while (assigAction == JOptionPane.YES_OPTION);

			createAction = JOptionPane.showConfirmDialog(null, "Gostaria de criar um novo Cargo?", "Cargo",
					JOptionPane.YES_NO_OPTION);

		} while (createAction == JOptionPane.YES_OPTION);

	}

	private static void teamBuildingProcess() {
		// CREATING TEAM
		int createAction = JOptionPane.YES_OPTION;
		int assigAction = JOptionPane.YES_OPTION;

		do {
			Team team = createTeam();

			do {
				assignManagerToTeam(team);
				assigAction = JOptionPane.showConfirmDialog(null, "Deseja atribuir mais um gerenciador para equipe?",
						"Equipe", JOptionPane.YES_NO_OPTION);
			} while (assigAction == JOptionPane.YES_OPTION);

			assigAction = JOptionPane.YES_OPTION;
			do {
				assingEmployeeToTeam(team);
				assigAction = JOptionPane.showConfirmDialog(null, "Deseja atribuir mais um funcion�rio a equipe?",
						"Equipe", JOptionPane.YES_NO_OPTION);
			} while (assigAction == JOptionPane.YES_OPTION);

			createAction = JOptionPane.showConfirmDialog(null, "Deseja criar uma nova Equipe?", "Equipe",
					JOptionPane.YES_NO_OPTION);

		} while (createAction == JOptionPane.YES_OPTION);
	}

	private static Team createTeam() {
		JOptionPane.showMessageDialog(null, "Cadastre uma equipe");
		String name = JOptionPane.showInputDialog("Digite o nome da equipe");
		TeamBO teamBo = new TeamBO();

		Team team = new Team(teamBo.validateTeamName(name), companyAccount.getEmail(), companyAccount.getCompany());
		JOptionPane.showMessageDialog(null, team);
		return team;
	}

	private static void assignManagerToTeam(Team team) {
		JOptionPane.showMessageDialog(null, "Atribua um respons�vel para a equipe");
		String managerEmail = JOptionPane.showInputDialog("Digite o e-mail do respons�vel da equipe");
		TeamManagerBO teamManagerBo = new TeamManagerBO();

		while (!EmailValidator.emailIsValid(managerEmail)) {
			JOptionPane.showMessageDialog(null, "Email inv�lido.");
			managerEmail = JOptionPane.showInputDialog("Digite novamente");
		}

		TeamManager teamManager = new TeamManager(
				teamManagerBo.validateManagerEmailIsCompanyAccountEmail(managerEmail, companyAccount.getEmail()), team);
		JOptionPane.showMessageDialog(null, teamManager);
	}

	private static void assingEmployeeToTeam(Team team) {
		JOptionPane.showMessageDialog(null, "Atribua um funcion�rio a equipe");
		String employeeEmail = JOptionPane.showInputDialog("Digite o e-mail do funcion�rio");
		TeamEmployeeBO teamEmployeeBo = new TeamEmployeeBO();

		while (!EmailValidator.emailIsValid(employeeEmail)) {
			JOptionPane.showMessageDialog(null, "Email inv�lido.");
			employeeEmail = JOptionPane.showInputDialog("Digite novamente");
		}

		TeamEmployee teamEmployee = new TeamEmployee(
				teamEmployeeBo.validateEmployeeEmailIsCompanyAccountEmail(employeeEmail, companyAccount.getEmail()),
				team);
		JOptionPane.showMessageDialog(null, teamEmployee);

	}

	private static Office createOffice() {
		JOptionPane.showMessageDialog(null, "Cadastre um cargo");

		String name = JOptionPane.showInputDialog("Digite o nome do cargo");
		OfficeBO officeBo = new OfficeBO();
		String description = JOptionPane.showInputDialog("Digite a descri��o de cargo");

		while (!officeBo.officeDescriptionIsValid(description)) {
			JOptionPane.showMessageDialog(null, "A descri��o de cargo deve ter no m�nimo 10 caracteres.");
			description = JOptionPane.showInputDialog("Digite novamente");
		}

		String emailManager = JOptionPane.showInputDialog("Digite o e-mail do respons�vel direto do cargo");

		while (!EmailValidator.emailIsValid(emailManager)) {
			JOptionPane.showMessageDialog(null, "Email inv�lido.");
			emailManager = JOptionPane.showInputDialog("Digite novamente");
		}

		Office office = new Office(name, description,
				officeBo.validateEmailManagerIsCompanyAccountEmail(emailManager, companyAccount.getEmail()),
				companyAccount.getEmail(), companyAccount.getCompany());

		JOptionPane.showMessageDialog(null, office.toString());
		return office;
	}

	private static void assignEmployeeToOffice(Office office) {
		JOptionPane.showMessageDialog(null, "Atribua um funcion�rio para um cargo");
		String employeeEmail = JOptionPane.showInputDialog("Digite o e-mail do funcion�rio");
		EmployeeAccountBO employeeAccBo = new EmployeeAccountBO();

		while (!EmailValidator.emailIsValid(employeeEmail)) {
			JOptionPane.showMessageDialog(null, "Email inv�lido.");
			employeeEmail = JOptionPane.showInputDialog("Digite novamente");
		}

		String[] employeeTypeList = Arrays.stream(EmployeeType.class.getEnumConstants()).map(Enum::name)
				.toArray(String[]::new);

		String employeeType = (String) JOptionPane.showInputDialog(null,
				"Qual perfil deseja atribuir a este funcion�rio?", "Tipo de Funcion�rio", JOptionPane.QUESTION_MESSAGE,
				null, employeeTypeList, employeeTypeList[0]);

		EmployeeAccount employeeAccount = new EmployeeAccount(
				employeeAccBo.validateEmployeeEmailIsCompanyAccountEmail(employeeEmail, companyAccount.getEmail()),
				office, EmployeeType.valueOf(employeeType));

		JOptionPane.showMessageDialog(null, employeeAccBo.getInfoEmployeeAccountAfterAssignToOffice(employeeAccount));

	}

	private static Task createTask() {
		JOptionPane.showMessageDialog(null, "Cadastre uma Tarefa");
		TaskBO taskBo = new TaskBO();

		String title = JOptionPane.showInputDialog("Digite o t�tulo da tarefa");

		while (!taskBo.taskTitleIsValid(title)) {
			JOptionPane.showMessageDialog(null, "O T�tulo da tarefa deve ter no m�nimo 3 caracteres.");
			title = JOptionPane.showInputDialog("Digite novamente");
		}

		String description = JOptionPane.showInputDialog("Digite a descri��o da tarefa");

		while (!taskBo.taskDescriptionIsValid(description)) {
			JOptionPane.showMessageDialog(null, "A Descri��o da tarefa deve ter no m�nimo 10 caracteres.");
			description = JOptionPane.showInputDialog("Digite novamente");
		}

		int point = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de pontos da Tarefa"));
		while (!taskBo.taskPointIsValid(point)) {
			JOptionPane.showMessageDialog(null, "A Pontua��o da tarefa ser entre 0 � 10.");
			point = Integer.parseInt(JOptionPane.showInputDialog("Digite novamente"));
		}
		
		task = new Task(title, description, point, employeeAccount.getEmail(), companyAccount.getCompany().getId());

		JOptionPane.showMessageDialog(null, task);

		task.setId(++countId);
		// SAVE METHOD FOR SIMULATION
		if (taskList != null)
			taskList.add(task);
		else {
			taskList = new ArrayList<Task>();
			taskList.add(task);
		}

		return task;
	}

	private static void assignEmployeeToTask(Task task) {
		String employeeEmail = JOptionPane.showInputDialog("Digite o e-mail do funcion�rio");
		EmployeeTaskBO employeeTaskBo = new EmployeeTaskBO();

		while (!EmailValidator.emailIsValid(employeeEmail)) {
			JOptionPane.showMessageDialog(null, "Email inv�lido.");
			employeeEmail = JOptionPane.showInputDialog("Digite novamente");
		}

		 employeeTask = new EmployeeTask(
				employeeTaskBo.validateEmployeeEmailIsCompanyAccountEmail(employeeEmail, companyAccount.getEmail()),
				task);


		// SAVE METHOD FOR SIMULATION
		if (employeeTaskList != null)
			employeeTaskList.add(employeeTask);
		else {
			employeeTaskList = new ArrayList<EmployeeTask>();
			employeeTaskList.add(employeeTask);
		}

		JOptionPane.showMessageDialog(null, employeeTask);

	}

	private static void setForSimulation() {
		// ADD TASK FOR SIMULATION
		if (task == null) {
			task = new Task("Tiltle Test - FOR SIMULATION", "Teste Description", 10, "TesteUser@gmail.com",
					companyAccount.getCompany().getId());
			taskList = new ArrayList<Task>();
			taskList.add(task);
		}

		// ASSING TASK TO NON-MANAGERIAL EMPLOYEE FOR SIMULATION
		if (employeeTask == null) {
			employeeTask = new EmployeeTask(employeeAccount.getEmail(), task);
			employeeTaskList = new ArrayList<EmployeeTask>();
			employeeTaskList.add(employeeTask);
		}
	}

	private static void updateEmployeeTasks() {

		boolean isManagerTask = false;
		EmployeeTaskBO employeeTaskBo = new EmployeeTaskBO();
		
		for (Task task : taskList) {

			if (task.getCreatedBy() == employeeAccount.getEmail())
				isManagerTask = true;

			Predicate<EmployeeTask> byTask = x -> x.getTask().getId() == task.getId();
			List<EmployeeTask> employeeByTask = employeeTaskList.stream().filter(byTask).collect(Collectors.toList());

			for (EmployeeTask employeeTask : employeeByTask) {

				String[] statusTaskList = employeeTaskBo.getStatusList(isManagerTask, employeeTask.getStatus());

				// FOR SIMULATION
				if (isManagerTask)
					employeeTask.setStatus(Status.WaitingForApproval);

				if (employeeAccount.getEmail() == employeeTask.getEmployeeEmail() || isManagerTask) {

					int optionUpdateTask = JOptionPane.showConfirmDialog(null,
							String.format("Deseja alterar o status da tarefa : %s" + "\nDo funcion�rio: %s",
									task.getTitle(), employeeTask.getEmployeeEmail()),
							"Tarefa - Alterar Status", JOptionPane.YES_NO_OPTION);

					if (optionUpdateTask == JOptionPane.YES_OPTION) {

						String status = (String) JOptionPane.showInputDialog(null, "Altere o status para:",
								"Altere o status da tarefa", JOptionPane.QUESTION_MESSAGE, null, statusTaskList,
								statusTaskList[0]);

						JOptionPane.showMessageDialog(null, String.format("Alterando status: %s \n Para o status: %s",
								employeeTask.getStatus().toString(), status.toString()));

						employeeTask.setStatus(Status.valueOf(status));

						JOptionPane.showMessageDialog(null, "Status Alterado com sucesso");

						if (employeeTask.getStatus() == Status.Approved) {
							EmployeeAccount employeeToSetPoint = employeeAccount.getEmployeeByEmailAndCompanyId(
									employeeTask.getEmployeeEmail(), companyAccount.getId());

							employeeToSetPoint.setPoint(task.getPoint());

						}
					}

				}

			}

		}

	}
}
