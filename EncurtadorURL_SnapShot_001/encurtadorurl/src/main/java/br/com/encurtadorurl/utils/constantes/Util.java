package br.com.encurtadorurl.utils.constantes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Util {
	/**
	 * 
	 * @param object
	 * @return
	 */
	public static <T>Object deepClone(T object) {
	   try {
	     ByteArrayOutputStream baos = new ByteArrayOutputStream();
	     ObjectOutputStream oos = new ObjectOutputStream(baos);
	     oos.writeObject(object);
	     ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
	     ObjectInputStream ois = new ObjectInputStream(bais);
	     return ois.readObject();
	   }
	   catch (Exception e) {
	     e.printStackTrace();
	     return null;
	   }
	 }
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String sanitizeURL(String url) throws Exception
	{
		try
		{
			if (url.substring(0, 7).toLowerCase().equals("http://"))
			{
				url = url.substring(7);
			}
			else if (url.substring(0, 8).toLowerCase().equals("https://"))
			{
				url = url.substring(8);
			}
			else if (url.charAt(url.length() - 1) == '/')
			{
				url = url.substring(0, url.length() - 1);
			}
		}
		catch(Exception ex)
		{
			throw new Exception(ex.getMessage(),ex);
		}
		
		return url;
	}
}
