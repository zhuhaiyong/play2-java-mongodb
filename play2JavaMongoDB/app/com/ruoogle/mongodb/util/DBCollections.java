package com.ruoogle.mongodb.util;

public enum DBCollections {
	TempUser("tb_tempuser"),//临时用户表
	SeqenceID("tb_seqenceid");//需要自增长的ID的表的ID记录表
	private String collection;
	private DBCollections(String collection) {
		this.collection = collection;
	}
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
	}
}
