package com.xpworks.db;

import com.xpworks.service.DBOpenHelper;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PersonProvider extends ContentProvider {

	private DBOpenHelper dbOpenHelp;
	private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int PERSONS = 1;
	private static final int PERSON = 2;
	static{
		MATCHER.addURI("com.xpworks.db.providers.personprovider", "person", PERSONS);
		MATCHER.addURI("com.xpworks.db.providers.personprovider", "person/#", PERSON);
	}
	
	@Override
	public boolean onCreate() {
		dbOpenHelp = new DBOpenHelper(this.getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = dbOpenHelp.getReadableDatabase();
		switch (MATCHER.match(uri)) {
		case 1:
			Cursor c1 = db.query("person", projection, selection, selectionArgs, null, null, sortOrder);
			return c1;
		case 2:
			long rowid = ContentUris.parseId(uri);
			String where = "personid="+rowid;
			if (selection != null && !"".equals(selection.trim()) ) {
				where += " and "+selection;
			}
			Cursor c2 = db.query("person", projection, where, selectionArgs, null, null, sortOrder);
			return c2;	
		default:
			throw new IllegalArgumentException(" 这是一个非法的URI："+uri);
		}
	}

	@Override
	public String getType(Uri uri) {
		switch (MATCHER.match(uri)) {
		case 1:
			return "vnd.android.cursor.dir/person";
		case 2:
			return "vnd.android.cursor.itme/person";
		default:
			throw new IllegalArgumentException(" 这是一个非法的URI："+uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbOpenHelp.getWritableDatabase();
		switch (MATCHER.match(uri)) {
		case 1:
			long rowid = db.insert("person", "name", values);//主键值
			//content://com.xpworks.db.providers.personprovider/person/rowid
			//Uri insertUri = Uri.parse("content://com.xpworks.db.providers.personprovider/person/"+rowid);
			Uri insertUri = ContentUris.withAppendedId(uri, rowid);
			return insertUri;
		default:
			throw new IllegalArgumentException(" 这是一个非法的URI："+uri);
		}
		
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbOpenHelp.getWritableDatabase();
		int num = 0;
		
		switch (MATCHER.match(uri)) {
		case 1:
			num = db.delete("person",selection,selectionArgs);
			break;
		case 2:
			long rowid = ContentUris.parseId(uri);
			String where = "personid="+rowid;
			if (selection != null && !"".equals(selection.trim()) ) {
				where += " and "+selection;
			}
			num = db.delete("person",where,selectionArgs);
			break;
		default:
			throw new IllegalArgumentException(" 这是一个非法的URI："+uri);
		}
		return num;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		SQLiteDatabase db = dbOpenHelp.getWritableDatabase();
		int num = 0;		
		switch (MATCHER.match(uri)) {
		case 1:
			num = db.update("person", values, selection, selectionArgs);
			break;
		case 2:
			long rowid = ContentUris.parseId(uri);
			String where = "personid="+rowid;
			if (selection != null && !"".equals(selection.trim()) ) {
				where += " and "+selection;
			}
			num = db.update("person",values,where,selectionArgs);
			break;
		default:
			throw new IllegalArgumentException(" 这是一个非法的URI："+uri);
		}
		return num;
	}

}
