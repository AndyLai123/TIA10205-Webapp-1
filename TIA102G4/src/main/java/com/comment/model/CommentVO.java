package com.comment.model;


public class CommentVO implements java.io.Serializable {
    private Integer commentId;
    private Integer restId;
    private Integer memberId;
    private Integer rating;
    private String  content;
    
    public Integer getCommentId() {
    	return commentId;
    }
    public void setCommentId(Integer commentId) {
    	this.commentId = commentId;
    }
    public Integer getRestId() {
    	return restId;
    }
    public void setRestId(Integer restId) {
    	this.restId = restId;
    }
    public Integer getMemberId() {
    	return memberId;
    }
    public void setMemberId(Integer memberId) {
    	this.memberId = memberId;
    }
    public Integer getRating() {
    	return rating;
    }
    public void setRating(Integer rating) {
    	this.rating = rating;
    }
    public String getContent() {
    	return content;
    }
    public void setContent(String content) {
    	this.content = content;
}
}