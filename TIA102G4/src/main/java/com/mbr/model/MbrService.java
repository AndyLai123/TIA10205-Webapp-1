package com.mbr.model;

import java.sql.Date;
import java.util.List;

public class MbrService {

	private MbrDAO_interface dao;
	
	public MbrService() {
		dao = new MbrDAO();
	}
	
	public MbrVO addMbr(Date regisdate,String name,String account,String password,String email,Integer gender,String mobileno,byte[] sticker) {
		
		MbrVO mbrVO = new MbrVO();
		
		mbrVO.setRegisdate(regisdate);
		mbrVO.setName(name);
		mbrVO.setAccount(account);
		mbrVO.setPassword(password);
		mbrVO.setEmail(email);
		mbrVO.setGender(gender);
		mbrVO.setMobileno(mobileno);
		mbrVO.setSticker(sticker);
		dao.insert(mbrVO);
		
		return mbrVO;
	}
	public MbrVO updateMbr(Integer memberId,Date regisdate,String name,String account,String password,String email,Integer gender,String mobileno,byte[] sticker) {
	
		MbrVO mbrVO = new MbrVO();
		
		mbrVO.setMemberId(memberId);
		mbrVO.setRegisdate(regisdate);
		mbrVO.setName(name);
		mbrVO.setAccount(account);
		mbrVO.setPassword(password);
		mbrVO.setEmail(email);
		mbrVO.setGender(gender);
		mbrVO.setMobileno(mobileno);
		mbrVO.setSticker(sticker);
		dao.update(mbrVO);
		
		return mbrVO;
	}
	public void deleteMbr(Integer memberId) {
		dao.delete(memberId);
	}
	public MbrVO getOneMbr(Integer memberId) {
		return dao.findByPrimaryKey(memberId);
	}
	
	public List<MbrVO> getAll(){
		return dao.getAll();
	}	
}
