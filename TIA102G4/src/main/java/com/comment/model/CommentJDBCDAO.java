package com.comment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CommentJDBCDAO implements CommentDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/chugether?serverTimezone=Asia/Taipei";
    String userid = "root";
	String passwd = "123456";
	
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
						
			pstmt.setInt(1, commentVO.getRestId());
			pstmt.setInt(2, commentVO.getMemberId());
			pstmt.setInt(3, commentVO.getRating());
			pstmt.setString(4, commentVO.getContent());
			
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void update(CommentVO commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setInt(2, commentVO.getRestId());
			pstmt.setInt(3, commentVO.getMemberId());
			pstmt.setInt(4, commentVO.getRating());
			pstmt.setString(5, commentVO.getContent());
			pstmt.setInt(1, commentVO.getCommentId());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1,commentId);
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public CommentVO findByPrimaryKey(Integer CommentId) {
		CommentVO commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1,CommentId);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				commentVO = new CommentVO();
				commentVO.setCommentId(rs.getInt("commentId"));
				commentVO.setRestId(rs.getInt("restId"));
				commentVO.setMemberId(rs.getInt("memberId"));
				commentVO.setRating(rs.getInt("rating"));
				commentVO.setContent(rs.getString("content"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public static void main (String[] args) {
		CommentJDBCDAO dao = new CommentJDBCDAO();
		
		//新增
//	    CommentVO commentVO1 = new CommentVO();
//	    commentVO1.setRestId(5);
//	    commentVO1.setMemberId(2);
//	    commentVO1.setRating(3);
//	    commentVO1.setContent("感覺還好");
//	    dao.insert(commentVO1);
	    
	    //修改
//	    CommentVO commentVO2 = new CommentVO();
//	    commentVO2.setCommentId(7);
//	    commentVO2.setRestId(3);
//	    commentVO2.setMemberId(2);
//	    commentVO2.setRating(3);
//	    commentVO2.setContent("還行");
//	    dao.update(commentVO2);
	    
	    //刪除
//	    dao.delete(6);
	    
	    //查詢
//	    CommentVO commentVO3 = dao.findByPrimaryKey(3);
//	    System.out.print(commentVO3.getCommentId() + ",");
//		System.out.print(commentVO3.getRestId() + ",");
//		System.out.print(commentVO3.getMemberId() + ",");
//		System.out.print(commentVO3.getRating() + ",");
//		System.out.print(commentVO3.getContent() + ",");		
//		System.out.println("---------------------");
		
		//查詢
		List<CommentVO> list = dao.getAll();
		for (CommentVO aComment : list) {
		System.out.print(aComment.getCommentId() + ",");
		System.out.print(aComment.getRestId() + ",");
		System.out.print(aComment.getMemberId() + ",");
		System.out.print(aComment.getRating() + ",");
		System.out.print(aComment.getContent() + ",");
	    System.out.println();
		}

	}
}
