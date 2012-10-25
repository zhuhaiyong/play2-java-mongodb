package com.ruoogle.mongodb.util;

public enum DBCollections {
	TempUser("tb_tempuser"),//临时用户表
	SeqenceID("tb_seqenceid");//需要自增长的ID的表的ID记录表
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
