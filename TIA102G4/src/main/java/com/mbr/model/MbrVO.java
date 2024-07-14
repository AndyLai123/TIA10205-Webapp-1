package com.mbr.model;
import java.sql.Date;

public class MbrVO implements java.io.Serializable {
    private Integer  memberId;
    private Date    regisdate;
    private String  name;
    private String  account;
    private String  password;
    private String  email;
    private Integer  gender;  
    private String  mobileno;
    private byte[] sticker;
    
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Date getRegisdate() {
		return regisdate;
	}
	public void setRegisdate(Date regisdate) {
		this.regisdate = regisdate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public byte[] getSticker() {
		return sticker;
	}
	public void setSticker(byte[] sticker) {
		this.sticker = sticker;
	} 
    

}  

 