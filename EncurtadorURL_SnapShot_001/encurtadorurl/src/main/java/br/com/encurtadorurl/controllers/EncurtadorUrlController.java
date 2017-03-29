package br.com.encurtadorurl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.encurtadorurl.DTO.EncurtadorUrlDTO;
import br.com.encurtadorurl.exceptions.UrlNotFoundException;
import br.com.encurtadorurl.interfaces.controllers.IEncurtadorUrlController;
import br.com.encurtadorurl.interfaces.manager.IEncurtadorUrlManager;
import br.com.encurtadorurl.manager.EncurtadorUrlManager;
import br.com.encurtadorurl.utils.constantes.Const;

@RestController
public class EncurtadorUrlController extends BaseController implements IEncurtadorUrlController {
	
	@Autowired
	private IEncurtadorUrlManager encurtadorUrlManager = new EncurtadorUrlManager();

	/**
	 * 
	 * @param url
	 * @param alias
	 * @return String
	 */
	@Override
    @RequestMapping(value="/encurtaUrl/{url}/{alias}",method=RequestMethod.GET)
    @ResponseBody
	public String encurtadUrl(@PathVariable String url, @PathVariable String alias)
	{
		String returnData = "";
		
		EncurtadorUrlDTO returnEncurtadorUrl = new EncurtadorUrlDTO(); 
		
		GsonBuilder gsonBuilder = new GsonBuilder();  
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);  
		Gson gson = gsonBuilder.create();
	    	
		try
		{
			returnEncurtadorUrl = encurtadorUrlManager.encurtaUrl(url,alias);
			
			returnData = gson.toJson(returnEncurtadorUrl);
	    }
		catch(UrlNotFoundException ex)
	   	{
	   		returnData =  ex.getMessage();
	   	}
	   	catch(Exception ex)
	   	{
	   		returnData =  Const.REQUEST_MESSAGE_ERROR;
	   	}

    	return returnData;
	}
	
	/**
	 * 
	 * @param alias
	 * @return String
	 */
	@Override
    @RequestMapping(value="/getUrlEncurtadaByAlias/{alias}",method=RequestMethod.GET)
    @ResponseBody
	public String getUrlEncurtadaByAlias(@PathVariable("alias") String alias)
	{
		String returnData = "";
		
		EncurtadorUrlDTO returnEncurtadorUrl = new EncurtadorUrlDTO(); 
		
		GsonBuilder gsonBuilder = new GsonBuilder();  
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);  
		Gson gson = gsonBuilder.create();
	    	
		try
		{
			returnEncurtadorUrl = encurtadorUrlManager.getUrlEncurtadaByAlias(alias);
			
			returnData = gson.toJson(returnEncurtadorUrl);
	    }
	   	catch(UrlNotFoundException ex)
	   	{
	   		returnData =  ex.getMessage();
	   	}
		catch(Exception ex)
	   	{
	   		returnData =  Const.REQUEST_MESSAGE_ERROR;
	   	}

    	return returnData;
	}
	
	/**
	 * 
	 * @param url
	 * @return String
	 */
	@Override
    @RequestMapping(value="/getUrlEncurtadaByUrl/{url:.+}",method=RequestMethod.GET)
    @ResponseBody
	public String getUrlEncurtadaByUrl(@PathVariable("url") String url)
	{
		String returnData = "";
		
		EncurtadorUrlDTO returnEncurtadorUrl = new EncurtadorUrlDTO(); 
		
		GsonBuilder gsonBuilder = new GsonBuilder();  
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);  
		Gson gson = gsonBuilder.create();
	    	
		try
		{
			returnEncurtadorUrl = encurtadorUrlManager.getUrlEncurtadaByUrl(url);
			
			returnData = gson.toJson(returnEncurtadorUrl);
	    }
		catch(UrlNotFoundException ex)
	   	{
	   		returnData =  ex.getMessage();
	   	}
	   	catch(Exception ex)
	   	{
	   		returnData =  Const.REQUEST_MESSAGE_ERROR;
	   	}

    	return returnData;
	}
}