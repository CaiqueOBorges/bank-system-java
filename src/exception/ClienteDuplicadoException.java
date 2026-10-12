package exception;

public class ClienteDuplicadoException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public ClienteDuplicadoException(String mensagem) {
		super(mensagem);
	}

}
