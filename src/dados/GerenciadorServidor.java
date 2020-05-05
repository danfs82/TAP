package dados;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JOptionPane;

import excecoes.ExcecaoEntrada;

public class GerenciadorServidor {

	private String nomeArquivo;
	private RandomAccessFile arquivo;

	/**
	 * Construtor sem par�metro.
	 *
	 * Abre o arquivo com o nome default ao inicializar a classe.
	 */
	public GerenciadorServidor() {
		setNomeArquivo("SERVIDOR.DAT");
		abrirArquivo();
	}

	/**
	 * Construtor com par�metro.
	 *
	 * Abre o arquivo com o nome especificado ao inicializar a classe.
	 */
	public GerenciadorServidor(String nomeArquivo) {
		setNomeArquivo(nomeArquivo);
		abrirArquivo();
	}

	/**
	 * Destrutor da classe. Fecha o arquivo.
	 */
	public void finalize() {
		fecharArquivo();
	}

	// Gets e Sets
	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public RandomAccessFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(RandomAccessFile arquivo) {
		this.arquivo = arquivo;
	}

	/**
	 * Abre o arquivo com o nome especificado.
	 */
	public void abrirArquivo() {
		try {
			// Cria a refer�ncia externa ao objeto fileArquivo
			File fileArquivo = new File(getNomeArquivo());
			// Abre o arquivo para leitura e escrita
			arquivo = new RandomAccessFile(fileArquivo, "rw");
		} catch (IOException io) {
			System.out.println("Problemas ao manipular o arquivo: " + io);
		}
	}

	/**
	 * Fecha a refer�ncia ao arquivo.
	 */
	public void fecharArquivo() {
		try {
			// Fecha o arquivo
			arquivo.close();
		} catch (IOException io) {
			System.out.println("Problemas ao manipular o arquivo: " + io);
		}
	}

	/**
	 * Inclui um registro do arquivo.
	 *
	 * @param registro Registro de servidor a ser adicionado
	 * @return Retorna verdadeiro ou falso se conseguiu realizar a inclus�o.
	 */
	public boolean inserir(RegistroServidor registro) {
		try {
			// Posiciona o ponteiro de grava��o no final do arquivo
			arquivo.seek(arquivo.length());
			// Escreve o registro no arquivo
			registro.escrita(arquivo);
			return true;
		} catch (IOException io) {
			System.out.println("Problemas ao manipular o arquivo: " + io);
		}
		return false;
	}

	/**
	 * Realiza o retorno dos dados n�o exclu�dos do arquivo. Retorna os dados do
	 * arquivo onde o campo chave é diferente de -1.
	 *
	 * @return Uma String com os dados do arquivo.
	 */
	public String listarLogico() {
		String linha = "";
		// Instancia um registro para armazenar os dados lido do arquivo.
		RegistroServidor registro = new RegistroServidor();
		try {
			// Posiciona no in�cio do arquivo
			arquivo.seek(0);
			// Enquanto o ponteiro de leitura for menor que o tamanho do arquivo
			while (getArquivo().getFilePointer() < getArquivo().length()) {
				// Realiza a leitura de um registro do arquivo
				registro.leitura(arquivo);
				// Concatena somente valores chave diferente de -1
				if (registro.getMatricula() != -1) {
					// Concatena os dados do registro
					linha = linha + registro.toString() + "\n\n";
				}
			}
		} catch (EOFException eof) {
			System.out.println("Chegou ao final do arquivo: " + eof);
		} catch (IOException io) {
			System.out.println("Problemas ao manipular o arquivo: " + io);
		}
		return linha;
	}

	/**
	 * Realiza o retorno dos dados do arquivo. Retorna todos os dados do arquivo
	 * inclusive os exclu�dos.
	 *
	 * @return Uma String com os dados do arquivo.
	 */
	public String listarFisico() {
		// Vari�vel para concatenar os dados
		String linha = "";
		// Instancia um registro para armazenar os dados lido do arquivo.
		RegistroServidor registro = new RegistroServidor();
		try {
			arquivo.seek(0);
			// Enquanto o ponteiro de leitura for menor que o tamanho do arquivo
			while (getArquivo().getFilePointer() < getArquivo().length()) {
				// Realiza a leitura de um registro do arquivo
				registro.leitura(arquivo);
				// Concatena os dados do registro
				linha = linha + registro.toString() + "\n\n";
			}
		} catch (EOFException eof) {
			System.out.println("Chegou ao final do arquivo: " + eof);
		} catch (IOException io) {
			System.out.println("Problemas ao manipular o arquivo: " + io);
		}
		return linha;
	}

