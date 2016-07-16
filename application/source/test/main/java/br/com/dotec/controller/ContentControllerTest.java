package br.com.dotec.controller;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.util.test.JSR303MockValidator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.dotec.model.Content;
import br.com.dotec.persistence.dao.ContentDAO;

public class ContentControllerTest {
	private MockResult result;
	private JSR303MockValidator   validator;  
	private ContentsController  contentsController;
	
	
	private ContentDAO contentDAO;
	@Mock
	private Content content;
	
	public void setUp(){
			MockitoAnnotations.initMocks(this);
			result = new MockResult();
			validator = new JSR303MockValidator();
			contentsController = new ContentsController(contentDAO, result, validator);
	}
	
	@Test
	public void saveContent(){
					
	}
}
