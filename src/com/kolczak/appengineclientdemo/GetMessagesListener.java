package com.kolczak.appengineclientdemo;

import java.util.ArrayList;
import java.util.Map;

public interface GetMessagesListener {
	void onMessagesReceived (ArrayList<Map<String, String>> list);
}
