package br.com.encurtadorurl.exceptions;

public class UrlNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5670030274611382615L;
	
	public UrlNotFoundException (){
		super(); 
				
	}
	
	public UrlNotFoundException (String message){
		super(message); 
				
	}

}
