package com.example.ccy.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	Button add;

	long BackTime = 0;

	Op op = new Op();
	int no;
	private MyDatabaseHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView listView = (ListView) findViewById(R.id.list_view);

		op.create(this);
		//op.clear();
		NoteAdapter adapter = new NoteAdapter(MainActivity.this, R.layout.note_item, op.getList());
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				NoteContent noteContent = op.getList().get(position);
				//Toast.makeText(MainActivity.this, noteContent.getNo()+"  "+noteContent.getMome(), Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MainActivity.this, ScientActivity.class);
				intent.putExtra("no",noteContent.getNo());
				startActivity(intent);
			}
		});

		add = (Button) findViewById(R.id.add);
		add.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		//display_history.setMovementMethod(ScrollingMovementMethod.getInstance());//支持滑动
		switch(v.getId()) {
			case R.id.add:
				Intent intent = new Intent(MainActivity.this, ScientActivity.class);
				intent.putExtra("no",32767);
				startActivity(intent);
				finish();
				break;
			/*case R.id.delete:
				op.delete(no);
				break;*/
			default:
				break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if((System.currentTimeMillis() - BackTime > 2000)){
				Toast.makeText(this, "再按一次返回桌面", Toast.LENGTH_SHORT).show();
				BackTime = System.currentTimeMillis();
			} else {
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				startActivity(intent);
			}
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
}
