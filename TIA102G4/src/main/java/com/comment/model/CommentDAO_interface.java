package com.comment.model;

import java.util.*;

public interface CommentDAO_interface {
	      public void insert(CommentVO commentVO);
	      public void update(CommentVO commentVO);
	      public void delete(Integer commentId);
	      public CommentVO findByPrimaryKey(Integer commentId);
	      public List<CommentVO> getAll();
	      //萬用複合查詢(傳入參數型態Map)(回傳 List)
 //         public List<CommentVO> getAll(Map<String, String[]> map);
	      
}
