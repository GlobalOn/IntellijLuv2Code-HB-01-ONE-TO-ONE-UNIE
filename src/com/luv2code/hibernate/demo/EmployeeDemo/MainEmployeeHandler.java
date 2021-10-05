package com.luv2code.hibernate.demo.EmployeeDemo;

import com.luv2code.hibernate.demo.entity.Employee;
import java.util.Scanner;

public class MainEmployeeHandler {
    public static void main(String[] args) {

        Employee employee = new Employee("Drew", "An", "LiverpoolUniversity");

        //Save new Employee in DB
        DBEmployeeCreator dbEmployeeCreator = new DBEmployeeCreator();
        dbEmployeeCreator.createEmployeeInDB(employee);

//        //Delete employee by ID
//        DBEmployeeRemover dbEmployeeRemover = new DBEmployeeRemover();
//        dbEmployeeRemover.removeEmployeeInDB(new Scanner(System.in).nextInt());
//
//        //Update employee company
//        DBEmployeeCompanyUpdater dbEmployeeCompanyUpdater = new DBEmployeeCompanyUpdater();
//        dbEmployeeCompanyUpdater.updateEmployeeCompany(7, "BioLab");
//
//        //Read employee
//        DBEmployeeReader dbEmployeeReader = new DBEmployeeReader();
//        dbEmployeeReader.readEmployee(8);
//
        //Read employees who company is Luxoft
        DBEmployeesByCompany dbEmployeesByCompany = new DBEmployeesByCompany();
        dbEmployeesByCompany.readEmployeeByCompany();
    }
}
