package exception;

import javax.servlet.ServletException;

public class InvalidUserAccesDenied extends ServletException{
	public InvalidUserAccesDenied(String message){
		super(message);
	}
}
