package com.xpworks.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	
	public DBOpenHelper(Context context) {
		super(context, "xpworks.db", null, 2);//���µ�database�ļ�����
		 
	}

	/**
	 * ���ݿ��һ�α����õ�ʱ�����
	 * �������ݿ⡢�������ݿ��
	 */	
	public void onCreate(SQLiteDatabase db) {
		String sql ="CREATE TABLE person (personid integer primary key autoincrement, name varchar(20))";
		db.execSQL(sql);
		
	}

	/**
	 * ���ݿ�汾�ű����ʱ�򴴽��ģ������Ҫ������Ҫ�޸����ݿ�ʱʹ��
	 */
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String sql="ALTER TABLE person ADD phone VARCHAR(12) NULL ";
		db.execSQL(sql);
	}

}
