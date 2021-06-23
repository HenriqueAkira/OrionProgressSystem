package br.com.fiap.tdsu.view;

import java.util.Arrays;

import javax.swing.JOptionPane;

import br.com.fiap.tdsu.bean.Address;
import br.com.fiap.tdsu.bean.Company;
import br.com.fiap.tdsu.bean.CompanyAccount;
import br.com.fiap.tdsu.bean.EmployeeAccount;
import br.com.fiap.tdsu.bean.EmployeeTask;
import br.com.fiap.tdsu.bean.Office;
import br.com.fiap.tdsu.bean.Task;
import br.com.fiap.tdsu.bean.Team;
import br.com.fiap.tdsu.bean.TeamEmployee;
import br.com.fiap.tdsu.bean.TeamManager;
import br.com.fiap.tdsu.bean.EmployeeAccount.EmployeeType;
import br.com.fiap.tdsu.bo.AccountBO;
import br.com.fiap.tdsu.bo.AddressBO;
import br.com.fiap.tdsu.bo.CompanyBO;
import br.com.fiap.tdsu.bo.EmployeeAccountBO;
import br.com.fiap.tdsu.bo.EmployeeTaskBO;
import br.com.fiap.tdsu.bo.OfficeBO;
import br.com.fiap.tdsu.bo.TaskBO;
import br.com.fiap.tdsu.bo.TeamBO;
import br.com.fiap.tdsu.bo.TeamEmployeeBO;
import br.com.fiap.tdsu.bo.TeamManagerBO;
import br.com.fiap.tdsu.util.EmailValidator;

public class CompanyAccountConsoleView {

	// Nome: Marcus Vinicius Titanero Guelfi RM: 86223
	// Nome: Gustavo dos Santos de Melo RM: 86394
	// Nome: Vinicius Mazinetti RM: 84310
	// Nome: Henrique Akira Yasuda RM: 86349
	// Nome: Gabriel Costa Santos Meireles RM: 80913
	// Nome: Kelly Naomi Mitsuishi RM: 84299

	private static CompanyAccount companyAccount;

	public static void main(String[] args) {

		try {
			
			createAccountCompany();
			companyAccount.logIn();
			
			teamBuildingProcess();
			officeBuildingProcess();
			taskBuildingProcess();
			
			companyAccount.logOut();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado.");
		}
	}

	private static void createAccountCompany() {
		AccountBO accountBo = new AccountBO();

		JOptionPane.showMessageDialog(null, "Cadastre sua conta empresa");
		String email = JOptionPane.showInputDialog("Digite seu e-mail");

		while (!EmailValidator.emailIsValid(email)) {
			JOptionPane.showMessageDialog(null, "Email inválido.");
			email = JOptionPane.showInputDialog("Digite novamente");
		}

		String password = JOptionPane.showInputDialog("Digite sua senha");
		while (!accountBo.passwordIsValid(password)) {
			JOptionPane.showMessageDialog(null, "Senha inválida. Senha deve conter no mínimo 8 caracteres.");
			password = JOptionPane.showInputDialog("Digite novamente");
		}
		// CREATING COMPANY
		Company company = getCompanyInfo();

		companyAccount = new CompanyAccount(email, password, company);

		JOptionPane.showMessageDialog(null, companyAccount);
	}

	private static Company getCompanyInfo() {
		JOptionPane.showMessageDialog(null, "Cadastre sua empresa");
		String cnpj = JOptionPane.showInputDialog("Digite o CNPJ");
		CompanyBO companyBo = new CompanyBO();

		while (!companyBo.cnpjIsValid(cnpj)) {
			JOptionPane.showMessageDialog(null, "CNPJ inválido.");
			cnpj = JOptionPane.showInputDialog("Digite novamente");
		}

		String name = JOptionPane.showInputDialog("Digite o nome da empresa");
		Address address = getAddressInfo();

		return new Company(cnpj, name, address);
	}

