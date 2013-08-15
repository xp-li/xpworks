package com.xpworks.file;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xpworks.service.FileService;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button=(Button)this.findViewById(R.id.button);
		button.setOnClickListener(new buttonOnclick());
	}
	
	public final class buttonOnclick implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			TextView filenameText=(TextView) findViewById(R.id.filename);
			TextView filecontentText=(TextView) findViewById(R.id.filcontent);
			String filename = filenameText.getText().toString();
			String filecontent = filecontentText.getText().toString();
			FileService fileService =new FileService(getApplicationContext());
			try{
				fileService.save(filename, filecontent);
				Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_LONG).show();
			}catch(Exception  e){ 
				Toast.makeText(getApplicationContext(), R.string.filed, Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
