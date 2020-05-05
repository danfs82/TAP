package entidades;

/**
 * Classe base de servidor.
 */
public class Servidor {

	private int matricula;
	private String nome;
	private String setor;
	private double salario;
	private int classe;

	public Servidor() {
		this(0, "", "", 0.0, 0);
	}

	public Servidor(int matricula, String nome, String setor, double salario, int classe) {
		setMatricula(matricula);
		setNome(nome);
		setSetor(setor);
		setSalario(salario);
		setClasse(classe);
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public void setClasse(int classe) {
		this.classe = classe;
	}

	public int getMatricula() {
		return matricula;
	}

	public String getNome() {
		return nome;
	}

	public String getSetor() {
		return setor;
	}

	public double getSalario() {
		return salario;
	}

	public int getClasse() {
		return classe;
	}

	@Override
	public String toString() {
		return ("Matrícula: " + getMatricula() + "\nNome: " + getNome() + "\nSetor: " + getSetor() + "\nSalário: "
				+ getSalario() + "\nClasse : " + getClasse());
	}
}
