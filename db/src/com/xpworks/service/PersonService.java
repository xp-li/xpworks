package com.xpworks.service;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xpworks.domain.Person;

public class PersonService {
	
	private DBOpenHelper dbOpenHelper;
	
	public PersonService(Context context ) {
		this.dbOpenHelper = new DBOpenHelper(context);
	}
	/**
	 * 添加记录
	 * @param person
	 */
	public void addPerson(Person person){
		
		SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", person.getName());
		values.put("phone", person.getPhone());
		values.put("amount", person.getAmount());
		sqLiteDatabase.insert("person", null, values);		
	}
	
	/**
	 * 删除记录
	 * @param personid
	 */
	public void deletePerson(Integer personid){
		SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();
		sqLiteDatabase.delete("person", "personid=?", new String[] {personid.toString()});
	}
	
	/**
	 * 更新记录
	 * @param person
	 */
	public void updatePerson(Person person){
		SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();	
		ContentValues values = new ContentValues();
		values.put("name", person.getName());
		values.put("phone", person.getPhone());
		values.put("amount", person.getAmount());
		sqLiteDatabase.update("person", values, "personid=?", new String[] {String.valueOf(person.getId())});
	}
	
	/**
	 * 查找记录
	 * @param personid
	 */
	public Person find(Integer personid){
		SQLiteDatabase sqLiteDatabase = dbOpenHelper.getReadableDatabase();
		
		Cursor cursor = sqLiteDatabase.query("person", new String[] {"personid","name","phone","amount"}, "personid=?", new String[] {personid.toString()}, null, null, null);
			
		if(cursor.moveToFirst()){
			int id = cursor.getInt(cursor.getColumnIndex("personid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			int amount =cursor.getInt(cursor.getColumnIndex("amount"));
			Person person =new Person(id, name, phone,amount);
			return person;
		}
		cursor.close();
		return null;
	}
	
	/**
	 * 分页查找记录
	 * @param setoff 跳过多少条
	 * @param maxResult 获取多少条 
	 * @return
	 */
	public List<Person> getScrollData(int setoff,int maxResult){
		List<Person> persons = new ArrayList<Person>();
		
		SQLiteDatabase sqLiteDatabase = dbOpenHelper.getReadableDatabase();		
		Cursor cursor = sqLiteDatabase.query("person", null, null, null, null, null, "personid asc", setoff+","+maxResult);
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("personid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			int amount =cursor.getInt(cursor.getColumnIndex("amount"));
			Person person =new Person(id, name, phone,amount);
			persons.add(person);
		}
		cursor.close();
		return persons;
	}
	
	public Cursor getScrollDataCursor(int setoff,int maxResult){
		
		SQLiteDatabase sqLiteDatabase = dbOpenHelper.getReadableDatabase();		
		Cursor cursor = sqLiteDatabase.rawQuery("select personid as _id,name,phone,amount from person order by personid asc limit ?,?", 
				new String[]{String.valueOf(setoff),String.valueOf(maxResult)});
		return cursor;
	}
	
	/**
	 * 获取记录总条数
	 * @return
	 */
	public long getCount(){
		SQLiteDatabase sqLiteDatabase = dbOpenHelper.getReadableDatabase();
		Cursor cursor = sqLiteDatabase.query("person", new String[]{"count(*)"}, null, null, null, null, null);
		cursor.moveToFirst();
		long count = cursor.getLong(0);
		cursor.close();
		return count;
	}
	
	public void payment(){
		SQLiteDatabase sqLiteDatabase = dbOpenHelper.getReadableDatabase();
		sqLiteDatabase.beginTransaction();
		try{
			sqLiteDatabase.execSQL("update person set amount=amount-10 where personid=2");
			sqLiteDatabase.execSQL("update person set amount=amount+10 where personid=3");
			sqLiteDatabase.setTransactionSuccessful();//设置事务的标志为True
		}finally{
			sqLiteDatabase.endTransaction();//提交或回滚，事务的提交或回滚是由事务的标志确定的，默认是False
			//如果是True则提交，False则回滚；
		}		
	}

}
