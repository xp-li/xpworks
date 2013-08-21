package com.xpworks.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.xpworks.domain.Person;
import com.xpworks.service.PersonService;

public class PersonServiceTest extends AndroidTestCase {
	
	private static final String TAG = "PersonServiceTest";
	
	public void testGetPersons() throws Exception {
		
		InputStream xml = this.getClass().getClassLoader().getResourceAsStream("person.xml");
		List <Person> persons =  PersonService.getPersons(xml);
		
		for(Person person : persons){
			Log.i(TAG, person.toString());
		}
	}
	
	public void testSave() throws Exception {
		
		List <Person> persons =  new ArrayList<Person>();
		persons.add(new Person(1,"zhangsan",80 ));
		persons.add(new Person(2,"lili",20 ));
		persons.add(new Person(3,"xiaoxiao",8 ));
		
		File xmlFile = new File(getContext().getFilesDir(),"persons.xml");
		FileOutputStream out = new FileOutputStream(xmlFile);
		
		PersonService.save(persons, out);
	}

}
