package com.xpworks.phone;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	
	private EditText phoneNumberText; 
		
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		phoneNumberText =(EditText)findViewById(R.id.phone_number);
		Button callButton = (Button)findViewById(R.id.call);
		callButton.setOnClickListener(new callButtonOnClick());
	}
	
	private final class callButtonOnClick implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			String phoneNumber =phoneNumberText.getText().toString();
			Intent intent =new Intent();
			intent.setAction("android.intent.cation.CALL");
			intent.addCategory("android.intent.category.DEFAULT");
			intent.setData(Uri.parse("tel:"+phoneNumber));
			startActivity(intent);//方法内部会自动添加类别
			
		}
		
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
