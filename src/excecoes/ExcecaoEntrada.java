package excecoes;

public class ExcecaoEntrada extends RuntimeException {
	
	// Classe para exceção padrão de entrada de dados do Servidor
	
	private static final long serialVersionUID = 1L;
	
	public ExcecaoEntrada (String msg) {
		
		super(msg);
		
	}
}
