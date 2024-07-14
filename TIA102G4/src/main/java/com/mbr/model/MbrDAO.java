package com.mbr.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MbrDAO implements MbrDAO_interface{
    
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
			"INSERT INTO member (regisdate,name,account,password,email,gender,mobileno,sticker) VALUES (?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT =
			"SELECT memberId,regisdate,name,account,password,email,gender,mobileno,sticker FROM member order by memberId";
	private static final String GET_ONE_STMT =
			"SELECT memberId,regisdate,name,account,password,email,gender,mobileno,sticker FROM member where memberId = ?";
	private static final String DELETE =
			"DELET FROM member where memberId = ?";
	private static final String UPDATE =
			"UPDATE member set memberId=?,regisdate=?,name=?,account=?,password=?,email=?,gender=?,mobileno=?,sticker=? where memberId = ?";
	@Override
	public void insert(MbrVO mbrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
    try {
			
		 con = ds.getConnection();
		 pstmt = con.prepareStatement(INSERT_STMT);
		 
		 pstmt.setDate(1, mbrVO.getRegisdate());
		 pstmt.setString(2, mbrVO.getName());
		 pstmt.setString(3, mbrVO.getAccount());
		 pstmt.setString(4, mbrVO.getPassword());
		 pstmt.setString(5, mbrVO.getEmail());
		 pstmt.setInt(6, mbrVO.getGender());
		 pstmt.setString(7, mbrVO.getMobileno());
		 pstmt.setBytes(8, mbrVO.getSticker());
		
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
	public void update(MbrVO mbrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		 try {
				
			  con = ds.getConnection();
			  pstmt = con.prepareStatement(UPDATE);
			  
			  pstmt.setDate(1, mbrVO.getRegisdate());
			  pstmt.setString(2, mbrVO.getName());
			  pstmt.setString(3, mbrVO.getAccount());
			  pstmt.setString(4, mbrVO.getPassword());
			  pstmt.setString(5, mbrVO.getEmail());
			  pstmt.setInt(6, mbrVO.getGender());
			  pstmt.setString(7, mbrVO.getMobileno());
			  pstmt.setBytes(8, mbrVO.getSticker());
			  pstmt.setInt(9, mbrVO.getMemberId());
			  
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
	public void delete(Integer memberId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, memberId);
			
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
	public MbrVO findByPrimaryKey(Integer memberId) {
		MbrVO mbrVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
	
			pstmt.setLong(1, memberId);
			rs = pstmt.executeQuery();
			
        while (rs.next()) {
				
				mbrVO = new MbrVO();
				mbrVO.setMemberId(rs.getInt("memberid"));
				mbrVO.setRegisdate(rs.getDate("regisdate"));
				mbrVO.setName(rs.getString("name"));
				mbrVO.setAccount(rs.getString("account"));
				mbrVO.setPassword(rs.getString("password"));
				mbrVO.setEmail(rs.getString("email"));
				mbrVO.setGender(rs.getInt("gender"));
				mbrVO.setMobileno(rs.getString("mobileno"));
				mbrVO.setSticker(rs.getBytes("sticker"));
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
		return mbrVO;
		
	}
	@Override
	public List<MbrVO> getAll() {
		List<MbrVO> list = new ArrayList<MbrVO>();
		MbrVO mbrVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
   	
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			 while (rs.next()) {
					
					mbrVO = new MbrVO();
					mbrVO.setMemberId(rs.getInt("memberId"));
					mbrVO.setRegisdate(rs.getDate("regisdate"));
					mbrVO.setName(rs.getString("name"));
					mbrVO.setAccount(rs.getString("account"));
					mbrVO.setPassword(rs.getString("password"));
					mbrVO.setEmail(rs.getString("email"));
					mbrVO.setGender(rs.getInt("gender"));
					mbrVO.setMobileno(rs.getString("mobileno"));
					mbrVO.setSticker(rs.getBytes("sticker"));
					list.add(mbrVO);
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

