package com.luv2code.hibernate.demo.StudentDemo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
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

            //create 3 student objects
            System.out.println("Creating three student objects...");
            Student student1 = new Student("Lary", "Lorn", "Lary2Lamy");
            Student student2 = new Student("Tony", "Pirson", "paTony.com");
            Student student3 = new Student("Neil", "Lirb", "NLirb.ru");
            //start a transaction
            session.beginTransaction();
            //save the student object
            System.out.println("Session saving the students...");
            session.save(student1);
            session.save(student2);
            session.save(student3);
            //commit transaction
            System.out.println("Session getting the transaction, transaction commiting itself...");
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }
    }
}
