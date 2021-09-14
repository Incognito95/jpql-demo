package tester;

import entity.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Employee("xa12tt", "Kurt", "Wonnegut", new BigDecimal(335567)));
            em.persist(new Employee("hyu654", "Hanne", "Olsen", new BigDecimal(435867)));
            em.persist(new Employee("uio876", "Jan", "Olsen", new BigDecimal(411567)));
            em.persist(new Employee("klo999", "Irene", "Petersen", new BigDecimal(33567)));
            em.persist(new Employee("jik666", "Tian", "Wonnegut", new BigDecimal(56567)));
            em.getTransaction().commit();
            
            //Complete all these small tasks. Your will find the solution to all, but the last,
            //In this document: https://en.wikibooks.org/wiki/Java_Persistence/JPQL#JPQL_supported_functions
            
            //1) Create a query to fetch all employees with a salary > 100000 and print out all the salaries
            Query query = em.createQuery("SELECT e FROM Employee e WHERE e.salary > 100000");
            List<Employee> result = query.getResultList();
            System.out.println("here's a list of all employees with a salary > 100000: " + result);

            //2) Create a query to fetch the employee with the id "klo999" and print out the firstname
            // Query for a single object.
            Query getEmployeeById = em.createQuery("SELECT e FROM Employee e WHERE e = 'klo999'");
            query.setParameter("id", getEmployeeById);
            Employee result2 = (Employee)query.getSingleResult();
            System.out.println("employee with the id klo999: " + result2);

            //3) Create a query to fetch the highest salary and print the value
            // Query for a single data element.
            Query getHighestSalary = em.createQuery("SELECT MAX(e.salary) FROM Employee e");
            BigDecimal result3 = (BigDecimal)query.getSingleResult();
            System.out.println("the highest salary: " + result3);

            //4) Create a query to fetch the firstName of all Employees and print the names
            // Query for a List of data elements.
            Query fetchFirstName = em.createQuery("SELECT e.firstName FROM Employee e");
            List<String> result4 = query.getResultList();
            System.out.println("Fetch firstname of all employees: " + result4);

            //5 Create a query to calculate the number of employees and print the number
            Query numberOfEmployees = em.createQuery("SELECT COUNT(Employee) FROM Employee e");
            List<String> result5 = query.getResultList();
            System.out.println("The number of employees are: " + result5);

            //6 Create a query to fetch the Employee with the higest salary and print all his details
            Query employeeWithHighestSalary = em.createQuery("SELECT MAX(e.salary) FROM Employee e");
            List<Employee> result6 = query.getResultList();
            System.out.println("The employee with the highest salary is: " + result6);


        } finally {
            em.close();
            emf.close();
        }
    }

}
