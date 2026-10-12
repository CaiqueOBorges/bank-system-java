package exception;

public class ContaDuplicadaException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ContaDuplicadaException(String mensagem) {
		super(mensagem);
	}

}
