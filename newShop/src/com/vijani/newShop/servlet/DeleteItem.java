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


public class DeleteItem extends HttpServlet{

	private static final long serialVersionUID = 3929754882026245094L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Long id = (Long.parseLong(request.getParameter("id")));
		
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
		
		session.delete(item);
		//session.delete(this.find(deleteID));
		session.getTransaction().commit();
		response.getWriter().append("item deleted");
		session.close();
		
	}


	/*
	 * public Product find(int id) {
		Product product = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			org.hibernate.query.Query<Product> query = session.createQuery("from Product where id = :id", Product.class);
			query.setParameter("id", id);
			product = query.getSingleResult();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return product;
	}
	 * */
	
}

