package br.com.fiap.tdsu.bo;

public class TaskBO {

	public boolean taskTitleIsValid(String title) {
		return !title.isEmpty() && title.length() > 3;
	}

	public boolean taskDescriptionIsValid(String description) {
		return !description.isEmpty() && description.length() > 10;
	}

	public boolean taskPointIsValid(int point) {
		return point > 0 && point <= 10;
	}

}
