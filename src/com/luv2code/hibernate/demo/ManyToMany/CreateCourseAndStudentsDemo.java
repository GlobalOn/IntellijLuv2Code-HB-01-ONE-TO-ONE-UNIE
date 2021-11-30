package com.luv2code.hibernate.demo.ManyToMany;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {
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

            //create a course
            Course tempCourse = new Course("Dota2 - how 2 up 9k MMR");

            //save the course and leverage the cascade all
            session.save(tempCourse);

            //create students
            Student student1 = new Student("Jerry", "Mouse", "jerryMoUsE@email.com");
            Student student2 = new Student("Tom", "Cat", "catTom@email.com");
            Student student3 = new Student("Batty", "Spark", "baTtySp@email.com");

            //add students to the course
            tempCourse.addStudent(student1);
            tempCourse.addStudent(student3);


            //save students
            session.save(student1);
            session.save(student2);
            session.save(student3);
            System.out.println("Saved students are: " + tempCourse.getStudents());

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
