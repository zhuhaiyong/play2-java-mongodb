package models.user;

/**
 * 临时用户，是指没有补充信息或者绑定第三方帐号的用户
 * @author zhy
 */
public class TempUser {
	private int id;				//临时用户ID
	private String deviceCode;  //设备code
	private int lastRealID;			//最后一次登录的 真实用户ID
}
