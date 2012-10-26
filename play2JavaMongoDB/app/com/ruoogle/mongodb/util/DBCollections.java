package com.ruoogle.mongodb.util;

public enum DBCollections {
	User("tb_user"),		//用户表
	Device("tb_device");	//设备表
	private String coll_name;
	public String getColl_name() {
		return coll_name;
	}
	public void setColl_name(String coll_name) {
		this.coll_name = coll_name;
	}
	private DBCollections(String coll_name) {
		this.coll_name = coll_name;
	}
}
