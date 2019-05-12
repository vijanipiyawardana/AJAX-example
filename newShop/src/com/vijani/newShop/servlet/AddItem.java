package com.vijani.newShop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.vijani.newShop.database.HibernateUtil;
import com.vijani.newShop.entity.Item;


public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Item item1 = new Item();
		
		item1.setName(request.getParameter("name"));
		item1.setUnitPrice(Float.parseFloat(request.getParameter("unitPrice")));
		item1.setQtyOnHand(Integer.parseInt(request.getParameter("qtyOnHand")));

		session.getTransaction().begin();
		session.persist(item1);
		session.getTransaction().commit();
		response.getWriter().append("item added");
		session.close();
	}

}
