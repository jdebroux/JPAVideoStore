package com.skilldistillery.jpavideostore.client;

import com.skilldistillery.jpavideostore.data.ActorDAOImpl;

public class ActorDAOTest {
	
	private static ActorDAOImpl dao = new ActorDAOImpl();
	
	public static void main(String[] args) {
		dao = new ActorDAOImpl();
//		Actor actor = new Actor("Leroy", "Jenkins");
//		System.out.println("Before persist: " + actor);
//		dao.create(actor);
//		System.out.println("After persist: " + actor);
//		System.out.println("************************");
//		Actor anotherActor = new Actor("Peppy", "LaPew");
//		anotherActor = dao.update(202, anotherActor);
//		System.out.println(anotherActor);
		System.out.println(dao.destory(201));
	}
}
