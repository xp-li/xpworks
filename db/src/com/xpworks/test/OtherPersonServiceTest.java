package com.xpworks.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.xpworks.domain.Person;
import com.xpworks.service.DBOpenHelper;
import com.xpworks.service.OtherPersonService;

public class OtherPersonServiceTest extends AndroidTestCase {
	
	private static final String TAG = "PersonServiceTest";
	
	public void testCreatDB() throws Exception {
		DBOpenHelper dbOpenHelper = new DBOpenHelper(getContext());
		dbOpenHelper.getWritableDatabase();
	} 
	
	public void testSave() throws Exception {
		OtherPersonService personService = new OtherPersonService(this.getContext()) ;
		Person person = new Person("liming","1232344545",100);
		personService.addPerson(person);
				
	}
	
	public void testDelete() throws Exception {
		OtherPersonService personService = new OtherPersonService(this.getContext()) ;
		personService.deletePerson(22);
		
	}
	
	public void testUpdate() throws Exception {
		OtherPersonService personService = new OtherPersonService(this.getContext()) ;
		Person person = personService.find(21);
		person.setName("aaaa");
		person.setPhone("1860000006");
		personService.updatePerson(person);
		
	}

	public void testFind() throws Exception {
		OtherPersonService personService = new OtherPersonService(this.getContext()) ;
		Person person = personService.find(21);
		Log.i(TAG, person.toString());
	
	}
	
	public void testScrollData() throws Exception {
		OtherPersonService personService = new OtherPersonService(this.getContext()) ;
		List<Person> result = personService.getScrollData(0, 50);
		for(Person person:result){
			Log.i(TAG, person.toString());
		}
		
		
	}
	public void testCount() throws Exception {
		OtherPersonService personService = new OtherPersonService(this.getContext()) ;
		String number= String.valueOf(personService.getCount());
		Log.i(TAG, number);
	}

}
