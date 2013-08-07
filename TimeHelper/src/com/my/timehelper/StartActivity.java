package com.my.timehelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_start);
    	
		/*���´�������������ܣ�splashScreen��
		  * android��ʵ�ַǳ��򵥣�ʹ��Handler�����postDelayed�����Ϳ���ʵ�֡�
		  * ����������ﴫ��һ��Runnable�����һ���ӳٵ�ʱ�䡣 ��һ��������Runnable��������������ӳٺ���Ҫִ�еĲ�����
		  * �ӳٵ�ʱ���ɵڶ�������ָ������λ�Ǻ��롣
		 */
		new Handler().postDelayed(new Runnable() {
			public void run() {
				Intent intent = new Intent(StartActivity.this,MainActivity.class);  //����������ui��ת����ui
				startActivity(intent);
				StartActivity.this.finish();    // ����������������
		    }
		}, 1000);    //��λ�Ǻ���
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }
    */
}
