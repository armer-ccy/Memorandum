package com.example.ccy.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class ScientActivity extends AppCompatActivity implements View.OnClickListener {

	EditText time;
	EditText note;

	Button back;
	Button delete;

	int no;
	//long Now=System.currentTimeMillis();
	//List expression = new ArrayList();
	Op op=new Op();
	String date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);

		op.init(ScientActivity.this);

		time = (EditText) findViewById(R.id.time);
		//time.clearFocus();//失去焦点，不弹出键盘
		time.setCursorVisible(false);
		note = (EditText) findViewById(R.id.note);

		back =(Button) findViewById(R.id.back);
		delete =(Button) findViewById(R.id.delete);
		back.setOnClickListener(this);
		delete.setOnClickListener(this);

		Intent intent=getIntent();
		no=intent.getIntExtra("no",1);

		Date timer=new Date();
		date=timer.toLocaleString();
		if(no==32767){
			note.setText("");
			time.setText(timer.toLocaleString());
		}else {
			note.setText(op.displayAll(no));
			time.setText(op.displayTime(no));
		}

	}

	@Override
	public void onClick(View v) {
		//display_history.setMovementMethod(ScrollingMovementMethod.getInstance());//支持滑动
		Intent intent = new Intent(ScientActivity.this,MainActivity.class);
		switch(v.getId()) {
			case R.id.back:
				op.back(no,note.getText().toString(),date);
				startActivity(intent);
				finish();
				break;
			case R.id.delete:
				op.delete(no);
				startActivity(intent);
				finish();
				break;
			default:
				break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			op.back(no,note.getText().toString(),date.toString());
			Intent intent = new Intent(ScientActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
			/*if ((System.currentTimeMillis() - time > 1000)) {
				Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
				time = System.currentTimeMillis();
			} else {
				//如果添加了actiyity的生命周期控制类，可需要进行操作
				//如果有后台服务下载，就不能退出了。可以设置状态判断处理。
				finish();
				System.exit(0);
			}*/
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}

	}
}
