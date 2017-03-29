package br.com.encurtadorurl.utils.constantes;

import java.util.HashMap;
import java.util.Random;

public class ShortenUrl {
	private HashMap<String, String> keyMap; 
	private HashMap<String, String> valueMap;
				
	public static final int LENGTH_SHORTEN_URL = 5;	
	
	private String domain; 
							
	private char myChars[]; 
							
	private Random myRand; 
	private int keyLength; 

	
	public ShortenUrl(String domainUrl) {
		keyMap = new HashMap<String, String>();
		valueMap = new HashMap<String, String>();
		myRand = new Random();
		keyLength = 8;
		myChars = new char[62];
		for (int i = 0; i < 62; i++) {
			int j = 0;
			if (i < 10) {
				j = i + 48;
			} else if (i > 9 && i <= 35) {
				j = i + 55;
			} else {
				j = i + 61;
			}
			myChars[i] = (char) j;
		}
		domain = domainUrl;
	}

	public ShortenUrl(int length, String newDomain) {
		this(newDomain);
		
		this.keyLength = length;
		
		if (!newDomain.isEmpty()) {
			//Caso queira realizar a limpeza de HTTP/HTTPS
			//newDomain = sanitizeURL(newDomain);
			domain = newDomain;
		}
	}

	public String shortenURL(String longURL) throws Exception
	{
		String shortURL = "";
		
		try
		{
			longURL = Util.sanitizeURL(longURL);
			if (valueMap.containsKey(longURL)) {
				shortURL = domain + "/" + valueMap.get(longURL);
			} else {
				shortURL = domain + "/" + getKey(longURL);
			}
		}
		catch(Exception ex)
		{
			throw new Exception(ex.getMessage(),ex);
		}
		
		return shortURL;
	}

	public String expandURL(String shortURL) {
		String longURL = "";
		String key = shortURL.substring(domain.length() + 1);
		longURL = keyMap.get(key);
		return longURL;
	}	

	private String getKey(String longURL) {
		String key;
		key = generateKey();
		keyMap.put(key, longURL);
		valueMap.put(longURL, key);
		return key;
	}

	private String generateKey() {
		String key = "";
		boolean flag = true;
		while (flag) {
			key = "";
			for (int i = 0; i <= keyLength; i++) {
				key += myChars[myRand.nextInt(62)];
			}
			
			if (!keyMap.containsKey(key)) {
				flag = false;
			}
		}
		return key;
	}
}