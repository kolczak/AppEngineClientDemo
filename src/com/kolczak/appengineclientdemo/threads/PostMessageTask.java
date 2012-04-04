package com.kolczak.appengineclientdemo.threads;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;

import android.os.AsyncTask;

import com.kolczak.appengineclientdemo.PostMessageListener;

public class PostMessageTask extends AsyncTask<String, Integer, HttpResponse> {

	private final static String URL = "http://192.168.1.11:8080/messageserver";
	private PostMessageListener listener;
	
	public PostMessageTask(PostMessageListener listener) {
		this.listener = listener;
	}
	
	@Override
	protected HttpResponse doInBackground(String... params) {
		if (params.length == 0)
			return null;
		
		HttpPost httpMethod = new HttpPost(URL);
        httpMethod.addHeader("Accept", "text/html");
        httpMethod.addHeader("Content-Type", "application/xml");
        
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("author", params[0]));
        nameValuePairs.add(new BasicNameValuePair("content", params[1]));
        try {
			httpMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
        DefaultHttpClient client = new DefaultHttpClient();

        HttpResponse response = null;
        try {
            response = client.execute(httpMethod);
        } catch (IOException e) {
            e.printStackTrace();
        }
		return response;
	}

	@Override
    protected void onPostExecute( HttpResponse result) {

		listener.onMessagesReceived(result);

    }
	
}