	/**
	 * Retorna a quantidade de registros.
	 *
	 * @return Um n�mero inteiro com a quantidade de registros.
	 */
	public int getQuantidadeRegistro() {
		int contador = 0;
		try {
			// Instancia um registro para armazenar os dados lido do arquivo.
			RegistroServidor registro = new RegistroServidor();
			// Posiciono no in�cio do arquivo.
			arquivo.seek(0);
			// Enquanto o ponteiro de leitura for menor que o tamanho do arquivo.
			while (getArquivo().getFilePointer() < getArquivo().length()) {
				// Realiza a leitura de um registro do arquivo
				registro.leitura(arquivo);
				// Incrementa o contador de registro.
				contador = contador + 1;
			}
		} catch (IOException io) {
			System.out.println("Problemas ao manipular o arquivo: " + io);
		}
		return contador;
	}

	/**
	 * Recupera as informa��es do arquivo.
	 *
	 * @return Uma string com os dados do arquivo.
	 */
	public String informacoesTamanho() {
		String informacoes = "";
		try {
			// Concatena as informa��es do arquivo
			informacoes = arquivo.length() + " kb ";
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro no Adicionar!");
		}
		return informacoes;
	}

	public String informacoesQuantidade() {
		String informacoes = "";
		// Concatena as informa��es do arquivo
		informacoes = Integer.toString(getQuantidadeRegistro());

		return informacoes;
	}

	/**
	 * Atualiza um registro no arquivo.
	 *
	 * @param chave    Chave do registro a ser atualizado.
	 * @param servidor Um servidor com os novos dados.
	 * @return Retorna verdadeiro ou falso se conseguiu atualizar o registro.
	 */
	public boolean atualizar(int chave, RegistroServidor servidor) {
		try {
			// Pega a posi��o do registro com a chave a ser alterada
			int posicao = posicao(chave);
			if (posicao != -1) {
				// Atribui o novo registro a ser salvo
				RegistroServidor registro = servidor;
				// Posiciona na posi��o do registro a ser alterado
				arquivo.seek(posicao * registro.getTamanhoRegistro());
				// Escreve o registro no arquivo
				registro.escrita(arquivo);
				return true;

			} else {
				return false;
			}
		} catch (EOFException eof) {
			System.out.println("Chegou ao final do arquivo: " + eof);
		} catch (IOException io) {
			System.out.println("Problemas ao manipular o arquivo: " + io);
		}
		return false;
	}

	/**
	 * Retorna a posi��o de um registro no arquivo.
	 *
	 * @param chave Valor a ser procurado.
	 *
	 * @return Um valor com a posi��o do registro que tem a chave.
	 */
	public int posicao(int chave) {
		int posicao = -1;
		// Instancia um registro para armazenar os dados lido do arquivo.
		RegistroServidor registro = new RegistroServidor();
		try {
			// Posiciona no in�cio do arquivo
			arquivo.seek(0);
			boolean achei = false;
			// Enquanto o ponteiro de leitura for menor que o tamanho do arquivo e n�o achei
			while ((getArquivo().getFilePointer() < getArquivo().length()) && (achei == false)) {
				// Realiza a leitura de um registro do arquivo
				registro.leitura(arquivo);
				// Vefifica se � igual a chave
				if (registro.getMatricula() == chave) {
					achei = true;
				}
				// Incrementa o contador de posi��es
				posicao = posicao + 1;
			}
			// Verifica se achou
			if (achei == true) {
				// Retorna a posi��o encontrada
				return posicao;
			} else {
				return -1;
			}
		} catch (EOFException eof) {
			System.out.println("Chegou ao final do arquivo: " + eof);
		} catch (IOException io) {
			System.out.println("Problemas ao manipular o arquivo: " + io);
		}
		return posicao;
	}

