package models;

import org.codehaus.jackson.node.ObjectNode;

public class ResultModel {
	private int  code;
	private String  name;
	private ObjectNode 	data;
	private ObjectNode    notif;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ObjectNode getData() {
		return data;
	}
	public void setData(ObjectNode data) {
		this.data = data;
	}
	public ObjectNode getNotif() {
		return notif;
	}
	public void setNotif(ObjectNode notif) {
		this.notif = notif;
	}
	public ResultModel(int code, String name, ObjectNode data, ObjectNode notif) {
		super();
		this.code = code;
		this.name = name;
		this.data = data;
		this.notif = notif;
	}
	public ResultModel() {
		super();
	}
	
}
