package com.xpworks.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.xpworks.adapter.PersonAdapter;
import com.xpworks.domain.Person;
import com.xpworks.service.PersonService;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private ListView listview;
	private PersonService personService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		personService = new PersonService(this);
		listview=(ListView)this.findViewById(R.id.listView);
		listview.setOnItemClickListener(new ItemClickListener());
		show3();
		
	}
	private final class ItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
			ListView itemListView = (ListView)parent;
			Person person = (Person)itemListView.getItemAtPosition(position);
			Toast.makeText(getApplicationContext(), String.valueOf(person.getId()), Toast.LENGTH_LONG).show();
		}
		
	}
	
	
	/**
	 * ◊‘∂®“Â  ≈‰∆˜
	 */
	private void show3() {
		List<Person> persons = personService.getScrollData(0, 20);
		PersonAdapter adapter= new PersonAdapter(this, persons, R.layout.item);
		listview.setAdapter(adapter);
	}
	
	private void show2() {
		// TODO Auto-generated method stub
		Cursor cursor = personService.getScrollDataCursor(0, 20);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item, cursor,
				new String[] {"name","phone","amount"}, new int[]{R.id.name,R.id.phone,R.id.amount});
		listview.setAdapter(adapter);
	}

	private void show() {
		// TODO Auto-generated method stub
		List<Person> persons = personService.getScrollData(0, 20);
		List<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
		
		for(Person person:persons){
			HashMap<String,Object> item = new HashMap<String,Object>();
			item.put("name", person.getName());
			item.put("phone", person.getPhone());
			item.put("amount", person.getAmount());
			item.put("id", person.getId());
			data.add(item);
		}		
		
		SimpleAdapter adapter= new SimpleAdapter(this,data, R.layout.item, 
				new String[]{"name","phone","amount"}, new int[]{R.id.name,R.id.phone,R.id.amount});
		listview.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