	/**
	 * Exclui logicamente um registro do arquivo.
	 *
	 * Atribui -1 para o campo chave do registro.
	 *
	 * @param chave Chave do registro a ser exclu�do.
	 * @return Verdadeiro ou falso se conseguiu excluir logicamente o registro.
	 */
	public boolean excluirMatricula(int chave) {
		int posicao = -1;
		// Instancia um registro para armazenar os dados lido do arquivo.
		RegistroServidor registro = new RegistroServidor();
		try {
			// Posiciona no inicio do arquivo
			arquivo.seek(0);
			boolean achei = false;
			// Enquanto o ponteiro de leitura for menor que o tamanho do arquivo e não
			// achei
			while ((getArquivo().getFilePointer() < getArquivo().length()) && (achei == false)) {
				registro.leitura(arquivo);
				if (registro.getMatricula() == chave) {
					achei = true;
				}
				posicao = posicao + 1;
			}
			if (achei == true) {
				registro.setMatricula(-1);
				arquivo.seek(posicao * registro.getTamanhoRegistro());
				registro.escrita(arquivo);
				return true;
			} else {
				return false;
			}
		} catch (EOFException eof) {
			System.out.println("Chegou ao final do arquivo: " + eof);
		} catch (IOException io) {
			System.out.println("Problemas ao manipular o arquivo: " + io);
		}
		return false;
	}

	public boolean excluirNome(String nome) {
		int posicao = -1;
		// Instancia um registro para armazenar os dados lido do arquivo.
		RegistroServidor registro = new RegistroServidor();
		try {
			// Posiciona no inicio do arquivo
			arquivo.seek(0);
			boolean achei = false;
			// Enquanto o ponteiro de leitura for menor que o tamanho do arquivo e n�o achei
			while ((getArquivo().getFilePointer() < getArquivo().length()) && (achei == false)) {
				registro.leitura(arquivo);
				if (registro.getMatricula() != -1) {

					if (nome.equalsIgnoreCase(registro.getNome())) {

						achei = true;
					}
				}
				posicao = posicao + 1;
			}
			if (achei == true) {
				registro.setMatricula(-1);
				arquivo.seek(posicao * registro.getTamanhoRegistro());
				registro.escrita(arquivo);
				return true;
			} else {
				return false;
			}
		} catch (EOFException eof) {
			System.out.println("Chegou ao final do arquivo: " + eof);
		} catch (IOException io) {
			System.out.println("Problemas ao manipular o arquivo: " + io);
		}
		return false;
	}

	/**
	 * Exclui fisicamente um registro do arquivo.
	 *
	 * @param chave Valor da chave do registro a ser exclu�do.
	 */
	public void excluirFisico(int chave) {
		// Instancia um registro para armazenar os dados lido do arquivo.
		RegistroServidor registro = new RegistroServidor();
		try {
			// Retorna o nome do arquivo at� o ponto
			String nomeArquivoTemp = getNomeArquivo().substring(0, getNomeArquivo().indexOf("."));
			// Adiciona a extens�o tmp ao nome do arquivo temporario
			nomeArquivoTemp = nomeArquivoTemp + ".TMP";
			// Cria a refer�ncia ao arquivo tempor�rio
			File fileArquivoTemp = new File(nomeArquivoTemp);
			// Abre o arquivo tempor�rio para leitura e escrita
			RandomAccessFile arquivoTemp = new RandomAccessFile(fileArquivoTemp, "rw");
			// Esvazia o arquivo tempor�rio
			arquivoTemp.setLength(0);
			try {
				// Posiciona o arquivos dos dados no inicio
				arquivo.seek(0);
				// Copia todos os registro do arquivo para um arquivo tempor�rio, menos o
				// registro a ser exclu�do
				// Enquanto o ponteiro de leitura for menor que o tamanho do arquivo
				while ((getArquivo().getFilePointer() < getArquivo().length())) {
					// Realiza a leitura do registro
					registro.leitura(arquivo);
					// Copia todos os registros menos a chave
					if (registro.getMatricula() != chave) {
						// Posiciona o arquivo tempor�rio no final
						arquivoTemp.seek(arquivoTemp.length());
						// Escreve o registro no arquivo tempor�rio
						registro.escrita(arquivoTemp);
					}
				}
			} catch (EOFException eof) {
				System.out.println("Chegou ao final do arquivo: " + eof);
			} catch (IOException io) {
				System.out.println("Problemas ao manipular o arquivo: " + io);
			}
			// Apaga o arquivo original
			arquivo.setLength(0);
			try {
				// Posiciona no in�cio do arquivo
				arquivoTemp.seek(0);
				// Enquanto o ponteiro de leitura for menor que o tamanho do arquivo
				while ((arquivoTemp.getFilePointer() < arquivoTemp.length())) {
					// Realiza a leitura de um registro do arquivo
					registro.leitura(arquivoTemp);
					// Posiciona no final do arquivo
					arquivo.seek(arquivo.length());
					// Escreve um registro no arquivo
					registro.escrita(arquivo);
				}
			} catch (EOFException eof) {
				System.out.println("Chegou ao final do arquivo: " + eof);
			} catch (IOException io) {
				System.out.println("Problemas ao manipular o arquivo: " + io);
			}
			// Apaga o temporario
			arquivoTemp.setLength(0);
			// Fecha o arquivo tempor�rio
			arquivoTemp.close();
			// Apaga o arquivo tempor�rio
			fileArquivoTemp.delete();
		} catch (EOFException eof) {
			System.out.println("Chegou ao final do arquivo: " + eof);
		} catch (IOException io) {
			System.out.println("Problemas ao manipular o arquivo: " + io);
		}
	}

