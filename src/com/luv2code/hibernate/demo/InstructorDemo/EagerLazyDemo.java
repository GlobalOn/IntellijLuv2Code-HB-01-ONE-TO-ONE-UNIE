package com.luv2code.hibernate.demo.InstructorDemo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {
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
            session.beginTransaction();

            int theId = 1;

            //get the instructor from DB
            Instructor instructor = session.get(Instructor.class, theId);

            System.out.println("LUV2CODE: " + instructor);

            //Get course for instructor
            System.out.println("LUV2CODE: " + instructor.getCourse());

            //commit transaction
            session.getTransaction().commit();

            //close the session
            session.close();
            System.out.println("LUV2CODE: " + instructor.getCourse());

            System.out.println("Done!");
        }
    }
}
