package com.luv2code.hibernate.demo.InstructorDemo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class CreateCoursesDemo {
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

            //create courses
            Course course1 = new Course("Cooking crispy Idaho potato");
            Course course2 = new Course("Cooking spicy wings");
            Course course3 = new Course("Cooking royal beef");
            Course course4 = new Course("Cooking chocolate pie");
            Course course5 = new Course("Cooking vegetables grill");

            //add this courses to the instructor
            instructor.add(course1);
            instructor.add(course2);
            instructor.add(course3);
            instructor.add(course4);
            instructor.add(course5);

            //save the courses
            session.save(course1);
            session.save(course2);
            session.save(course3);
            session.save(course4);
            session.save(course5);
            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