	/**
	 * Pesquisa uma chave no arquivo retornando o registro.
	 *
	 * @param chave Valor chave a se pesquisado no arquivo.
	 * @return Retorna o registro encontrado no arquivo.
	 */
	public RegistroServidor pesquisarMatricula(int chave) {
		// Objeto de retorno do m�todo
		RegistroServidor retorno = null;
		// Registro a ser utilizado para ler o arquivo
		RegistroServidor registro = new RegistroServidor();
		try {
			// Posiciona no in�cio do arquivo
			arquivo.seek(0);
			// Realiza a leitura do primeiro registro do arquivo
			registro.leitura(arquivo);
			// Enquanto o ponteiro de leitura for menor que o tamanho do arquivo ou
			// diferente da chave
			while ((getArquivo().getFilePointer() < getArquivo().length()) && (registro.getMatricula() != chave)) {
				// Realiza a leitura de um registro do arquivo
				registro.leitura(arquivo);
			}
			// Verifica se � igual a chave
			if (registro.getMatricula() == chave) {
				// Guarda o registro encontrado para retornar
				retorno = registro;
			}
		} catch (EOFException eof) {
			System.out.println("Chegou ao final do arquivo: " + eof);
		} catch (IOException io) {
			System.out.println("Problemas ao manipular o arquivo: " + io);
		}
		return retorno;
	}

	public RegistroServidor pesquisarNome(String nome) {
		// Objeto de retorno do m�todo
		RegistroServidor retorno = null;
		// Registro a ser utilizado para ler o arquivo
		RegistroServidor registro = new RegistroServidor();
		try {
			// Posiciona no in�cio do arquivo
			arquivo.seek(0);
			// Realiza a leitura do primeiro registro do arquivo
//          registro.leitura(arquivo);
			boolean achei = false;
			// Enquanto o ponteiro de leitura for menor que o tamanho do arquivo ou
			// diferente da chave
			while ((getArquivo().getFilePointer() < getArquivo().length()) && (achei == false)) {
				// Realiza a leitura de um registro do arquivo
				registro.leitura(arquivo);

				if (registro.getMatricula() != -1) {

					if (nome.equalsIgnoreCase(registro.getNome())) {

						achei = true;
						// Guarda o registro encontrado para retornar
						retorno = registro;
					}
				}
			}
			// Verifica se � igual a chave

		} catch (EOFException eof) {
			System.out.println("Chegou ao final do arquivo: " + eof);
		} catch (IOException io) {
			System.out.println("Problemas ao manipular o arquivo: " + io);
		}
		return retorno;
	}

	/**
	 * Apaga os registro do arquivo.
	 *
	 * @return Se conseguiu esvaziar o arquivo.
	 */
	public boolean zeraArquivo() {
		try {
			// Seta o tamanho do arquivo em 0.
			arquivo.setLength(0);
			return true;
		} catch (IOException io) {
			System.out.println("Problemas ao manipular o arquivo: " + io);
			return false;
		}
	}
}
