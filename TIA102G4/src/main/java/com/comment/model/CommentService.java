package com.comment.model;

import java.util.List;

public class CommentService {

	private CommentDAO_interface dao;
	
	public CommentService() {
		dao = new CommentDAO();
	}
	public CommentVO addComment(Integer restId, Integer memberId, Integer rating, String content) {
		
		CommentVO commentVO = new CommentVO();
		
		commentVO.setRestId(restId);
		commentVO.setMemberId(memberId);
		commentVO.setRating(rating);
		commentVO.setContent(content);
		
		return commentVO;
	}
	
	public CommentVO updateComment(Integer commentId, Integer restId, Integer memberId, Integer rating, String content) {
		
	CommentVO commentVO = new CommentVO();
	
	commentVO.setCommentId(commentId);
	commentVO.setRestId(restId);
	commentVO.setMemberId(memberId);
	commentVO.setRating(rating);
	commentVO.setContent(content);
	dao.update(commentVO);
	
	return commentVO;
	}
	
	public void deletedComment(Integer commentId) { 
	dao.delete(commentId);	
	}
	
	public CommentVO getOneComment(Integer commentId) {
		return dao.findByPrimaryKey(commentId);
	}
	
	public List<CommentVO> getAll(){
		return dao.getAll();
	}
	
}
