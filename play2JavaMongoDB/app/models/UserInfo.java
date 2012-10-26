package models;

import java.util.Date;

public class UserInfo {
	private String    	nick;			//昵称
	private int 	  	gener;			//性别
	private Date    	brithday;   	//生日
	private String    	head;			//头像
	private int 		app;
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getGener() {
		return gener;
	}
	public void setGener(int gener) {
		this.gener = gener;
	}
	public Date getBrithday() {
		return brithday;
	}
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public int getApp() {
		return app;
	}
	public void setApp(int app) {
		this.app = app;
	}
	public UserInfo(String nick, int gener, Date brithday, String head) {
		super();
		this.nick = nick;
		this.gener = gener;
		this.brithday = brithday;
		this.head = head;
	}
	public UserInfo() {
		super();
	}
}
