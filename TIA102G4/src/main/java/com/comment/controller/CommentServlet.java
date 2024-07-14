package com.comment.controller;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comment.model.*;

public class CommentServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
	        throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			    String str = req.getParameter("commentId");
			    if (str == null || (str.trim()).length() ==0) {
			    	errorMsgs.add("請輸入討論ID");
			    }
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
            
			Integer commentId = null;
			try {
				commentId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("討論ID格式不正確");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			
			CommentService commentSvc = new CommentService();
			CommentVO commentVO = commentSvc.getOneComment(commentId);
			if(commentVO == null) {
				errorMsgs.add("查無資料");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			
			req.setAttribute("commentVO", commentVO);
			String url = "/emp/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer commentId = Integer.valueOf(req.getParameter("commentId"));
			
			CommentService commentSvc = new CommentService();
			CommentVO commentVO = commentSvc.getOneComment(commentId);
			
			req.setAttribute("commentVO", commentVO);
			String url ="/emp/update_emp_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
Integer commentId = Integer.valueOf(req.getParameter("commentId").trim());

Integer restId = Integer.valueOf(req.getParameter("restId").trim());
		}
	}
}