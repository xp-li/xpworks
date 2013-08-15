package com.xpworks.test;

import android.test.AndroidTestCase;
import android.util.Log;

public class Logtest extends AndroidTestCase {
	
	private static final String TAG ="Logtest";
	
	public void testOutLog () throws Throwable{
		Log.v(TAG, "www.xpworks.com- ‘ ‘÷–Œƒ");
	}
	
	public void testOutLog2 () throws Throwable{
		System.out.println("www.out.com");
	}
	
	public void testOutLog3 () throws Throwable{
		System.err.println("www.err.com");
	}

}
