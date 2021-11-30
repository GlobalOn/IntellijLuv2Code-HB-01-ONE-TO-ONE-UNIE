package com.luv2code.hibernate.demo.ManyToMany;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCoursesForJerryDemo {
    public static void main(String[] args) {

        //create session factory
        SessionFactory sessionFactory = new Configuration().
                configure("hibernate-many-to-many.cfg.xml").
                addAnnotatedClass(Instructor.class).
                addAnnotatedClass(InstructorDetail.class).
                addAnnotatedClass(Course.class).
                addAnnotatedClass(Review.class).
                addAnnotatedClass(Student.class).
                buildSessionFactory();
        //create session

        try (sessionFactory; Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Student jerryStudent = session.get(Student.class, 1);

            System.out.println(jerryStudent.getCourses());

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
