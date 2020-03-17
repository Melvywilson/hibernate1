package com.new1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employees")
public class Employee {
	@Column(name="firstName")
private String firstName;
	@Column(name="lastName")
private String lastName;
	@Column(name="salary")
private int salary;
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column
private int employeeId;
public int getEmployeeId() {
	return this.employeeId;
}
public void setEmployeeId(int employeeId) {
	this.employeeId = employeeId;
}
public Employee(String firstName,String lastName,int salary)
{this.firstName=firstName;
this.lastName=lastName;
this.salary=salary;}
public String getFirstName() {
	return this.firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return this.lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public int getSalary() {
	return this.salary;
}
public void setSalary(int salary) {
	this.salary = salary;
}

}
