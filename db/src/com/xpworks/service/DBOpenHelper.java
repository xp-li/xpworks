package com.xpworks.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	
	public DBOpenHelper(Context context) {
		super(context, "xpworks.db", null, 2);//包下的database文件夹中
		 
	}

	/**
	 * 数据库第一次被调用的时候调用
	 * 创建数据库、生成数据库表
	 */	
	public void onCreate(SQLiteDatabase db) {
		String sql ="CREATE TABLE person (personid integer primary key autoincrement, name varchar(20))";
		db.execSQL(sql);
		
	}

	/**
	 * 数据库版本号变更的时候创建的，当软件要升级需要修改数据库时使用
	 */
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String sql="ALTER TABLE person ADD phone VARCHAR(12) NULL ";
		db.execSQL(sql);
	}

}
