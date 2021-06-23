package br.com.fiap.tdsu.bo;

import javax.swing.JOptionPane;

public class TeamBO {

	public String validateTeamName(String name) {

		while (name.isEmpty() || name.length() < 2) {
			JOptionPane.showMessageDialog(null, "O nome do time deve conter mais do que 1 caracter.");
			name = JOptionPane.showInputDialog("Digite novamente");
		}

		return name;
	}
}
