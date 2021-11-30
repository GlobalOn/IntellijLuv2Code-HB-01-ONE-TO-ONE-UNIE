package com.luv2code.hibernate.demo.ManyToMany;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewsDemo {
    public static void main(String[] args) {

        //create session factory
        SessionFactory sessionFactory = new Configuration().
                configure("hibernate-one-to-many-uni.cfg.xml").
                addAnnotatedClass(Instructor.class).
                addAnnotatedClass(InstructorDetail.class).
                addAnnotatedClass(Course.class).
                addAnnotatedClass(Review.class).
                buildSessionFactory();
        //create session

        try (sessionFactory; Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            //create a course
            Course tempCourse = new Course("Dota2 - how 2 up 9k MMR");
            //add some reviews
            Review review1 = new Review("No way to do it.");
            Review review2 = new Review("Much more easier than u think.");
            Review review3 = new Review("Only few players could get it.");
            Review review4 = new Review("Just play more.");
            Review review5 = new Review("U need 2 play more.");

            tempCourse.addReview(review1);
            tempCourse.addReview(review2);
            tempCourse.addReview(review3);
            tempCourse.addReview(review4);
            tempCourse.addReview(review5);
            //save the course and leverage the cascade all
             session.save(tempCourse);
            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
