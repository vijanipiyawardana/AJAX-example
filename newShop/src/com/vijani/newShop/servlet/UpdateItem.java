package com.vijani.newShop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.vijani.newShop.database.HibernateUtil;

public class UpdateItem extends HttpServlet{

	private static final long serialVersionUID = -3899792666229102785L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		 
	}

}
