package com.example.ccy.noteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Op {
	private List<NoteContent> noteList = new ArrayList<>();
	int no;
	private MyDatabaseHelper dbHelper;

	Op() {}

	public void init(Context context) {
		dbHelper = new MyDatabaseHelper(context, "NoteBook.db", null, 1);
	}

	public void create(Context context) {
		dbHelper = new MyDatabaseHelper(context, "NoteBook.db", null, 1);
		dbHelper.getWritableDatabase();
	}

	public void back(int no, String content, String date) {
		if(!("".equals(content) || content == null)){
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			query();
			if(no == 32767){
				this.no = noteList.size() + 1;
				NoteContent noteContent = new NoteContent(this.no, content, date, 0);
				ContentValues value = new ContentValues();
				value.put("id", noteContent.getNo());
				value.put("content", noteContent.getContent());
				value.put("date", noteContent.getDate());
				value.put("mome", noteContent.getMome());
				db.insert("NoteBook", null, value);
			} else if(!displayAll(no).equals(content)){
				NoteContent noteContent = new NoteContent(no, content, date, 0);
				ContentValues value = new ContentValues();
				value.put("content", noteContent.getContent());
				value.put("date", noteContent.getDate());
				db.update("NoteBook", value, "id = ?", new String[]{noteContent.getNo() + ""});
			}
		}
	}

	public void delete(int no) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues value = new ContentValues();
		value.put("mome", 1);
		db.update("NoteBook", value, "id = ?", new String[]{no + ""});
	}

	public void query() {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		noteList.clear();
		Cursor cursor = db.query("NoteBook", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do {
				NoteContent noteContent = new NoteContent();
				noteContent.set(cursor.getInt(cursor.getColumnIndex("id")),
					cursor.getString(cursor.getColumnIndex("content")),
					cursor.getString(cursor.getColumnIndex("date")),
					cursor.getInt(cursor.getColumnIndex("mome")));
				noteList.add(noteContent);
			}
			while(cursor.moveToNext());
		}
		cursor.close();
	}

	public void query(int no) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		noteList.clear();
		Cursor cursor = db.query("NoteBook", null, "id=?", new String[]{no + ""}, null, null, null);
		int i = 0;
		if(cursor.moveToFirst()){
			do {
				NoteContent noteContent = new NoteContent();
				noteContent.set(cursor.getInt(cursor.getColumnIndex("id")),
					cursor.getString(cursor.getColumnIndex("content")),
					cursor.getString(cursor.getColumnIndex("date")),
					cursor.getInt(cursor.getColumnIndex("mome")));
				noteList.add(noteContent);
			}
			while(cursor.moveToNext());
		}
		cursor.close();
	}

	public String displayAll(int no) {
		query(no);
		return noteList.get(0).getContent();
	}

	public String display(String str) {
		if(str.length() > 11){
			return str.substring(0, 7) + "...";
		} else {
			return str;
		}
	}

	public String displayTime(int no) {
		query(no);
		return noteList.get(0).getDate();
	}

	public void add(int no, String content, String time) {
		NoteContent noteContent = new NoteContent(no, content, time, 0);
		noteList.add(noteContent);
	}

	public void clear() {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete("NoteBook", "id>?", new String[]{"-1"});
	}

	public List<NoteContent> getList() {
		query();
		List<NoteContent> list = new ArrayList<>();
		for(int i = 0; i < noteList.size(); i++){
			NoteContent noteContent = noteList.get(i);
			noteContent.setContent(display(noteContent.getContent()));
			if(noteContent.getMome() == 0) list.add(noteContent);
		}
		return list;
	}
}