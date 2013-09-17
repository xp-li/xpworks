package com.xpworks.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xpworks.domain.Person;

public class OtherPersonService {
	
	private DBOpenHelper dbOpenHelper;
	
	public OtherPersonService(Context context ) {
		this.dbOpenHelper = new DBOpenHelper(context);
	}
	/**
	 * 添加记录
	 * @param person
	 */
	public void addPerson(Person person){
		
		SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();//有数据库缓存特性
		//容易出错
		//String sql = "insert into person (name,phone) values ('"+person.getName()+"','"+person.getPhone()+"')";
		String sql = "insert into person (name,phone) values (?,?)";
		sqLiteDatabase.execSQL(sql, new Object[]{person.getName(),person.getPhone()});
		
	}
	
	/**
	 * 删除记录
	 * @param personid
	 */
	public void deletePerson(Integer personid){
		SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "delete from person where personid =?";
		sqLiteDatabase.execSQL(sql, new Object[]{personid});
		
	}
	
	/**
	 * 更新记录
	 * @param person
	 */
	public void updatePerson(Person person){
		SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();	
		String sql = "update person set name =?,phone=? where personid =?";
		sqLiteDatabase.execSQL(sql, new Object[]{person.getName(),person.getPhone(),person.getId()});
	
	}
	
	/**
	 * 查找记录
	 * @param personid
	 */
	public Person find(Integer personid){
		SQLiteDatabase sqLiteDatabase = dbOpenHelper.getReadableDatabase();
		Cursor cursor = sqLiteDatabase.rawQuery("select * from person where personid=?",
				new String[] {personid.toString()});
		
		if(cursor.moveToFirst()){
			int id = cursor.getInt(cursor.getColumnIndex("personid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			Person person =new Person(id, name, phone);
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
		String sql ="select * from person order by personid asc limit ?,? ";
		Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[] {String.valueOf(setoff),String.valueOf(maxResult)});
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("personid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			Person person =new Person(id, name, phone);
			persons.add(person);
		}
		cursor.close();
		return persons;
	}
	
	/**
	 * 获取记录总条数
	 * @return
	 */
	public long getCount(){
		SQLiteDatabase sqLiteDatabase = dbOpenHelper.getReadableDatabase();
		Cursor cursor = sqLiteDatabase.rawQuery("select count(*) from person", null);
		cursor.moveToFirst();
		long count = cursor.getLong(0);
		cursor.close();
		return count;
	}

}
