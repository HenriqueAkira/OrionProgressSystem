package br.com.fiap.tdsu.bean;

public class EmployeeTask {

	private int id;
	private String employeeEmail;
	private Task task;
	private Status status;

	public EmployeeTask(String employeeEmail, Task task) {
		this.employeeEmail = employeeEmail;
		this.task = task;
		status = Status.ToDo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public enum Status {
		ToDo, WaitingForApproval, Approved, Disapprove
	}

	public String toString() {
		return "-------Tarefa Atribuída-------" + "\nEmail:  " + employeeEmail + "\nStatus: " + status + "\nTarefa: "
				+ task.getTitle();
	}
}
