package com.vijani.newShop.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vijani.newShop.database.HibernateUtil;
import com.vijani.newShop.entity.Item;


public class GetItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q =  session.createQuery("select i from Item i");        
        
        List<Item> items = q.getResultList();
        
        //HibernateUtil.getSessionFactory().close();
        
        ObjectMapper mapper = new ObjectMapper(); 
        String itemsString = mapper.writeValueAsString(items);
        
		response.getWriter().append(itemsString);
	}

}