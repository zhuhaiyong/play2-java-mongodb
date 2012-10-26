package com.ruoogle.comm.enums;

public enum AppType {
	iPhone("10000000");
	private String 	app;
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	private AppType(String app) {
		this.app = app;
	}
}
