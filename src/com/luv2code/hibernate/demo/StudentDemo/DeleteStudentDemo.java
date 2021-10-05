package com.luv2code.hibernate.demo.StudentDemo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
    public static void main(String[] args) {

        //create session factory
        SessionFactory sessionFactory = new Configuration().
                configure("hibernate-student.cfg.xml").
                addAnnotatedClass(Student.class).
                buildSessionFactory();
        //create session
        Session session = sessionFactory.getCurrentSession();

        int studentId = 3;

        try {
            //DELETE STUDENT FROM DATABASE:

            //1. Getting a new session and start a transaction
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            //2. Retrieve student based on id: primary key and UPDATING this student
            Student studentFromDB = session.get(Student.class, studentId);
            //DELETING THE STUDENT BY StudentObject based on ID
//            session.delete(studentFromDB);
            //3. Commit the transaction
            session.getTransaction().commit();

            //DELETE STUDENT FROM DB SECOND APPROACH (USING QUERY)
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            //.executeUpdate() method uses in the end of any update or delete operation
            session.createQuery("delete Student where lastName='Dota32'").
                                                                        executeUpdate();
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }
    }
}
