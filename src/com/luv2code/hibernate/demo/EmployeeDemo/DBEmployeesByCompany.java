package com.luv2code.hibernate.demo.EmployeeDemo;

import com.luv2code.hibernate.demo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DBEmployeesByCompany {

    public void readEmployeeByCompany() {
        try (SessionFactory sessionFactory = new Configuration().
                configure("hibernate-employee.cfg.xml").
                addAnnotatedClass(Employee.class).
                buildSessionFactory()) {

            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            List<Employee> employees = session.createQuery("from Employee e where e.company='Luxoft'").getResultList();

            for (Employee e : employees
            ) {
                System.out.println(e);
            }
            session.getTransaction().commit();
        }
    }
}
