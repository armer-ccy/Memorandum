package com.example.ccy.noteapp;

public class NoteContent {
	private int no;
	private String content;
	private String date;
	private int mome;

	NoteContent() {
	}

	NoteContent(int no, String content, String date, int mome) {
		this.no = no;
		this.content = content;
		this.date = date;
		this.mome = mome;
	}

	public void set(int no, String content, String date, int mome) {
		this.no = no;
		this.content = content;
		this.date = date;
		this.mome = mome;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getMome() {
		return mome;
	}

	public void setMome(int mome) {
		this.mome = mome;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
