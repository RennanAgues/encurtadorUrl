package br.com.encurtadorurl.interfaces.manager;

import org.springframework.stereotype.Component;

import br.com.encurtadorurl.DTO.EncurtadorUrlDTO;

@Component
public interface IEncurtadorUrlManager {
	
	public	EncurtadorUrlDTO encurtaUrl(String url, String alias) throws Exception;
	
	public	EncurtadorUrlDTO getUrlEncurtadaByUrl(String url) throws Exception;

	public	EncurtadorUrlDTO getUrlEncurtadaByAlias(String alias) throws Exception;
}