	private static Address getAddressInfo() {

		JOptionPane.showMessageDialog(null, "Cadastre o endereço");
		String zipCode = JOptionPane.showInputDialog("Digite o CEP");
		AddressBO addressBo = new AddressBO();

		while (!addressBo.zipCodeIsValid(zipCode)) {
			JOptionPane.showMessageDialog(null, "CEP inválido");
			zipCode = JOptionPane.showInputDialog("Digite novamente");
		}

		String street = JOptionPane.showInputDialog("Digite o endereço");
		int streetNumber = Integer.parseInt(JOptionPane.showInputDialog("Digite o número do endereço"));

		String complement = "";
		int setComplement = JOptionPane.showConfirmDialog(null, "Você deseja inserir um complemento?", "Complemento",
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
				assigAction = JOptionPane.showConfirmDialog(null, "Gostaria de atribuir outro funcionário a tarefa?",
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
				assigAction = JOptionPane.showConfirmDialog(null, "Gostaria de atribuir outro funcionário ao Cargo?",
						"Cargo", JOptionPane.YES_NO_OPTION);

			} while (assigAction == JOptionPane.YES_OPTION);

			createAction = JOptionPane.showConfirmDialog(null, "Gostaria de criar uma novo Cargo?", "Cargo",
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
				assigAction = JOptionPane.showConfirmDialog(null, "Deseja atribuir mais um funcionário a equipe?",
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
		JOptionPane.showMessageDialog(null, "Atribua um responsável para a equipe");
		String managerEmail = JOptionPane.showInputDialog("Digite o e-mail do responsável da equipe");
		TeamManagerBO teamManagerBo = new TeamManagerBO();

		while (!EmailValidator.emailIsValid(managerEmail)) {
			JOptionPane.showMessageDialog(null, "Email inválido.");
			managerEmail = JOptionPane.showInputDialog("Digite novamente");
		}

		TeamManager teamManager = new TeamManager(
				teamManagerBo.validateManagerEmailIsCompanyAccountEmail(managerEmail, companyAccount.getEmail()), team);
		JOptionPane.showMessageDialog(null, teamManager);
	}

	private static void assingEmployeeToTeam(Team team) {
		JOptionPane.showMessageDialog(null, "Atribua um funcionário a equipe");
		String employeeEmail = JOptionPane.showInputDialog("Digite o e-mail do funcionário");
		TeamEmployeeBO teamEmployeeBo = new TeamEmployeeBO();

		while (!EmailValidator.emailIsValid(employeeEmail)) {
			JOptionPane.showMessageDialog(null, "Email inválido.");
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
		String description = JOptionPane.showInputDialog("Digite a descrição de cargo");

		while (!officeBo.officeDescriptionIsValid(description)) {
			JOptionPane.showMessageDialog(null, "A descrição de cargo deve ter no mínimo 10 caracteres.");
			description = JOptionPane.showInputDialog("Digite novamente");
		}

		String emailManager = JOptionPane.showInputDialog("Digite o e-mail do responsável direto do cargo");

		while (!EmailValidator.emailIsValid(emailManager)) {
			JOptionPane.showMessageDialog(null, "Email inválido.");
			emailManager = JOptionPane.showInputDialog("Digite novamente");
		}

		Office office = new Office(name, description,
				officeBo.validateEmailManagerIsCompanyAccountEmail(emailManager, companyAccount.getEmail()),
				companyAccount.getEmail(), companyAccount.getCompany());

		JOptionPane.showMessageDialog(null, office.toString());
		return office;
	}

	private static void assignEmployeeToOffice(Office office) {
		JOptionPane.showMessageDialog(null, "Atribua um funcionário para um cargo");
		String employeeEmail = JOptionPane.showInputDialog("Digite o e-mail do funcionário");
		EmployeeAccountBO employeeAccBo = new EmployeeAccountBO();

		while (!EmailValidator.emailIsValid(employeeEmail)) {
			JOptionPane.showMessageDialog(null, "Email inválido.");
			employeeEmail = JOptionPane.showInputDialog("Digite novamente");
		}

		String[] employeeTypeList = Arrays.stream(EmployeeType.class.getEnumConstants()).map(Enum::name)
				.toArray(String[]::new);

		String employeeType = (String) JOptionPane.showInputDialog(null,
				"Qual perfil deseja atribuir a este funcionário?", "Tipo de Funcionário", JOptionPane.QUESTION_MESSAGE,
				null, employeeTypeList, employeeTypeList[0]);

		EmployeeAccount employeeAccount = new EmployeeAccount(
				employeeAccBo.validateEmployeeEmailIsCompanyAccountEmail(employeeEmail, companyAccount.getEmail()),
				office, EmployeeType.valueOf(employeeType));

		JOptionPane.showMessageDialog(null, employeeAccBo.getInfoEmployeeAccountAfterAssignToOffice(employeeAccount));
	}

	private static Task createTask() {
		JOptionPane.showMessageDialog(null, "Cadastre uma Tarefa");
		TaskBO taskBo = new TaskBO();

		String title = JOptionPane.showInputDialog("Digite o título da tarefa");

		while (!taskBo.taskTitleIsValid(title)) {
			JOptionPane.showMessageDialog(null, "O Título da tarefa deve ter no mínimo 3 caracteres.");
			title = JOptionPane.showInputDialog("Digite novamente");
		}

		String description = JOptionPane.showInputDialog("Digite a descrição da tarefa");

		while (!taskBo.taskDescriptionIsValid(description)) {
			JOptionPane.showMessageDialog(null, "A Descrição da tarefa deve ter no mínimo 10 caracteres.");
			description = JOptionPane.showInputDialog("Digite novamente");
		}

		int point = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de pontos da Tarefa"));
		while (!taskBo.taskPointIsValid(point)) {
			JOptionPane.showMessageDialog(null, "A Pontuação da tarefa ser entre 0 á 10.");
			point = Integer.parseInt(JOptionPane.showInputDialog("Digite novamente"));
		}

		Task task = new Task(title, description, point, companyAccount.getEmail(), companyAccount.getCompany().getId());

		JOptionPane.showMessageDialog(null, task);
		return task;
	}

	private static void assignEmployeeToTask(Task task) {
		String employeeEmail = JOptionPane.showInputDialog("Digite o e-mail do funcionário");
		EmployeeTaskBO employeeTaskBo = new EmployeeTaskBO();

		while (!EmailValidator.emailIsValid(employeeEmail)) {
			JOptionPane.showMessageDialog(null, "Email inválido.");
			employeeEmail = JOptionPane.showInputDialog("Digite novamente");
		}

		EmployeeTask employeeTask = new EmployeeTask(
				employeeTaskBo.validateEmployeeEmailIsCompanyAccountEmail(employeeEmail, companyAccount.getEmail()),
				task);

		JOptionPane.showMessageDialog(null, employeeTask);

	}

}
