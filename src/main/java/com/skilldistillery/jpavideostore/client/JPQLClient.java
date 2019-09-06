package com.skilldistillery.jpavideostore.client;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.jpavideostore.entities.Staff;

public class JPQLClient {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideoStore");
		EntityManager em = emf.createEntityManager();
		
		String qS = "SELECT staff FROM Staff staff WHERE staff.id < 10";
		
		List<Staff> results = em.createQuery(qS, Staff.class).getResultList();
		
		for (Staff staff : results) {
			System.out.println(staff.getFirstName() + " " + staff.getLastName());
		}
		
		System.out.println("***************");
		
		qS = "SELECT staff.lastName FROM Staff staff WHERE staff.id < 10";
		List<String> lastNames = em.createQuery(qS, String.class).getResultList();
		
		for (String lN : lastNames) {
			System.out.println(lN);
		}
		
		System.out.println("***************");

		qS = "SELECT staff.firstName, staff.lastName FROM Staff staff WHERE staff.id < :Id";
		List<Object[]> results2 = em.createQuery(qS, Object[].class).setParameter("Id", 10).getResultList();
		
		for (Object[] obj : results2) {
			System.out.println("First Name: " + obj[0] + "\tLast Name: " + obj[1]);
		}
		
		System.out.println("***************");
		
		//this is an example why you don't use .getSingleResult
		/*  qS = "SELECT staff FROM Staff staff WHERE staff.id = :id";
		Staff staff = em.createQuery(qS, Staff.class).setParameter("id", -1).getSingleResult();
		if(staff != null) {
		System.out.println(staff);
		}  */
		
		em.close();
		emf.close();
	}

}
