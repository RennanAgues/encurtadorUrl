package br.com.encurtadorurl.manager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.encurtadorurl.DTO.EncurtadorUrlDTO;
import br.com.encurtadorurl.entity.EncurtadorUrl;
import br.com.encurtadorurl.exceptions.UrlNotFoundException;
import br.com.encurtadorurl.interfaces.DAO.IEncurtadorUrlDAO;
import br.com.encurtadorurl.interfaces.manager.IEncurtadorUrlManager;
import br.com.encurtadorurl.utils.constantes.Const;
import br.com.encurtadorurl.utils.constantes.ShortenUrl;
import br.com.encurtadorurl.utils.constantes.Util;

@Component
public class EncurtadorUrlManager implements IEncurtadorUrlManager {
	
	@Autowired
	private IEncurtadorUrlDAO encurtadorUrlDAO;
	
	/**
	 * 
	 * @param url
	 * @param alias
	 * @return
	 * @throws Exception 
	 */
	@Override
	public EncurtadorUrlDTO encurtaUrl(String url, String alias) throws Exception
	{
		EncurtadorUrlDTO returnEncurtadorUrl = new EncurtadorUrlDTO(); 
    	boolean existItem = false;
    	boolean validUrl = false;
		
		try
		{
			EncurtadorUrlDTO encurtadorUrl_Url = existUrl(url);
			EncurtadorUrlDTO encurtadorUrl_Alias = existAlias(alias);
			
			existItem = encurtadorUrl_Url != null || encurtadorUrl_Alias != null;
			validUrl = checkURL(url,true);
			
			if(existItem)
			{
				EncurtadorUrlDTO encurtadorUrlInDatabase = encurtadorUrl_Url != null ? encurtadorUrl_Url : encurtadorUrl_Alias;
				
				encurtadorUrlInDatabase.setDescriptionReturn(Const.EXIST_ITEM);
				returnEncurtadorUrl = (EncurtadorUrlDTO) Util.deepClone(encurtadorUrlInDatabase);
			}
			else if (!validUrl)
			{
				returnEncurtadorUrl = new EncurtadorUrlDTO();
				
				returnEncurtadorUrl.setDescriptionReturn(Const.URL_IS_NOT_VALID);
				returnEncurtadorUrl.setCodeReturn(Const.CODE_ERROR_SHORTNER_URL);
			}
			else
			{
				EncurtadorUrl encurtadorUrl = shortnerUrl(url, alias);
				returnEncurtadorUrl = new EncurtadorUrlDTO(encurtadorUrl);		
			}			
	    }
	   	catch(Exception ex)
	   	{
	   		throw new Exception(ex.getMessage(), ex);
	   	}

    	return returnEncurtadorUrl;
	}

	
	/**
	 * 
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public EncurtadorUrlDTO getUrlEncurtadaByUrl(String url) throws Exception, UrlNotFoundException
	{
		EncurtadorUrlDTO returnUrlEncurtada = null; 
		
		try
		{
			List<EncurtadorUrl> listaUrlsEncurtadas = encurtadorUrlDAO.findByUrlAllIgnoringCase(url);
			
			if(listaUrlsEncurtadas.size() == 0){
				throw new UrlNotFoundException(Const.URL_IS_NOT_VALID);
			}
			
			returnUrlEncurtada = new EncurtadorUrlDTO(listaUrlsEncurtadas.get(0));
	    }
		catch(UrlNotFoundException ex)
	   	{
	   		throw new UrlNotFoundException(ex.getMessage());
	   	}
	   	catch(Exception ex)
	   	{
	   		throw new Exception(ex.getMessage(), ex);
	   	}

    	return returnUrlEncurtada;
	}

	/**
	 * 
	 * @param alias
	 * @return
	 * @throws Exception 
	 */	
	public EncurtadorUrlDTO getUrlEncurtadaByAlias(String alias) throws Exception, UrlNotFoundException
	{
		EncurtadorUrlDTO returnUrlEncurtada = new EncurtadorUrlDTO(); 
		
		try
		{
			List<EncurtadorUrl> listaUrlsEncurtadas = encurtadorUrlDAO.findByAliasAllIgnoringCase(alias);
			
			if(listaUrlsEncurtadas.size() == 0){
				throw new UrlNotFoundException(Const.ALIAS_IS_NOT_VALID);
			}
			
			returnUrlEncurtada = new EncurtadorUrlDTO(listaUrlsEncurtadas.get(0));
	    }
		catch(UrlNotFoundException ex)
	   	{
	   		throw new UrlNotFoundException(ex.getMessage());
	   	}
	   	catch(Exception ex)
	   	{
	   		throw new Exception(ex.getMessage(), ex);
	   	}

    	return returnUrlEncurtada;
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	private EncurtadorUrlDTO existUrl(String url) throws Exception, UrlNotFoundException
	{
		EncurtadorUrlDTO returnExistUrl = null; 
		List<EncurtadorUrl> listEncurtadorUrl = new ArrayList<EncurtadorUrl>();
    	
		try
		{
			listEncurtadorUrl =  encurtadorUrlDAO.findByUrlAllIgnoringCase(url);
			returnExistUrl = listEncurtadorUrl.size() > 0 ? new EncurtadorUrlDTO(listEncurtadorUrl.get(0)) : null;
	    }
	   	catch(Exception ex)
	   	{
	   		throw new Exception(ex.getMessage(), ex);
	   	}

    	return returnExistUrl;		
	}
	
	/**
	 * 
	 * @param alias
	 * @return
	 * @throws Exception
	 */
	private EncurtadorUrlDTO existAlias(String alias) throws Exception
	{
		EncurtadorUrlDTO returnExistAlias = null; 
		List<EncurtadorUrl> listEncurtadorAlias = new ArrayList<EncurtadorUrl>();
    	
		try
		{
			listEncurtadorAlias =  encurtadorUrlDAO.findByAliasAllIgnoringCase(alias);
			returnExistAlias = listEncurtadorAlias.size() > 0 ? new EncurtadorUrlDTO(listEncurtadorAlias.get(0)) : null;
	    }
	   	catch(Exception ex)
	   	{
	   		throw new Exception(ex.getMessage(), ex);
	   	}

    	return returnExistAlias;		
	}
	
	/**
	 * 
	 * @param url
	 * @param alias
	 * @return
	 * @throws Exception
	 */
	private EncurtadorUrl shortnerUrl(String url, String alias) throws Exception
	{
		EncurtadorUrl returnEncurtadorUrl = new EncurtadorUrl();
		ShortenUrl shortenUrl = null;
		String urlEncurtada = null;
		
		try
		{
			shortenUrl = new ShortenUrl(ShortenUrl.LENGTH_SHORTEN_URL, Const.NAME_DOMAIN_BEMOBI);
			urlEncurtada = shortenUrl.shortenURL(url);
			
			returnEncurtadorUrl.setUrl(url);
			returnEncurtadorUrl.setUrlEncurtadaWithAlias(String.format(Const.NAME_DOMAIN_BEMOBI.concat("/").concat(alias)));
			returnEncurtadorUrl.setUrlEncurtada(urlEncurtada);			
			returnEncurtadorUrl.setAlias(alias);
			returnEncurtadorUrl.setAtivo(true);
			returnEncurtadorUrl.setDataCriacao(new Date());
			returnEncurtadorUrl.setDescriptionReturn(Const.SUCCESS_SHORTNER_URL);
			returnEncurtadorUrl.setCodeReturn(Const.CODE_SUCCESS_SHORTNER_URL);
			
			returnEncurtadorUrl = encurtadorUrlDAO.save(returnEncurtadorUrl);
	    }
	   	catch(Exception ex)
	   	{
	   		throw new Exception(ex.getMessage(), ex);
	   	}

    	return returnEncurtadorUrl;		
	}	
	
	/**
	 * 
	 * @param strUrl
	 * @param firstCheck
	 * @return
	 * @throws Exception
	 */
	public boolean checkURL(String strUrl, boolean firstCheck) throws Exception {
	    boolean returnCheckUrl = false;
		
	    strUrl = "https://" + Util.sanitizeURL(strUrl);
	    
	    if(firstCheck){
	    	strUrl = "http://" + Util.sanitizeURL(strUrl);	
	    }
	    
		try {
	        URL url = new URL(strUrl);
	        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
	        urlConn.connect();

	        returnCheckUrl = HttpURLConnection.HTTP_OK == urlConn.getResponseCode();
	        
	        if(!returnCheckUrl && firstCheck){
	        	return checkURL(strUrl,false);
	        }
	    } catch (IOException e) {
	        //Url is not valid
	    }
		
		return returnCheckUrl;
	}
}
