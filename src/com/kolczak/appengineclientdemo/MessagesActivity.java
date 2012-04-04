package com.kolczak.appengineclientdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.kolczak.appengineclientdemo.threads.GetMessagesTask;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MessagesActivity extends Activity implements GetMessagesListener {
	
	private ListView listView = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messages_activity);
		listView = (ListView) findViewById(R.id.msg_list);
		new GetMessagesTask(this).execute(null);
	}

	@Override
	public void onMessagesReceived(ArrayList<Map<String, String>> list) {
		String[] from = { "content", "author" };
		int[] to = { android.R.id.text1, android.R.id.text2 };

		SimpleAdapter adapter = new SimpleAdapter(this, list,
				android.R.layout.simple_list_item_2, from, to);
		listView.setAdapter(adapter);
		
	}
	
}
