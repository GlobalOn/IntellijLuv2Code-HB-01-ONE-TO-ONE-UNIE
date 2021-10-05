package com.luv2code.hibernate.demo.InstructorDemo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {
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
            //Start a transaction
            session.beginTransaction();

            //Get the instructorDetail by id
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, 9);

            System.out.println("InstructorDetail = " + instructorDetail);

            //Print Associated Instructor
            System.out.println(instructorDetail.getInstructor());

            //Remove the associated bi-directional reference/link
            instructorDetail.getInstructor().setInstructorDetail(null);

            //Delete the InstructorDetail
            session.delete(instructorDetail);

            //Commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            //handle connection leak issue (close the session)
            session.close();
            sessionFactory.close();
        }
    }
}
