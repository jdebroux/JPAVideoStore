package com.skilldistillery.jpavideostore.client;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class IntermediateJPQLLab {
	private static EntityManagerFactory emf;
	static {
		emf = Persistence.createEntityManagerFactory("VideoStore");
	}

	public static void main(String[] args) {
		String qS = "SELECT adr.state, count(adr) FROM Address adr GROUP BY adr.state";
		EntityManager em = emf.createEntityManager();
		List<Object[]> adrs = em.createQuery(qS, Object[].class).getResultList();
		for (Object[] objs : adrs) {
			System.out.println(objs[0] + " " + objs[1]);
		}
	}
}
