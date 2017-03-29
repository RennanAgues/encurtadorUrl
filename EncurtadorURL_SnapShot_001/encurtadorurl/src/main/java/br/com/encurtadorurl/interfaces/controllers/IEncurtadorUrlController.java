package br.com.encurtadorurl.interfaces.controllers;

import org.springframework.web.bind.annotation.PathVariable;

public interface IEncurtadorUrlController {

	public	String getUrlEncurtadaByAlias(@PathVariable String alias);
	
	public	String getUrlEncurtadaByUrl(@PathVariable String url);
	
	public	String encurtadUrl(String url, String alias);
	
}
