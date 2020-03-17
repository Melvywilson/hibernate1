package com.new1;
import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
public class ManageEmployee {
private static SessionFactory factory;
public static void main(String[] args)
{
	try
	{
		factory=new Configuration().configure().buildSessionFactory();
	}
	catch(Throwable ex)
	{
		System.err.println("Failed to create sessionFactory object."+ex);
		throw new ExceptionInInitializerError(ex);
	}
	ManageEmployee ME=new ManageEmployee();
	/*add a few employee records in database*/
	Integer empID1=ME.addEmployee("Zara","Ali",2000);
	Integer empID2=ME.addEmployee("Daisy","Das",5000);
	Integer empID3=ME.addEmployee("John","Paul",5000);
	Integer empID4=ME.addEmployee("Mohd","Yasee",3000);
	/*list down all employee details*/
	ME.listEmployees();
	/*print total employees count*/
	ME.countEmployees();
	/*Print Total Salary*/
	ME.totalSalary();
}
public Integer addEmployee(String fname,String lname,int salary) {
	Session session=factory.openSession();
	Transaction tx=null;
	Integer employeeID=null;
	try {
		tx=session.beginTransaction();
		Employee employee=new Employee(fname,lname,salary);
		employeeID=(Integer)session.save(employee);
		tx.commit();
	}
	catch(HibernateException e)
	{
		if(tx!=null)tx.rollback();
		e.printStackTrace();
	}
	finally {
		session.close();
		
	}
	return employeeID;
}
/*Method to read all the employee having salary more than 2000*/
public void listEmployees()
{
	Session session=factory.openSession();
	Transaction tx=null;
	try
	{
		tx=session.beginTransaction();
		Criteria cr=session.createCriteria(Employee.class);
		//add restriction
		cr.add(Restrictions.gt("salary", 2000));
		List employees=cr.list();
		for(Iterator iterator=employees.iterator();iterator.hasNext();) {
			Employee employee=(Employee)iterator.next();
			System.out.print("First Name:"+employee.getFirstName());
			System.out.print("Last Name:"+employee.getLastName());
			System.out.print("Salary:"+employee.getSalary());
		}
		tx.commit();
	}
	catch(HibernateException e) {
		if(tx!=null)tx.rollback();
		e.printStackTrace();
	}finally {
		session.close();
	}
}
	/*method to print total number of records*/
	public void countEmployees() {
		Session session=factory.openSession();
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			Criteria cr=session.createCriteria(Employee.class);
			//to get total row count
			cr.setProjection(Projections.rowCount());
			List rowCount=cr.list();
			System.out.println("Total Count:"+rowCount.get(0));
			tx.commit();
		
		}
		catch(HibernateException e) {
			if(tx!=null)tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	/*method  to print sum of salaries */
	public void totalSalary() {
		Session session=factory.openSession();
		Transaction tx=null;
		try
		{
			tx=session.beginTransaction();
			Criteria cr=session.createCriteria(Employee.class);
			//to get total salary.
			cr.setProjection(Projections.sum("salary"));
			List totalSalary=cr.list();
			System.out.println("Total Salary:"+totalSalary.get(0));
			tx.commit();
			
		}
		catch(HibernateException e) {
			if(tx!=null)tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}

