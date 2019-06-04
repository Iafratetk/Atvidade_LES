package exception;

import javax.servlet.ServletException;
import javax.swing.JOptionPane;

public class InvalidUserException extends ServletException {
	public InvalidUserException(String message) {
		super(message);
	}

}
