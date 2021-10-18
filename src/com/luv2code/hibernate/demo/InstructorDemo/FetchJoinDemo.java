package com.luv2code.hibernate.demo.InstructorDemo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {
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
            Query<Instructor> query = session.createQuery("select  i from Instructor i " +
                                                          "JOIN FETCH i.courses " +
                                                          "WHERE i.id=:theInstructorId",
                                                          Instructor.class);

            //set parameter on query
            query.setParameter("theInstructorId", theId);

            Instructor instructor = query.getSingleResult();

            //commit transaction
            session.getTransaction().commit();

            //close the session
            session.close();

            System.out.println(instructor.getCourse());

            System.out.println("Done!");
        }
    }
}
