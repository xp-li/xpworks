package com.example.sms;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private EditText numberEditText;
	private EditText contentEditText;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		numberEditText=(EditText)this.findViewById(R.id.number);
		contentEditText=(EditText)this.findViewById(R.id.content);
		button=(Button)this.findViewById(R.id.button);
		button.setOnClickListener(new buttonOnclickListener());
	}
	
	private final class buttonOnclickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			String number=numberEditText.getText().toString();
			String content=contentEditText.getText().toString();
			SmsManager smsManager = SmsManager.getDefault();
			ArrayList<String> texts = smsManager.divideMessage(content);
			for(String text:texts){
				smsManager.sendTextMessage(number, null, text, null, null);
			}			
			//context:上下文信息，类似于Web中的application或者Session
			Toast.makeText(MainActivity.this, R.string.success, Toast.LENGTH_LONG).show();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
