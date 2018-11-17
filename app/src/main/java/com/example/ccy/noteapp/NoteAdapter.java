package com.example.ccy.noteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

//自定义适配器，继承自ArrayAdapter
public class NoteAdapter extends ArrayAdapter<NoteContent> {
	//resourceID指定ListView的布局方式
	private int resourceID;
	//重写NoteAdapter的构造器
	public NoteAdapter(Context context, int textViewResourceID , List<NoteContent> objects){
		super(context,textViewResourceID,objects);
		resourceID = textViewResourceID;
	}
	//自定义item资源的解析方式
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//获取当前NoteContent实例
		NoteContent note = getItem(position);
		View view;
		ViewHolder viewHolder;
		/*if(convertView == null){*/
			view = LayoutInflater.from(getContext()).inflate(resourceID,null);
			viewHolder = new ViewHolder();
			viewHolder.noteContent = (TextView)view.findViewById(R.id.note_Content);
			viewHolder.noteDate = (TextView)view.findViewById(R.id.note_Date);
			//将ViewHolder存储在View中
			view.setTag(viewHolder);
		/*}else {
			view = convertView;
			viewHolder = (ViewHolder)view.getTag();
		}*/
		//引入NoteContent对象的属性值
		viewHolder.noteContent.setText(note.getContent()+"");
		viewHolder.noteDate.setText(note.getDate()+"");
		return view;
	}

	class ViewHolder{
		TextView noteContent;
		TextView noteDate;
	}
}