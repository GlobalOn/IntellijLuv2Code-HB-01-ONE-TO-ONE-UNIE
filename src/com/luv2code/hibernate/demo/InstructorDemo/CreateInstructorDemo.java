package com.luv2code.hibernate.demo.InstructorDemo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {
    public static void main(String[] args) {

        //create session factory
        SessionFactory sessionFactory = new Configuration().
                configure("hibernate-one-to-many.cfg.xml").
                addAnnotatedClass(Instructor.class).
                addAnnotatedClass(InstructorDetail.class).
                addAnnotatedClass(Course.class).
                buildSessionFactory();
        //create session

        try (sessionFactory; Session session = sessionFactory.getCurrentSession()) {
            //Create the objects
            Instructor instructor = new Instructor("Susan", "Public", "SuzanP@gmail.com");
            InstructorDetail instructorDetail = new InstructorDetail("https://youTube/CookWithSu", "Cooking");
//            Course course = new Course("Cooking course");

            //Associate objects together
            instructor.setInstructorDetail(instructorDetail);
//            instructor.add(course);
            //start a transaction
            session.beginTransaction();

            //Save the Instructor
            //Note: this will also save InstructorDetails
            //cause of CascadeType = ALL
            session.save(instructor);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
