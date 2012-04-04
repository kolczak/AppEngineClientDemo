package com.kolczak.appengineclientdemo;

public class Config {

	private static Config instance = new Config();
	
	private Config() {}
	
	public Config getInstance() {
		return instance;
	}
}
