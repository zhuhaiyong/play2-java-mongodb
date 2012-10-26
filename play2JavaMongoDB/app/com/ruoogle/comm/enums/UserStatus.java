package com.ruoogle.comm.enums;

public enum UserStatus {
	Temp_User(0),
	Real_User(1),
	Allocation_User(2);
	private int status;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	private UserStatus(int status) {
		this.status = status;
	}
	
}
