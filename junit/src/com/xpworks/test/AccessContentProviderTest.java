package com.xpworks.test;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

public class AccessContentProviderTest extends AndroidTestCase {
	
	private static final String TAG ="AccessContentProviderTest";
	
	public void testInsert() throws Exception{
		Uri uri = Uri.parse("content://com.xpworks.db.providers.personprovider/person");
		ContentResolver resolver = this.getContext().getContentResolver();
		ContentValues values = new ContentValues();
		values.put("name", "baobao");
		values.put("phone", "1888888888888888");
		values.put("amount", "888000000000");
		resolver.insert(uri, values);
	} 
	
	public void testDeletePersonTest() throws Exception{
		Uri uri = Uri.parse("content://com.xpworks.db.providers.personprovider/person/9");
		ContentResolver resolver = this.getContext().getContentResolver();
		resolver.delete(uri, null, null);
	} 
	
	public void testUpdatePersonTest() throws Exception{
		Uri uri = Uri.parse("content://com.xpworks.db.providers.personprovider/person/19");
		ContentResolver resolver = this.getContext().getContentResolver();
		ContentValues values = new ContentValues();
		values.put("name", "zhangxiaoxiao");		
		resolver.update(uri, values, null, null);
	} 
	public void testQueryPersonTest() throws Exception{
		Uri uri = Uri.parse("content://com.xpworks.db.providers.personprovider/person");
		ContentResolver resolver = this.getContext().getContentResolver();
		Cursor cursor = resolver.query(uri, null, null, null, "personid asc");
		while (cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndex("name"));
			Log.i(TAG, name);
		}
	} 
}
