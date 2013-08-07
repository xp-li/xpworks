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
    	
		/*如下代码完成闪屏功能：splashScreen。
		  * android的实现非常简单，使用Handler对象的postDelayed方法就可以实现。
		  * 在这个方法里传递一个Runnable对象和一个延迟的时间。 第一个参数是Runnable对象，里面包含了延迟后需要执行的操作。
		  * 延迟的时间由第二个参数指定，单位是毫秒。
		 */
		new Handler().postDelayed(new Runnable() {
			public void run() {
				Intent intent = new Intent(StartActivity.this,MainActivity.class);  //从启动动画ui跳转到主ui
				startActivity(intent);
				StartActivity.this.finish();    // 结束启动动画界面
		    }
		}, 1000);    //单位是毫秒
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }
    */
}
