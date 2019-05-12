package com.vijani.newShop.servlet;

import java.io.IOException;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.vijani.newShop.database.HibernateUtil;
import com.vijani.newShop.entity.Item;

public class UpdateItem extends HttpServlet{

	private static final long serialVersionUID = -3899792666229102785L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Long id = (Long.parseLong(request.getParameter("id")));
		
		String updatedName = request.getParameter("newName");
		Integer updatedQty = (Integer.parseInt(request.getParameter("newQty")));
		Float updatedUnitPrice = (Float.parseFloat(request.getParameter("newUnitPrice")));
	
		session.getTransaction().begin();
		
		Item item = null;
		try {
		Query q =  session.createQuery("select i from Item i where id = :id", Item.class);
		q.setParameter("id", id);
		item = (Item) q.getSingleResult();
		}catch(Exception e) {
			if(item == null)
				System.out.println("Item not found!");
		}
		
		item.setName(updatedName);
		item.setQtyOnHand(updatedQty);
		item.setUnitPrice(updatedUnitPrice);
		
		session.update(item);
		session.getTransaction().commit();
		response.getWriter().append("item updated");
		session.close();
		
		 
	}

}
