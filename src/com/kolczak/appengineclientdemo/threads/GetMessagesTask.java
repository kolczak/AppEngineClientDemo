package com.kolczak.appengineclientdemo.threads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import com.kolczak.appengineclientdemo.GetMessagesListener;

import android.os.AsyncTask;

public class GetMessagesTask extends
		AsyncTask<String, Integer, ArrayList<Map<String, String>>> {

	private final static String URL = "http://192.168.1.11:8080/messages.jsp";
	private GetMessagesListener listener;

	public GetMessagesTask(GetMessagesListener listener) {
		this.listener = listener;
	}
	
	@Override
	protected ArrayList<Map<String, String>> doInBackground(String... arg0) {
		HttpClient httpclient = new DefaultHttpClient();
		ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();
		ResponseHandler<String> responseHandler = new BasicResponseHandler();

		String response = null;
		try {
			response = httpclient.execute(new HttpGet(URL), responseHandler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		response = response.trim().replace("\n", "");
		if (response != null) {

			JSONArray json = null;

			try {

				json = new JSONArray(response);

				if (json != null) {
					for (int i = 0; i < json.length(); i++) {

						JSONObject obj = json.getJSONObject(i);
						String author = obj.getString("author"); 
						String content = obj.getString("content");
						result.add(putData(content, author));
					}
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

		return result;

	}
	
	private HashMap<String, String> putData(String content, String author) {
		HashMap<String, String> item = new HashMap<String, String>();
		item.put("content", content);
		item.put("author", author);
		return item;
	}
	
	@Override
    protected void onPostExecute( ArrayList<Map<String, String>> result) {

		listener.onMessagesReceived(result);

    }

}
