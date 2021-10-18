package com.luv2code.hibernate.demo.InstructorDemo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {
    public static void main(String[] args) {

        //create session factory
        SessionFactory sessionFactory = new Configuration().
                configure("hibernate-one-to-many.cfg.xml").
                addAnnotatedClass(Instructor.class).
                addAnnotatedClass(InstructorDetail.class).
                buildSessionFactory();
        //create session
        Session session = sessionFactory.getCurrentSession();


        try {
            //Start a transaction
            session.beginTransaction();

            //Get the instructor by id
            Instructor instructor = session.get(Instructor.class, 1);

            //Delete that instructor
            //Note: this will also delete InstructorDetails
            //cause of CascadeType = ALL
            if (instructor != null) {
                session.delete(instructor);
            }
            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }
    }
}
