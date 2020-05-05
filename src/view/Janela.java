package view;

import javax.swing.*;

public class Janela extends JFrame {
	// Classe de interface gráfica

	private JMenuBar menubar;
	private JMenu menuIncluir;
	public JMenuItem menuIncluirIncluir;
	private JMenu menuExcluir;
	public JMenuItem menuExcluirMatricula;
	public JMenuItem menuExcluirNome;
	private JMenu menuAtualizar;
	public JMenuItem menuAtualizarMatricula;
	public JMenuItem menuAtualizarNome;
	private JMenu menuConsultar;
	public JMenuItem menuConsultarMatricula;
	public JMenuItem menuConsultarNome;
	private JMenu menuListar;
	public JMenuItem menuListarFisico;
	public JMenuItem menuListarLogico;
	private JMenu menuPropriedades;
	public JMenuItem menuPropriedadesTamanho;
	public JMenuItem menuPropriedadesQuantidade;
	public JMenuItem menuPropriedadesEsvaziar;
	private JMenu menuSair;
	public JMenuItem menuSairSair;

	private JLabel jLTitulo;
	private JLabel jLMatricula;
	public JTextField jTMatricula;
	private JLabel jLNome;
	public JTextField jTNome;
	private JLabel jLSetor;
	public JTextField jTSetor;
	private JLabel jLSalario;
	public JTextField jTSalario;
	private JLabel jLClasse;
	public JTextField jTClasse;
	public JButton jBOk;
	public JButton jBLimpar;
	public JButton jBVoltar;

	private JLabel jLGenerico;
	private JTextField jTGenerico;

	public JPanel jPPainel;

	public Janela() {
		inicializar();
	}

	public void inicializar() {

		this.setBounds(500, 100, 450, 500);

		jPPainel = new JPanel();
		jPPainel.setSize(450, 400);
		jPPainel.setLocation(0, 0);

		menubar = new JMenuBar();
		menuIncluir = new JMenu("Incluir");
		menuIncluir.setMnemonic('I');
		menuExcluir = new JMenu("Excluir");
		menuExcluir.setMnemonic('E');
		menuAtualizar = new JMenu("Atualizar");
		menuAtualizar.setMnemonic('A');
		menuConsultar = new JMenu("Consultar");
		menuConsultar.setMnemonic('C');
		menuListar = new JMenu("Listar");
		menuListar.setMnemonic('L');
		menuPropriedades = new JMenu("Propriedades");
		menuPropriedades.setMnemonic('P');
		menuSair = new JMenu("Sair");
		menuSair.setMnemonic('S');

		menuIncluirIncluir = new JMenuItem("Incluir");
		menuExcluirMatricula = new JMenuItem("Por matrícula");
		menuExcluirNome = new JMenuItem("Por nome");
		menuAtualizarMatricula = new JMenuItem("Por matrícula");
		menuAtualizarNome = new JMenuItem("Por nome");
		menuConsultarMatricula = new JMenuItem("Por matrícula");
		menuConsultarNome = new JMenuItem("Por nome");
		menuListarFisico = new JMenuItem("Físico");
		menuListarLogico = new JMenuItem("Lógico");
		menuPropriedadesTamanho = new JMenuItem("Tamanho do Arquivo");
		menuPropriedadesQuantidade = new JMenuItem("Quantidade de registros");
		menuPropriedadesEsvaziar = new JMenuItem("Esvaziar Arquivo");
		menuSairSair = new JMenuItem("Sair");

		menuIncluir.add(menuIncluirIncluir);
		menuExcluir.add(menuExcluirMatricula);
		menuExcluir.add(menuExcluirNome);
		menuAtualizar.add(menuAtualizarMatricula);
		menuAtualizar.add(menuAtualizarNome);
		menuConsultar.add(menuConsultarMatricula);
		menuConsultar.add(menuConsultarNome);
		menuListar.add(menuListarFisico);
		menuListar.add(menuListarLogico);
		menuPropriedades.add(menuPropriedadesTamanho);
		menuPropriedades.add(menuPropriedadesQuantidade);
		menuPropriedades.add(menuPropriedadesEsvaziar);
		menuSair.add(menuSairSair);

		menubar.add(menuIncluir);
		menubar.add(menuExcluir);
		menubar.add(menuAtualizar);
		menubar.add(menuConsultar);
		menubar.add(menuListar);
		menubar.add(menuPropriedades);
		menubar.add(menuSair);

		this.setJMenuBar(menubar);

		this.setTitle("Gerenciador de Servidores Públicos");
		getContentPane().add(jPPainel);

	}

	// Cada método abaixo trata de um tipo de janela específico para cada necessidade do programa
	
