package dados;

import java.io.IOException;
import java.io.RandomAccessFile;

import entidades.Servidor;

public class RegistroServidor extends Servidor {

	/**
	 * Construtor sem par�metros.
	 */
	public RegistroServidor() {
		super();
	}

	/**
	 * Construtor com par�metros
	 *
	 * @param matricula c�digo de um matricula
	 * @param nome      Nome de um servidor
	 * @param setor     Setor de um servidor
	 * @param salario   Sal�rio de um servidor
	 * @param classe    Classe do servidor
	 */
	public RegistroServidor(int matricula, String nome, String setor, double salario, int classe) {
		super(matricula, nome, setor, salario, classe);
	}

	/**
	 * Realiza a leitura dos dados do arquivo especificado.
	 *
	 * Preenche os atributos de servidor utilizado o arquivo especificado.
	 *
	 * @param arquivo Refer�ncia ao arquivo com os dados do servidor.
	 * @throws IOException
	 */
	public void leitura(RandomAccessFile arquivo) throws IOException {
		setMatricula(arquivo.readInt());
		// Retira os espa�os da leitura da String com m�todo trim
		setNome(montaPalavra(arquivo, 34).trim());
		// Retira os espa�os da leitura da String com o m�todo trim
		setSetor(montaPalavra(arquivo, 31).trim());
		setSalario(arquivo.readDouble());
		setClasse(arquivo.readInt());
	}

	/**
	 * Lê do arquivo caracter a caracter e monta uma string.
	 *
	 * @param arquivo Refer�ncia ao arquivo com os caracteres a serem lidos.
	 * @return
	 * @throws IOException
	 */
	private String montaPalavra(RandomAccessFile arquivo, int tamanho) throws IOException {
		// Vetor de char da palavra a ser montada
		char palavra[] = new char[tamanho];
		char temp;
		// Recupera do arquivo 15 caracteres
		for (int i = 0; i < palavra.length; i++) {
			temp = arquivo.readChar();
			palavra[i] = temp;
		}
		return new String(palavra).replace('\0', ' ');
	}

	public void escrita(RandomAccessFile arquivo) throws IOException {
		arquivo.writeInt(getMatricula());
		// Escreve 15 caracteres do nome no arquivo
		escrevePalavra(arquivo, getNome(), 34);
		// Escreve 15 caracteres do endere�o no arquivo
		escrevePalavra(arquivo, getSetor(), 31);
		arquivo.writeDouble(getSalario());
		arquivo.writeInt(getClasse());
	}

	// Escreve a palavra no arquivo atravs de um Buffer de String
	private void escrevePalavra(RandomAccessFile arquivo, String palavra, int tamanho) throws IOException {
		StringBuffer buf = null;
		if (palavra != null) {
			buf = new StringBuffer(palavra);
		} else {
			buf = new StringBuffer(tamanho);
		}

		// Tamanho da String a ser gravada
		buf.setLength(tamanho);
		arquivo.writeChars(buf.toString());
	}

	/**
	 * Retorna o getTamanhoRegistro do registro de servidor.
	 *
	 * @return Um inteiro com o getTamanhoRegistro do registro do servidor.
	 */
	public int getTamanhoRegistro() {
		// 4 matricula + 30 nome + 15 setor + 8 salario + 4 classe
		// matricula int = 4 bytes
		// nome String = 34 caracteres = 1 caracter ocupada 2 bytes = 68 bytes
		// setor String = 31 caracteres = 1 caracter ocupada 2 bytes = 62 bytes
		// salario double = 8 bytes
		// classe int = 4 bytes
		// Total 150 bytes
		return (4 + (2 * 34) + (2 * 31) + 8 + 4);
	}
}
