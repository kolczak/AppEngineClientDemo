package com.kolczak.appengineclientdemo;

import com.kolczak.appengineclientdemo.threads.*;

import org.apache.http.HttpResponse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PostGetAppEngineExampleActivity extends Activity implements PostMessageListener {
    
	private Button submitBt;
	private Button showMessagesBt;
	private EditText authorEt;
	private EditText contentEt;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //assign views to variables
        submitBt = (Button)findViewById(R.id.m_bt_submit);
    	showMessagesBt = (Button)findViewById(R.id.m_bt_messages);
    	authorEt = (EditText)findViewById(R.id.m_et_author);
    	contentEt = (EditText) findViewById(R.id.m_et_content);
    	
		// set events
		submitBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if (authorEt.getText().toString().equals("")
						|| contentEt.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(), "Fill all text fields", Toast.LENGTH_LONG).show();
				} else {
					new PostMessageTask(PostGetAppEngineExampleActivity.this).execute(
							authorEt.getText().toString(), 
							contentEt.getText().toString());
				}
			}
		});
		
		showMessagesBt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(PostGetAppEngineExampleActivity.this, MessagesActivity.class));
			}
		});
    }

	@Override
	public void onMessagesReceived(HttpResponse response) {
		Toast.makeText(this, "Post message sent", Toast.LENGTH_LONG).show();
		authorEt.setText("");
		contentEt.setText("");
	}

}