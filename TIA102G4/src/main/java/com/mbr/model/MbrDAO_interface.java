package com.mbr.model;

import java.util.*;

public interface MbrDAO_interface {
	      public void insert(MbrVO mbrVO);
	      public void update(MbrVO mbrVO);
	      public void delete(Integer memberId);
	      public MbrVO findByPrimaryKey(Integer memberId);
	      public List<MbrVO> getAll();
		
		

}
