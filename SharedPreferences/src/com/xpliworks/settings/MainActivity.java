package com.xpliworks.settings;

import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.xpliworks.service.PreferencesService;

public class MainActivity extends Activity {
	
	private EditText nameText;
	private EditText ageText;
	private PreferencesService service;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		nameText = (EditText) this.findViewById(R.id.name);
		ageText = (EditText) this.findViewById(R.id.age);
		service = new PreferencesService(this);
		
		Map<String,String> params = service.getPreferences();
		nameText.setText(params.get("name"));
		ageText.setText(params.get("age"));
		
	}
	
	public void save(View view){
		String name = nameText.getText().toString();
		String age = ageText.getText().toString(); 		
		service.save(name,Integer.parseInt(age));
		Toast.makeText(this.getApplicationContext(), R.string.success, Toast.LENGTH_LONG).show();
		//在Activity中获取SharedPreference对象的方法API
		//SharedPreferences preferences = this.getPreferences(MODE_PRIVATE);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
