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
	 * ��Ӽ�¼
	 * @param person
	 */
	public void addPerson(Person person){
		
		SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();//�����ݿ⻺������
		//���׳���
		//String sql = "insert into person (name,phone) values ('"+person.getName()+"','"+person.getPhone()+"')";
		String sql = "insert into person (name,phone) values (?,?)";
		sqLiteDatabase.execSQL(sql, new Object[]{person.getName(),person.getPhone()});
		
	}
	
	/**
	 * ɾ����¼
	 * @param personid
	 */
	public void deletePerson(Integer personid){
		SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "delete from person where personid =?";
		sqLiteDatabase.execSQL(sql, new Object[]{personid});
		
	}
	
	/**
	 * ���¼�¼
	 * @param person
	 */
	public void updatePerson(Person person){
		SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();	
		String sql = "update person set name =?,phone=? where personid =?";
		sqLiteDatabase.execSQL(sql, new Object[]{person.getName(),person.getPhone(),person.getId()});
	
	}
	
	/**
	 * ���Ҽ�¼
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
	 * ��ҳ���Ҽ�¼
	 * @param setoff ����������
	 * @param maxResult ��ȡ������ 
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
	 * ��ȡ��¼������
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
