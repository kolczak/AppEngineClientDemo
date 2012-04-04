package com.kolczak.appengineclientdemo;

import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpResponse;

public interface PostMessageListener {
	void onMessagesReceived (HttpResponse response);
}
