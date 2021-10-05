package com.luv2code.hibernate.demo.InstructorDemo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args) {

        //create session factory
        SessionFactory sessionFactory = new Configuration().
                configure("hibernate-one-to-one.cfg.xml").
                addAnnotatedClass(Instructor.class).
                addAnnotatedClass(InstructorDetail.class).
                buildSessionFactory();
        //create session
        Session session = sessionFactory.getCurrentSession();


        try {
            //Create the objects
            Instructor instructor = new Instructor("Amanda", "Lorane", "LorameM@gmail.com");
            InstructorDetail instructorDetail = new InstructorDetail("https://youTube/YournewHouse", "HouseBuilding");

            //Associate objects together
            instructor.setInstructorDetail(instructorDetail);
            //start a transaction
            session.beginTransaction();

            //Save the Instructor
            //Note: this will also save InstructorDetails
            //cause of CascadeType = ALL
            session.save(instructor);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }
    }
}
