package br.com.encurtadorurl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.encurtadorurl.DTO.EncurtadorUrlDTO;
import br.com.encurtadorurl.interfaces.manager.IEncurtadorUrlManager;
import br.com.encurtadorurl.manager.EncurtadorUrlManager;
import br.com.encurtadorurl.utils.constantes.Const;
import junit.framework.TestCase;

/**
 * Unit test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AppTest  extends TestCase
{
	@Autowired
	private IEncurtadorUrlManager encurtadorUrlManager = new EncurtadorUrlManager();
	
	@Test
	public void validUrlAndAlias() {
		try {
			EncurtadorUrlDTO returnEncurtadorUrl = encurtadorUrlManager.encurtaUrl("www.globo.com","globo");
			assertEquals(Const.CODE_SUCCESS_SHORTNER_URL, returnEncurtadorUrl.getCodeReturn());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void invalidUrl() {
		try {
			EncurtadorUrlDTO returnEncurtadorUrl = encurtadorUrlManager.encurtaUrl("www.sssso.com","sssso");
			assertEquals(Const.CODE_ERROR_SHORTNER_URL, returnEncurtadorUrl.getCodeReturn());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void validExistUrlOrAlias() {
		try {
			encurtadorUrlManager.encurtaUrl("http://www.bemobi.com.br/","bemobi");
			EncurtadorUrlDTO returnEncurtadorUrl = encurtadorUrlManager.encurtaUrl("http://www.bemobi.com.br/","bemobi");
			
			assertEquals(Const.EXIST_ITEM, returnEncurtadorUrl.getDescriptionReturn());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
