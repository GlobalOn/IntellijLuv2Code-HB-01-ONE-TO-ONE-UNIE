package com.luv2code.hibernate.demo.InstructorDemo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {
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

            //get a course
            int id = 20;
            Course tempCourse = session.get(Course.class, id);

            //delete course
            session.delete(tempCourse);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
