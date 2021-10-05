package com.luv2code.hibernate.demo.StudentDemo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {

        //create session factory
        SessionFactory sessionFactory = new Configuration().
                configure("hibernate-student.cfg.xml").
                addAnnotatedClass(Student.class).
                buildSessionFactory();
        //create session
        Session session = sessionFactory.getCurrentSession();


        try {
            session.beginTransaction();

            //Query students
            List<Student> students = session.createQuery("from Student").getResultList();
            //Display the students
            displayStudents(students);

            //Query students with Last Name = 'Doe'
            List<Student> byLastNameStudents = session.createQuery("from Student s where s.lastName = 'Duck' ").getResultList();
            displayStudents(byLastNameStudents);

            //Query students with lastName 'Doe' of firstName 'Duffy'
            List<Student> byLastAndFirstNameStudents = session.
                                                        createQuery("from Student s where s.lastName = 'Duck' or s.firstName = 'Neil'").
                                                        getResultList();
            displayStudents(byLastAndFirstNameStudents);

            //Query students where email ends with LUv.2code
            List<Student> byEmailStudents = session.
                    createQuery("from Student s where s.email like '%com'").
                    getResultList();
            displayStudents(byEmailStudents);

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }
    }

    private static void displayStudents(List<Student> byLastNameStudents) {
        for (Student student : byLastNameStudents) {
            System.out.println(student);
        }
    }
}
