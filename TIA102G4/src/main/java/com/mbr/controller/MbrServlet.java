package com.mbr.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;
import javax.servlet.*;

import javax.servlet.http.*;
import javax.servlet.http.Part;
import com.mbr.model.*;


public class MbrServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String str = req.getParameter("memberId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入員工編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/mbr/mbr.do/update_mbr_input.jsp");
				failureView.forward(req, res);
				return;
        }
			Integer memberId = null;
			try {
				memberId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("員工編號格式不正確");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/mbr/mbr.do/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			MbrService mbrSvc = new MbrService();
			MbrVO mbrVO = mbrSvc.getOneMbr(memberId);
			if(mbrVO == null) {
				errorMsgs.add("查無資料");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/mbr/mbr.do/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			//3.
			req.setAttribute("mbrVO", mbrVO);
			RequestDispatcher successView = req.getRequestDispatcher("/mbr/mbr.do/listOneMbr.jsp"); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			Integer memberId = Integer.valueOf(req.getParameter("memberId"));
			
			MbrService mbrSvc = new MbrService();
			MbrVO mbrVO = mbrSvc.getOneMbr(memberId);
			
			req.setAttribute("mbrVO", mbrVO);
			RequestDispatcher successView = req.getRequestDispatcher("/mbr/mbr.do/update_mbr_input.jsp");// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
	}
         if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
         Integer memberId = Integer.valueOf(req.getParameter("memberId"));
         
         java.sql.Date regisdate = null;
	     try {
              regisdate = java.sql.Date.valueOf(req.getParameter("regisdate").trim());
         } catch (IllegalArgumentException e) {
        	 regisdate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			} 
         String name = req.getParameter("name");
         String nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
               if(name == null || name.trim().length()== 0) {
            	  errorMsgs.add("姓名: 請勿空白");   
               } else if(!name.trim().matches(nameReg)) {
            	  errorMsgs.add("姓名:中文或英文字母數字且長度必需在2到10之間");   
               }
         String account = req.getParameter("account");
         String accountReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
           if(account == null || account.trim().length()== 0) {
            	  errorMsgs.add("帳號請勿空白");   
               } else if(!account.trim().matches(accountReg)) {
            	  errorMsgs.add("帳號:英文字母數字且長度必需在2到10之間");   
               } 
            String password =req.getParameter("password");
            String passwordReg ="^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$"; 
            if(password == null || password.trim().length()== 0) {
            	 errorMsgs.add("密碼請勿空白");
            } else if(!password.trim().matches(passwordReg)) {
          	  errorMsgs.add("密碼:英文字母數字且長度必需在2到10之間");   
             }
            String email =req.getParameter("email");
            String emailReg ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"; 
            if(email == null || email.trim().length()== 0) {
            	 errorMsgs.add("信箱請勿空白");
            } else if(!email.trim().matches(emailReg)) {
          	  errorMsgs.add("信箱:格式不對"); 	 
            }
            Integer gender = Integer.valueOf(req.getParameter("gender"));	
            
            String mobileno =req.getParameter("mobileno");
            String mobilenoReg ="^\\d{10}$"; 
            if(mobileno ==null || mobileno.trim().length()== 0) {
            	 errorMsgs.add("電話請勿空白");
            } else if(!mobileno.trim().matches(mobilenoReg)) {
          	  errorMsgs.add("電話:必需在10位數字");
            }
            Part filePart = req.getPart("sticker");
            byte[] sticker = null;
            if (filePart != null && filePart.getSize() > 0) {
                InputStream inputStream = filePart.getInputStream();
                sticker = inputStream.readAllBytes();
            }
            
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
            
            if (!errorMsgs.isEmpty()) {
            req.setAttribute("mbrVO", mbrVO);
            RequestDispatcher failureView = req.getRequestDispatcher("/mbr/mbr.do/update_mbr_input.jsp");
            failureView.forward(req, res);
			return; 
         }
         
         MbrService mbrSvc = new MbrService();
         mbrVO = mbrSvc.updateMbr(memberId, regisdate, name, account, password, email, gender, mobileno, sticker);
         
         req.setAttribute("mbrVO", mbrVO);
         String url = "/mbr/listOneMbr.jsp";
         RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
		 successView.forward(req, res);
       }
         if ("insert".equals(action)) { 
        	 
        	 List<String> errorMsgs = new LinkedList<String>();
 			req.setAttribute("errorMsgs", errorMsgs);
         
 			java.sql.Date regisdate = null;
			try {
          regisdate = java.sql.Date.valueOf(req.getParameter("regisdate").trim());
         } catch (IllegalArgumentException e) {
        	 regisdate=new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
         }
			
		 String name = req.getParameter("name");
         String nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
          if(name == null || name.trim().length()== 0) {
          	 errorMsgs.add("姓名: 請勿空白");   
            } else if(!name.trim().matches(nameReg)) {
          	 errorMsgs.add("姓名:中文或英文字母數字且長度必需在2到10之間");   
            }
          
          String account = req.getParameter("account");
          String accountReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
          if(account == null || account.trim().length()== 0) {
           	  errorMsgs.add("帳號請勿空白");   
              } else if(!account.trim().matches(accountReg)) {
           	  errorMsgs.add("帳號:英文字母數字且長度必需在2到10之間");   
              }
          
          String password =req.getParameter("password");
          String passwordReg ="^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$"; 
          if(password == null || password.trim().length()== 0) {
          	 errorMsgs.add("密碼請勿空白");
          } else if(!password.trim().matches(passwordReg)) {
        	  errorMsgs.add("密碼:英文字母數字且長度必需在2到10之間");   
           }
          
          String email =req.getParameter("email");
          String emailReg ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,6}$"; 
          if(email == null || email.trim().length()== 0) {
          	 errorMsgs.add("信箱請勿空白");
          } else if(!email.trim().matches(emailReg)) {
        	  errorMsgs.add("信箱:格式不正確"); 	 
          }
          
          Integer gender = Integer.valueOf(req.getParameter("gender"));
          
          String mobileno =req.getParameter("mobileno");
          String mobilenoReg ="^\\d{10}$"; 
          if(mobileno == null || mobileno.trim().length()== 0) {
          	 errorMsgs.add("電話請勿空白");
          } else if(!mobileno.trim().matches(mobilenoReg)) {
        	  errorMsgs.add("電話:必需是10位數字");
          }
          Part filePart = req.getPart("sticker");
          byte[] sticker = null;
          if (filePart != null && filePart.getSize() > 0) {
              InputStream inputStream = filePart.getInputStream();
              sticker = inputStream.readAllBytes();
          }
          
          MbrVO mbrVO = new MbrVO();
          mbrVO.setRegisdate(regisdate);
          mbrVO.setName(name);
          mbrVO.setAccount(account);
          mbrVO.setPassword(password);
          mbrVO.setEmail(email);
          mbrVO.setGender(gender);
          mbrVO.setMobileno(mobileno);
          mbrVO.setSticker(sticker);
          
          if (!errorMsgs.isEmpty()) {
        	req.setAttribute("mbrVO", mbrVO);
        	RequestDispatcher failureView = req.getRequestDispatcher("/mbr/mbr.do/addMbr.jsp");
        	failureView.forward(req, res);
			return;
		}
         
          MbrService mbrSvc = new MbrService();
          mbrVO = mbrSvc.addMbr(regisdate, nameReg, accountReg, passwordReg, emailReg, gender, mobileno, sticker);
          String url = "/back-end/mbr/listAllMbr.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
         }
         
         if ("delete".equals(action)) { 

 			List<String> errorMsgs = new LinkedList<String>();
 			req.setAttribute("errorMsgs", errorMsgs);
          
 			Integer memberId = Integer.valueOf(req.getParameter("memberId"));
 			
 			MbrService mbrSvc = new MbrService();
 			mbrSvc.deleteMbr(memberId);
 			
 			String url = "/mbr/mbr.do/listAllMbr.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
         }
	}
             
}        