	public void janelaCRUD(int opcao, boolean matricula, boolean nome, String titulo) {
	
		limparPainel();
		jLTitulo = new JLabel();
		jLTitulo.setBounds(100, 10, 200, 21);

		getContentPane().setLayout(null);

		jLMatricula = new JLabel();
		jTMatricula = new JTextField();

		jLMatricula.setBounds(10, 30, 250, 21);
		jTMatricula.setBounds(10, 50, 250, 21);

		jLNome = new JLabel();
		jTNome = new JTextField();

		jLNome.setBounds(10, 70, 250, 21);
		jTNome.setBounds(10, 90, 250, 21);
		jLNome.setText("Nome");

		jLSetor = new JLabel();
		jTSetor = new JTextField();
		jLSalario = new JLabel();
		jTSalario = new JTextField();
		jLClasse = new JLabel();
		jTClasse = new JTextField();
		jBOk = new JButton();
		jBLimpar = new JButton();
		jBVoltar = new JButton();

		jLSetor.setBounds(10, 110, 250, 21);
		jTSetor.setBounds(10, 130, 250, 21);
		jLSalario.setBounds(10, 150, 250, 21);
		jTSalario.setBounds(10, 170, 250, 21);
		jLClasse.setBounds(10, 190, 250, 21);
		jTClasse.setBounds(10, 210, 250, 21);
		jBOk.setBounds(300, 40, 110, 30);
		jBLimpar.setBounds(300, 80, 110, 30);
		jBVoltar.setBounds(300, 120, 110, 30);
		jLSetor.setText("Setor");
		jLSalario.setText("Salário");
		jLClasse.setText("Classe");

		jLMatricula.setText("Matrícula");

		if (matricula == true) {

			jPPainel.add(jLMatricula, null);
			jPPainel.add(jTMatricula, null);

		}

		if (nome == true) {

			jPPainel.add(jLNome, null);
			jPPainel.add(jTNome, null);

		}

		if (opcao == 1) {

			jPPainel.add(jLSetor, null);
			jPPainel.add(jTSetor, null);
			jPPainel.add(jLSalario, null);
			jPPainel.add(jTSalario, null);
			jPPainel.add(jLClasse, null);
			jPPainel.add(jTClasse, null);

		}

		jLTitulo.setText(titulo);

		jPPainel.add(jLTitulo, null);

		jBOk.setText("OK");
		jBLimpar.setText("Limpar");
		jBVoltar.setText("Voltar");

		jPPainel.add(jBOk, null);
		jPPainel.add(jBLimpar, null);
		jPPainel.add(jBVoltar, null);

		getContentPane().repaint();

	}

	public void janelaPropriedades(String texto1, String texto2, String titulo) {

		limparPainel();
		jLTitulo = new JLabel();
		jLTitulo.setBounds(100, 10, 200, 21);

		jLGenerico = new JLabel();
		jTGenerico = new JTextField();

		jLGenerico.setBounds(10, 30, 250, 21);
		jTGenerico.setBounds(10, 50, 250, 21);
		jTGenerico.setEditable(false);

		jLGenerico.setText(texto1);
		jTGenerico.setText(texto2);

		jPPainel.add(jLGenerico, null);
		jPPainel.add(jTGenerico, null);

		jLTitulo.setText(titulo);

		jPPainel.add(jLTitulo, null);

		jBOk = new JButton();
		jBLimpar = new JButton();
		jBVoltar = new JButton();

		jBOk.setBounds(300, 40, 110, 30);
		jBLimpar.setBounds(300, 80, 110, 30);
		jBVoltar.setBounds(300, 120, 110, 30);

		jBOk.setText("OK");
		jBLimpar.setText("Limpar");
		jBVoltar.setText("Voltar");

		jPPainel.add(jBOk, null);
		jPPainel.add(jBLimpar, null);
		jPPainel.add(jBVoltar, null);

		jBOk.setEnabled(false);
		jBLimpar.setEnabled(false);
		getContentPane().repaint();

	}

	public void janelaListar(boolean fisico, int linhas, String conteudo, String titulo) {

		limparPainel();
		jLTitulo = new JLabel();
		JTextArea textArea = new JTextArea(linhas, 30);

		textArea.setText(conteudo);

		JPanel jPPainel2 = new JPanel();
		jPPainel2.add(textArea);
		textArea.setEditable(false);
		JScrollPane scrPane = new JScrollPane(jPPainel2);

		jPPainel2.setSize(290, 400);
		scrPane.setSize(290, 380);
		scrPane.setLocation(0, 40);

		jLTitulo.setBounds(100, 10, 200, 21);

		jLTitulo.setText(titulo);

		jPPainel.add(jLTitulo, null);
		jPPainel.add(scrPane);

		jBOk = new JButton();
		jBLimpar = new JButton();
		jBVoltar = new JButton();

		jBOk.setText("OK");
		jBLimpar.setText("Limpar");
		jBVoltar.setText("Voltar");

		jBOk.setBounds(300, 40, 110, 30);
		jBLimpar.setBounds(300, 80, 110, 30);
		jBVoltar.setBounds(300, 120, 110, 30);

		jPPainel.add(jBOk, null);
		jPPainel.add(jBLimpar, null);
		jPPainel.add(jBVoltar, null);

		jBOk.setEnabled(false);
		jBLimpar.setEnabled(false);
		getContentPane().repaint();

	}

	public void limparPainel() {
		jPPainel.removeAll();
	}

}