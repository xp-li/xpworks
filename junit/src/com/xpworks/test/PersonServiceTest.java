package com.xpworks.test;

import com.xpworks.service.PersonService;

import junit.framework.Assert;
import android.test.AndroidTestCase;

public class PersonServiceTest extends AndroidTestCase {
	
	public void testSave() throws Exception{
		PersonService service = new PersonService();
		service.save(null);
	}
	
	public void testAdd() throws Exception{
		PersonService service = new PersonService();
		int actual = service.add(1,2);
		Assert.assertEquals(8, actual);
	}

}
