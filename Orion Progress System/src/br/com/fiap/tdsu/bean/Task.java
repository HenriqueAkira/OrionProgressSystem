package br.com.fiap.tdsu.bean;

import java.util.Date;

public class Task {

	private int id;
	private int point;
	private String title;
	private String description;
	private Date createIn;
	private String createdBy;
	private int companyId;
	private boolean isEnable;
	
	public Task(String title, String description,int point, String createdBy, int companyId) {
		this.point = point;
		this.title = title;
		this.description = description;
		this.createdBy = createdBy;
		this.companyId = companyId;
		createIn = new Date();
		isEnable = true;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateIn() {
		return createIn;
	}
	public void setCreateIn(Date createIn) {
		this.createIn = createIn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public boolean isEnable() {
		return isEnable;
	}
	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}
	
	public String toString() {
		return "-------Tarefa-------" + "\n Título:  " + title + "\n Descrição: " + description
				+ "\n Quantidade de Pontos: " + point + "\n Criado em: " + createIn + "\n Ativo: "
				+ (isEnable ? "Sim" : "Não");

	}
}
