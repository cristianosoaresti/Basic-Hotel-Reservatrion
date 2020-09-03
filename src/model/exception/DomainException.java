package model.exception;

public class DomainException extends Exception {
	private static final long serialVersionUID = 1L;
	
	//Creating my own exception
	public DomainException(String msg) {
		super(msg);
	}

}
