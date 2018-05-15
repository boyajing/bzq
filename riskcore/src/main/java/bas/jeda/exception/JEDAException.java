/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.exception;

public class JEDAException extends RuntimeException {
         
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public JEDAException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public JEDAException(String message, Throwable cause) {
            
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public JEDAException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public JEDAException(Throwable cause) {
		super(cause);
	}
        
}