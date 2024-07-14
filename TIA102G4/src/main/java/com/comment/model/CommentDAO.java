package com.comment.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class CommentDAO implements CommentDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/chugether");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT =
	   "INSERT INTO comment (restId,memberId,rating,content) VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT =
			"SELECT commentId,restId,memberId,rating,content FROM comment order by commentId";
	private static final String GET_ONE_STMT =
			"SELECT commentId,restId,memberId,rating,content FROM comment where commentId = ?";
	private static final String DELETE =
			"DELETE FROM comment where commentId = ?";
	private static final String UPDATE =
			"UPDATE comment set restId=?,memberId=?,rating=?,content=? where commentId =?";
	@Override
	public void insert(CommentVO commentVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, commentVO.getRestId());
			pstmt.setInt(2, commentVO.getMemberId());
			pstmt.setInt(3, commentVO.getRating());
			pstmt.setString(4, commentVO.getContent());
			
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}	
		
	@Override
	public void update(CommentVO commentVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
       try {
			
		    con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, commentVO.getRestId());
			pstmt.setInt(2, commentVO.getMemberId());
			pstmt.setInt(3, commentVO.getRating());
			pstmt.setString(4, commentVO.getContent());
			pstmt.setInt(5, commentVO.getCommentId());
			
			pstmt.executeUpdate();
			
       } catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());	
       } finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}	
		
	@Override
	public void delete(Integer commentId) {

			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
				
				pstmt.setInt(1,commentId);
				
				pstmt.executeUpdate();
				
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());	
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}

		}	
	
	@Override
	public CommentVO findByPrimaryKey(Integer commentId) {
		CommentVO commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
            pstmt.setInt(1,commentId);
			
			rs = pstmt.executeQuery();
			
            while (rs.next()) {
				
				commentVO = new CommentVO();
				commentVO.setCommentId(rs.getInt("commentId"));
				commentVO.setRestId(rs.getInt("restId"));
				commentVO.setMemberId(rs.getInt("memberId"));
				commentVO.setRating(rs.getInt("rating"));
				commentVO.setContent(rs.getString("content"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());	
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return commentVO;
		
	}
			
	@Override
	public List<CommentVO> getAll() {
		   List<CommentVO> list = new ArrayList<CommentVO>();
		   CommentVO commentVO = null;	
		    
		    Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					
					commentVO = new CommentVO();
					commentVO.setCommentId(rs.getInt("commentId"));
					commentVO.setRestId(rs.getInt("restId"));
					commentVO.setMemberId(rs.getInt("memberId"));
					commentVO.setRating(rs.getInt("rating"));
					commentVO.setContent(rs.getString("content"));
					list.add(commentVO);
		    }
		} catch (SQLException se) {
		    throw new RuntimeException("A database error occured. "
				   + se.getMessage());	
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}    
	


