package com.xpworks.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.xpworks.domain.Person;
import com.xpworks.service.DBOpenHelper;
import com.xpworks.service.PersonService;

public class PersonServiceTest extends AndroidTestCase {
	
	private static final String TAG = "PersonServiceTest";
	
	public void testCreatDB() throws Exception {
		DBOpenHelper dbOpenHelper = new DBOpenHelper(getContext());
		dbOpenHelper.getWritableDatabase();
	} 
	
	public void testSave() throws Exception {
		PersonService personService = new PersonService(this.getContext()) ;
		for(int i= 0;i<20 ;i++){
			Person person = new Person("zhangsan"+i,"1232344545"+i,100);
		    personService.addPerson(person);
		}		
	}
	
	public void testDelete() throws Exception {
		PersonService personService = new PersonService(this.getContext()) ;
		personService.deletePerson(1);
		
	}
	
	public void testUpdate() throws Exception {
		PersonService personService = new PersonService(this.getContext()) ;
		Person person = personService.find(1);
		person.setName("lisi");
		person.setPhone("18600002456");
		personService.updatePerson(person);
		
	}

	public void testFind() throws Exception {
		PersonService personService = new PersonService(this.getContext()) ;
		Person person = personService.find(5);
		Log.i(TAG, person.toString());
	
	}
	
	public void testPayment() throws Exception {
		PersonService personService = new PersonService(this.getContext()) ;
		personService.payment();
	
	}
	
	public void testScrollData() throws Exception {
		PersonService personService = new PersonService(this.getContext()) ;
		List<Person> result = personService.getScrollData(0, 5);
		for(Person person:result){
			Log.i(TAG, person.toString());
		}
				
	}
	public void testCount() throws Exception {
		PersonService personService = new PersonService(this.getContext()) ;
		String number= String.valueOf(personService.getCount());
		Log.i(TAG, number);
	}
	
	public void testUpdateAmount() throws Exception {
		PersonService personService = new PersonService(this.getContext()) ;
		Person person2 = personService.find(2);
		Person person3 = personService.find(3);
		person2.setAmount(100);
		person3.setAmount(50);
		personService.updatePerson(person2);
		personService.updatePerson(person3);
		
	}

}
