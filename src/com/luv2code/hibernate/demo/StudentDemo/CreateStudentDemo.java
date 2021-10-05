package com.luv2code.hibernate.demo.StudentDemo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {
    public static void main(String[] args) {

        //create session factory
        SessionFactory sessionFactory = new Configuration().
                configure("hibernate-student.cfg.xml").
                addAnnotatedClass(Student.class).
                buildSessionFactory();
        //create session
        Session session = sessionFactory.getCurrentSession();


        try {
            //use the session to handle Java object and make some CRUD operations in DB

            //create the student object
            System.out.println("Creating a student object...");
            Student student = new Student("Mich", "Topson", "MITp@LUv.2code");
            //start a transaction
            session.beginTransaction();
            //save the student object
            System.out.println("Session saving the student...");
            session.save(student);
            //commit transaction
            System.out.println("Session getting the transaction, transaction commiting itself...");
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }
    }
}
