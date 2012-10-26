package com.ruoogle.comm.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class UniqueIDUtil{
	public static long getUniqueID(){
		long uniqueID=0;
		Socket clientSocket =null;
		DataInputStream dis =null;
		DataOutputStream dos=null;
		try {
			 clientSocket = new Socket("10.18.121.202", 4444);// 向10.18.121.202的4444端口发出客户请求
			 dos = new DataOutputStream(clientSocket.getOutputStream());//输出流, 从Client出去, 到服务器端。
			 dis = new DataInputStream(clientSocket.getInputStream());//从服务器端进入Client的流对象。
			 
			 Integer i=1;
			 dos.write(i.byteValue());
			 byte[] id=new byte[8*1];
			 dis.read(id);
			 uniqueID=getLong(id,false);
		} catch (Exception e) {
			System.out.println("Error" + e); 
			e.printStackTrace();
		}finally{
			try {
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				dos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return uniqueID;
	}
	
	public final static long getLong(byte[] buf, boolean asc) {
	    if (buf == null) {
	      throw new IllegalArgumentException("byte array is null!");
	    }
	    if (buf.length > 8) {
	      throw new IllegalArgumentException("byte array size > 8 !");
	    }
	    long r = 0;
	    if (asc)
	      for (int i = buf.length - 1; i >= 0; i--) {
	        r <<= 8;
	        r |= (buf[i] & 0x00000000000000ff);
	      }
	    else
	      for (int i = 0; i < buf.length; i++) {
	        r <<= 8;
	        r |= (buf[i] & 0x00000000000000ff);
	      }
	    return r;
	  }
 
}
