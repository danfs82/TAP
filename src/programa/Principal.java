package programa;


import javax.swing.JOptionPane;

import controle.Controle;
import dados.GerenciadorServidor;
import dados.RegistroServidor;

public class Principal {

	public static void main(String Arg[]) {
		// Classe que gerencia o arquivo de servidor

		Controle controle = new Controle();
		controle.executar();

	}
}
